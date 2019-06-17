package com.algorithms.lists.visualisation.states.palindrome;

import com.algorithms.graphics.toolbar.InformationBar;
import com.algorithms.graphics.toolbar.Toolbar;
import com.algorithms.graphics.toolbar.control.ToolbarControl;
import com.algorithms.lists.node.Node;
import com.algorithms.lists.visualisation.context.ListNodeAlgorithmContext;
import com.algorithms.lists.visualisation.objects.PalindromeControlTypes;
import com.algorithms.lists.visualisation.states.ListAbstractState;
import com.algorithms.lists.visualisation.states.palindrome.task.PalindromeAlgorithmTask;
import javafx.scene.control.Button;

import static com.algorithms.lists.visualisation.objects.PalindromeControlTypes.CHECK_PALINDROME;
import static com.algorithms.lists.visualisation.objects.PalindromeControlTypes.CLEAR;
import static com.algorithms.lists.visualisation.objects.PalindromeNodeControlUtils.*;


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

        context.getCanvas().deleteAllNodes();
        context.changeState(new PalindromeGenerateListState());
    }

    private void executePalindromeCheck(ToolbarControl control, ListNodeAlgorithmContext context) {
        disableSearch(context.getToolbar(), false);
        control.getControlAs(Button.class).setText("Stop");
        var task = createTask(context);
        context.changeState(new PalindromeStopCheckState(() -> {
            stopTask(task);
            stopCheck(context.getToolbar());
        }, head));
        new Thread(task).start();
    }

    private PalindromeAlgorithmTask createTask(ListNodeAlgorithmContext context) {
        return new PalindromeAlgorithmTask(head, context, () -> {
            stopCheck(context.getToolbar());
            context.changeState(this);
        });
    }

    private void stopCheck(Toolbar toolbar) {
        disableGenerateList(toolbar, true);
        disableSearch(toolbar, true);
        disableClearList(toolbar, false);
        toolbar.getControl(CHECK_PALINDROME.name()).getControlAs(Button.class).setText("Check if palindrome");
    }

    private void stopTask(PalindromeAlgorithmTask task) {
        task.cancel();
        task.stop();
    }


}
