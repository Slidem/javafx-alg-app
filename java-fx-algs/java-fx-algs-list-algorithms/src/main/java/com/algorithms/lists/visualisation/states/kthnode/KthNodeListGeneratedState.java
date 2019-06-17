package com.algorithms.lists.visualisation.states.kthnode;

import com.algorithms.graphics.canvas.nodes.CanvasNode;
import com.algorithms.graphics.toolbar.Toolbar;
import com.algorithms.graphics.toolbar.control.ToolbarControl;
import com.algorithms.lists.node.Node;
import com.algorithms.lists.visualisation.context.ListNodeAlgorithmContext;
import com.algorithms.lists.visualisation.objects.KthNodeControlTypes;
import com.algorithms.lists.visualisation.objects.KthNodeControlUtils;
import com.algorithms.lists.visualisation.states.ListAbstractState;
import com.algorithms.lists.visualisation.states.kthnode.tasks.KthNodeAlgorithmTask;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import static com.algorithms.lists.visualisation.objects.KthNodeControlTypes.*;
import static com.algorithms.lists.visualisation.objects.KthNodeControlUtils.*;

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
        KthNodeControlUtils.disableSearch(toolbar, true);
        KthNodeControlUtils.disableClearList(toolbar, true);
        KthNodeControlUtils.disableGenerateList(toolbar, false);
        context.changeState(new KthNodeGenerateListState());
    }

    private void executeStartSearch(ToolbarControl control, ListNodeAlgorithmContext context) {
        int kthNode = getKthElementSearch(context);

        Toolbar toolbar = context.getToolbar();
        disableSearch(toolbar, false);
        control.getControlAs(Button.class).setText("Stop");

        var task = new KthNodeAlgorithmTask(headNode, kthNode, () -> {
            stopCheck(context);
            context.changeState(this);
        }, context);

        context.changeState(new KthNodeStopSearchState(() -> {
            stopTask(task);
            stopCheck(context);
            context.getCanvas().getAllNodes().forEach(CanvasNode::reset);
        }, headNode));

        new Thread(task).start();
    }

    private boolean clicked(KthNodeControlTypes type, ToolbarControl control) {
        return type.name().equals(control.getType());
    }

    private int getKthElementSearch(ListNodeAlgorithmContext context) {
        return (int) context.getToolbar().getControl(KTH_ELEMENT_SEARCH.name()).getControlAs(ComboBox.class).getValue();
    }

    private void stopCheck(ListNodeAlgorithmContext context) {
        Toolbar toolbar = context.getToolbar();
        disableGenerateList(toolbar, true);
        disableClearList(toolbar, false);
        disableSearch(toolbar, true);
        toolbar.getControl(SEARCH_BUTTON.name()).getControlAs(Button.class).setText("Search");

    }

    private void stopTask(KthNodeAlgorithmTask task) {
        task.cancel(true);
        task.stop();
    }

}
