package com.algorithms.graph.visualisation.states;

import com.algorithms.graph.visualisation.context.GraphSearchAlgorithmContext;
import com.algorithms.graph.visualisation.controls.ButtonType;
import com.algorithms.graphics.toolbar.InformationBar;
import com.algorithms.graphics.toolbar.control.ToolbarControl;

import java.util.function.Consumer;

/**
 * @author slidem
 */
public class DefaultState extends AbstractGraphState {

    @Override
    public void controlClicked(ToolbarControl control, GraphSearchAlgorithmContext context) {
        switch (control.getType()) {
            case "NEW":
                handleCreateNodeState(context);
                break;
            case "DELETE":
                handleDeleteNodeState(context);
                break;
            case "CONNECT":
                handleConnectNodeState(context);
                break;
            case "SEARCH":
                handleSearchNodeState(context);
                break;
        }
    }

    private void handleCreateNodeState(GraphSearchAlgorithmContext context) {
        changeInfo("Click on the canvas to create a node", context);
        context.getToolbar().disableAllExcept(ButtonType.NEW.name());
        context.changeState(new CreateState());
    }

    private void handleDeleteNodeState(GraphSearchAlgorithmContext context) {
        int nodes = context.getCanvas().getAllNodes().size();
        if (nodes < 1) {
            displayAttentionMessage("At least one node should be available in order to perform a delete", context);
        } else {
            changeInfo("Click on a node to delete it", context);
            context.getToolbar().disableAllExcept(ButtonType.DELETE.name());
            context.changeState(new DeleteState());
        }
    }

    private void handleConnectNodeState(GraphSearchAlgorithmContext context) {
        int nodes = context.getCanvas().getAllNodes().size();
        if (nodes < 2) {
            displayAttentionMessage("At least two nodes should be available in order to perform a connection", context);
        } else {
            changeInfo("Click on a node you wish to start a connection from", context);
            context.getToolbar().disableAllExcept(ButtonType.CONNECT.name());
            context.changeState(new ConnectState());
        }
    }

    private void handleSearchNodeState(GraphSearchAlgorithmContext context) {
        int nodes = context.getCanvas().getAllNodes().size();
        if (nodes < 2) {
            displayAttentionMessage("At least two nodes should be available in order to perform a search", context);
        } else {
            context.getToolbar().getInformationBar().changeToInfoIcon();
            context.getToolbar().getInformationBar().changeMessage("Click on a graph node you wish to search from.");
            context.getToolbar().disableAllExcept(ButtonType.SEARCH.name());
            context.changeState(new SearchFromState());
        }
    }

    private void changeInfo(String message, GraphSearchAlgorithmContext context) {
        changeMessage(context, message, InformationBar::changeToInfoIcon);
    }

    private void displayAttentionMessage(String message, GraphSearchAlgorithmContext context) {
        changeMessage(context, message, InformationBar::changeToAttentionIcon);
    }

    private void changeMessage(GraphSearchAlgorithmContext ctx, String message, Consumer<InformationBar> iconChangeConsumer) {
        InformationBar infoBar = ctx.getToolbar().getInformationBar();
        infoBar.changeMessage(message);
        iconChangeConsumer.accept(infoBar);
    }

}
