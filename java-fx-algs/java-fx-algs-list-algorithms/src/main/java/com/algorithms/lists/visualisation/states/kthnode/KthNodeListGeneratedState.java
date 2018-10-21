package com.algorithms.lists.visualisation.states.kthnode;

import com.algorithms.graphics.canvas.Canvas;
import com.algorithms.graphics.canvas.nodes.CanvasNode;
import com.algorithms.graphics.toolbar.Toolbar;
import com.algorithms.graphics.toolbar.control.ToolbarControl;
import com.algorithms.lists.node.Node;
import com.algorithms.lists.visualisation.context.ListNodeAlgorithmContext;
import com.algorithms.lists.visualisation.objects.KthNodeControlTypes;
import com.algorithms.lists.visualisation.states.ListAbstractState;
import com.algorithms.lists.visualisation.states.kthnode.tasks.KthNodeAlgorithmTask;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;

import static com.algorithms.lists.visualisation.objects.KthNodeControlTypes.*;
import static com.algorithms.lists.visualisation.objects.KthNodeControlUtils.*;
import static com.algorithms.lists.visualisation.states.kthnode.tasks.KthNodeAlgorithmTask.KthNodeVisitEventType.*;
import static java.util.Objects.isNull;

/**
 * At this point the user generated the list.
 * User should have 2 choices:
 * - Start search for the kth node.
 * - Clear generated list and draw another one.
 *
 * @author Mihai Alexandru
 * @date 17.10.2018
 */
public class KthNodeListGeneratedState extends ListAbstractState {

    private Node<String> headNode;

    public KthNodeListGeneratedState(Node<String> headNode) {
        this.headNode = headNode;
    }

    @Override
    public void controlClicked(ToolbarControl control, ListNodeAlgorithmContext context) {
        Toolbar toolbar = context.getToolbar();
        disableGenerateList(toolbar, true);
        disableClearList(toolbar, true);
        if (clicked(CLEAR_BUTTON, control)) {
            executeClearList(context);
        } else if (clicked(SEARCH_BUTTON, control)) {
            executeStartSearch(control, context);
        }
    }

    private void executeClearList(ListNodeAlgorithmContext context) {
        context.getCanvas().deleteAllNodes();
        Toolbar toolbar = context.getToolbar();
        disableSearch(toolbar, true);
        context.changeState(new KthNodeGenerateListState());
    }

    private void executeStartSearch(ToolbarControl control, ListNodeAlgorithmContext context) {
        int kthNode = getKthElementSearch(context);

        Toolbar toolbar = context.getToolbar();
        disableSearch(toolbar, false);
        control.getControlAs(Button.class).setText("Stop");

        var task = new KthNodeAlgorithmTask(headNode, kthNode);
        task.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            getSearchTaskListener(kthNode, context, task, newValue);
        });
        context.changeState(new KthNodeStopSearchState(() -> stop(task, toolbar, context.getCanvas())));
        new Thread(task).start();
    }

    private void getSearchTaskListener(int kthNode, ListNodeAlgorithmContext context, KthNodeAlgorithmTask task, KthNodeAlgorithmTask.KthNodeVisitEvent newValue) {
        if (isNull(newValue)) {
            return;
        }
        Toolbar toolbar = context.getToolbar();
        var canvas = context.getCanvas();
        sleepOneSecond();
        if (newValue.getType() == NODE_VISITED) {
            visitNode(newValue, Color.BLUE, canvas);
        }
        if (newValue.getType() == RESULT_OBTAINED) {
            visitNode(newValue, Color.RED, canvas);
        }
        if (newValue.getType() == FINAL_RESULT_OBTAINED) {
            visitNode(newValue, Color.GREEN, canvas);
            toolbar.getInformationBar().changeToInfoIcon();
            toolbar.getInformationBar().changeMessage("Kth node [" + kthNode + "] found!");
            stop(task, toolbar, canvas);
            context.changeState(new KthNodeGenerateListState());
        }
    }

    private void visitNode(KthNodeAlgorithmTask.KthNodeVisitEvent newValue, Color color, Canvas<Node<String>> canvas) {
        var node = canvas.getNode(newValue.getResult().getNode().getId());
        node.ifPresent(n -> n.visit(color));
    }


    private boolean clicked(KthNodeControlTypes type, ToolbarControl control) {
        return type.name().equals(control.getType());
    }

    private int getKthElementSearch(ListNodeAlgorithmContext context) {
        return (int) context.getToolbar().getControl(KTH_ELEMENT_SEARCH.name()).getControlAs(ComboBox.class).getValue();
    }

    private void sleepOneSecond() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void stop(KthNodeAlgorithmTask task, Toolbar toolbar, Canvas<Node<String>> canvas) {
        disableGenerateList(toolbar, true);
        disableClearList(toolbar, false);
        disableSearch(toolbar, true);
        toolbar.getControl(SEARCH_BUTTON.name()).getControlAs(Button.class).setText("Search");
        canvas.getAllNodes().forEach(CanvasNode::reset);
        task.cancel(true);
        task.stop();
    }


}
