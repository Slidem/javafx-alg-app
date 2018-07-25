package com.algorithms.graph.visualisation.states.impl;


import com.algorithms.graph.visualisation.nodes.GraphNode;
import com.algorithms.graph.visualisation.states.GraphStateContext;

/**
 * @author slidem
 */
public class DeleteState extends AbstractGraphState {

    @Override
    public void nodeClicked(GraphNode graphNode, GraphStateContext context) {
        context.getToolbar().getInformationBar().resetToDefault();
        context.getToolbar().enableAll();
        context.getGraphCanvas().deleteNode(graphNode);
        context.changeState(new DefaultState());
    }

}
