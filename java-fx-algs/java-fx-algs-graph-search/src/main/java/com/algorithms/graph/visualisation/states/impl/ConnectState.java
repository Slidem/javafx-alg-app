package com.algorithms.graph.visualisation.states.impl;


import com.algorithms.graph.visualisation.nodes.GraphNode;
import com.algorithms.graph.visualisation.states.GraphStateContext;

/**
 * @author slidem
 */
public class ConnectState extends AbstractGraphState {

    @Override
    public void nodeClicked(GraphNode graphNode, GraphStateContext context) {
        graphNode.select();
        context.changeState(new ConnectToState(graphNode));
        context.getInformationBar().changeMessage("Select a node to connect to");
    }

}
