package com.algorithms.graph.visualisation.states.impl;


import com.algorithms.graph.visualisation.nodes.GraphNode;
import com.algorithms.graph.visualisation.states.GraphStateContext;
import com.algorithms.graph.visualisation.toolbars.GraphToolbar;

public class SearchToState extends AbstractGraphState {

    private final GraphNode from;

    public SearchToState(GraphNode from) {
        this.from = from;
    }

    @Override
    public void nodeClicked(GraphNode graphNode, GraphStateContext context) {
        graphNode.select();
        context.changeState(new StartSearchState(from, graphNode));
        context.getInformationBar().changeMessage("Click on \"Start Search\" to start the graph search.");
        context.getToolbar().changeButtonLabel(GraphToolbar.ButtonType.SEARCH, "Start Search");
    }

}
