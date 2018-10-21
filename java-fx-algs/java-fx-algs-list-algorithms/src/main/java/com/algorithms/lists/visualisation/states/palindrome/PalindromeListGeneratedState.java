package com.algorithms.lists.visualisation.states.palindrome;

import com.algorithms.graphics.canvas.Canvas;
import com.algorithms.graphics.toolbar.InformationBar;
import com.algorithms.graphics.toolbar.Toolbar;
import com.algorithms.graphics.toolbar.control.ToolbarControl;
import com.algorithms.lists.node.Node;
import com.algorithms.lists.visualisation.context.ListNodeAlgorithmContext;
import com.algorithms.lists.visualisation.objects.PalindromeControlTypes;
import com.algorithms.lists.visualisation.states.ListAbstractState;
import com.algorithms.lists.visualisation.states.palindrome.task.PalindromeAlgorithmTask;
import com.algorithms.lists.visualisation.states.palindrome.task.PalindromeAlgorithmTask.CompareCharacterResult;
import com.algorithms.lists.visualisation.states.palindrome.task.PalindromeAlgorithmTask.PalindromeVisitEvent;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

import static com.algorithms.lists.visualisation.objects.PalindromeControlTypes.CHECK_PALINDROME;
import static com.algorithms.lists.visualisation.objects.PalindromeControlTypes.CLEAR;
import static com.algorithms.lists.visualisation.objects.PalindromeNodeControlUtils.*;
import static java.util.Objects.isNull;
import static javafx.scene.paint.Color.*;


/**
 * At this point the user generated the list.
 * User should have 2 choices:
 * - Start check to see if generated list is a palindrome.
 * - Clear generated list and draw another one.
 *
 * @author Mihai Alexandru
 * @date 20.10.2018
 */
public class PalindromeListGeneratedState extends ListAbstractState {

    private Node<String> head;

    public PalindromeListGeneratedState(Node<String> head) {
        this.head = head;
    }

    @Override
    public void controlClicked(ToolbarControl control, ListNodeAlgorithmContext context) {
        Toolbar toolbar = context.getToolbar();
        disableGenerateList(toolbar, true);
        disableClearList(toolbar, true);
        if (clicked(CLEAR, control)) {
            executeClearList(context);
        } else if (clicked(CHECK_PALINDROME, control)) {
            executePalindromeCheck(control, context);
        }
    }

    private boolean clicked(PalindromeControlTypes type, ToolbarControl toolbarControl) {
        return type.name().equals(toolbarControl.getType());
    }

    private void executeClearList(ListNodeAlgorithmContext context) {
        Toolbar toolbar = context.getToolbar();
        disableGenerateList(toolbar, false);
        disableClearList(toolbar, true);
        disableSearch(toolbar, true);

        InformationBar informationBar = toolbar.getInformationBar();
        informationBar.changeToInfoIcon();
        informationBar.changeMessage("Generate a list to check if it's a palindrome.");

        context.changeState(new PalindromeGenerateListState());
    }

    private void executePalindromeCheck(ToolbarControl control, ListNodeAlgorithmContext context) {
        disableSearch(context.getToolbar(), false);
        control.getControlAs(Button.class).setText("Stop");
        var task = createTask(context);
        context.changeState(new PalindromeStopCheckState(() -> stopCheck(context.getToolbar(), task)));
        new Thread(task).start();
    }

    private PalindromeAlgorithmTask createTask(ListNodeAlgorithmContext context) {
        var task = new PalindromeAlgorithmTask(head);
        task.valueProperty().addListener(((observableValue, palindromeVisitEvent, newValue) -> processEvent(newValue, context, task)));
        return task;
    }

    private void stopCheck(Toolbar toolbar, PalindromeAlgorithmTask task) {
        disableGenerateList(toolbar, true);
        disableSearch(toolbar, true);
        disableClearList(toolbar, false);
        toolbar.getControl(CHECK_PALINDROME.name()).getControlAs(Button.class).setText("Check if palindrome");
        task.cancel();
        task.stop();
    }

    private void processEvent(PalindromeVisitEvent palindromeVisitEvent, ListNodeAlgorithmContext context, PalindromeAlgorithmTask task) {
        if (isNull(palindromeVisitEvent)) {
            return;
        }
        var canvas = context.getCanvas();
        var node = palindromeVisitEvent.getNode();
        sleepForOneSecond();
        switch (palindromeVisitEvent.getType()) {
            case RUNNER_VISITED:
                visitNode(node, TURQUOISE, canvas);
                break;
            case NODE_VISITED:
                visitNode(node, DEEPSKYBLUE, canvas);
                break;
            case NODE_ADDED_TO_STACK:
                visitNode(node, GREY, canvas);
                break;
            case VALID_PALINDROME:
                processValidPalindromeEvent(context, task);
                break;
            case COMPARE_CHARACTER_RESULT:
                processCompareCharacterEvent(palindromeVisitEvent, context, task);
                break;
            default:
        }
    }

    private void sleepForOneSecond() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void visitNode(Node<String> node, Color color, Canvas<Node<String>> canvas) {
        var canvasNode = canvas.getNode(node.getId());
        canvasNode.ifPresent(cn -> cn.visit(color));
    }

    private void processValidPalindromeEvent(ListNodeAlgorithmContext context, PalindromeAlgorithmTask task) {
        InformationBar informationBar = context.getToolbar().getInformationBar();
        informationBar.changeToInfoIcon();
        informationBar.changeMessage("List is a palindrome");
        stopCheck(context.getToolbar(), task);
    }

    private void processCompareCharacterEvent(PalindromeVisitEvent event, ListNodeAlgorithmContext context, PalindromeAlgorithmTask task) {
        CompareCharacterResult compareResult = event.getCompareCharacterResult();
        var canvas = context.getCanvas();
        visitNode(compareResult.getNodeA(), Color.BLUE, canvas);
        visitNode(compareResult.getNodeB(), Color.BLUE, canvas);

        sleepForOneSecond();

        boolean nodesEqual = compareResult.nodesEqual();
        Color comparisonColor = nodesEqual ? Color.GREEN : Color.RED;
        visitNode(compareResult.getNodeA(), comparisonColor, canvas);
        visitNode(compareResult.getNodeB(), comparisonColor, canvas);
        if (!nodesEqual) {
            stopCheck(context.getToolbar(), task);
        }
    }


}
