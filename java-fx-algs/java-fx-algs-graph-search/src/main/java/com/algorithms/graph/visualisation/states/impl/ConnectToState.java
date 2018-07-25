package com.algorithms.graph.visualisation.states.impl;

import com.algorithms.graph.visualisation.nodes.GraphNode;
import com.algorithms.graph.visualisation.states.GraphStateContext;

/**
 * @author slidem
 */
public class ConnectToState extends AbstractGraphState {

    private GraphNode from;

    public ConnectToState(GraphNode from) {
        this.from = from;
    }

    @Override
    public void nodeClicked(GraphNode graphNode, GraphStateContext context) {

        context.getToolbar().enableAll();
        context.getToolbar().getInformationBar().resetToDefault();
        context.getGraphCanvas().connectNode(from, graphNode);

        from.unselect();
        graphNode.unselect();

        context.changeState(new DefaultState());

    }

}
