package com.algorithms.graph.visualisation.states.impl;

import com.algorithms.graph.visualisation.states.GraphStateContext;
import com.algorithms.graph.visualisation.toolbars.GraphToolbar;
import com.algorithms.toolbar.information.InformationBar;

import java.util.function.Consumer;

/**
 * @author slidem
 */
public class DefaultState extends AbstractGraphState {

    @Override
    public void buttonClicked(GraphToolbar.ButtonType buttonType, GraphStateContext context) {
        switch (buttonType) {
            case NEW:
                handleCreateNodeState(context);
                break;
            case DELETE:
                handleDeleteNodeState(context);
                break;
            case CONNECT:
                handleConnectNodeState(context);
                break;
            case SEARCH:
                handleSearchNodeState(context);
                break;
        }
    }

    private void handleCreateNodeState(GraphStateContext context) {
        changeInfo("Click on the canvas to create a node", context);
        context.getToolbar().disableAllExcept(GraphToolbar.ButtonType.NEW);
        context.changeState(new CreateState());
    }

    private void handleDeleteNodeState(GraphStateContext context) {
        int nodes = context.getGraphCanvas().getNodeCount();
        if (nodes < 1) {
            displayAttentionMessage("At least one node should be available in order to perform a delete", context);
        } else {
            changeInfo("Click on a node to delete it", context);
            context.getToolbar().disableAllExcept(GraphToolbar.ButtonType.DELETE);
            context.changeState(new DeleteState());
        }
    }

    private void handleConnectNodeState(GraphStateContext context) {
        int nodes = context.getGraphCanvas().getNodeCount();
        if (nodes < 2) {
            displayAttentionMessage("At least two nodes should be available in order to perform a connection", context);
        } else {
            changeInfo("Click on a node to delete it", context);
            context.getToolbar().disableAllExcept(GraphToolbar.ButtonType.CONNECT);
            context.changeState(new ConnectState());
        }
    }

    private void handleSearchNodeState(GraphStateContext context) {
        int nodes = context.getGraphCanvas().getNodeCount();
        if (nodes < 2) {
            displayAttentionMessage("At least two nodes should be available in order to perform a search", context);
        } else {
            context.getInformationBar().changeToInfoIcon();
            context.getInformationBar().changeMessage("Click on a graph node you wish to search from.");
            context.getToolbar().disableAllExcept(GraphToolbar.ButtonType.SEARCH);
            context.changeState(new SearchFromState());
        }
    }

    private void changeInfo(String message, GraphStateContext context) {
        changeMessage(context, message, InformationBar::changeToInfoIcon);
    }

    private void displayAttentionMessage(String message, GraphStateContext context) {
        changeMessage(context, message, InformationBar::changeToAttentionIcon);
    }

    private void changeMessage(GraphStateContext ctx, String message, Consumer<InformationBar> iconChangeConsumer) {
        InformationBar infoBar = ctx.getInformationBar();
        infoBar.changeMessage(message);
        iconChangeConsumer.accept(infoBar);
    }

}
