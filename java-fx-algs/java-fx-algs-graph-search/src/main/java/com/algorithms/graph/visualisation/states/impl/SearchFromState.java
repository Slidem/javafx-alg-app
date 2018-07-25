package com.algorithms.graph.visualisation.states.impl;

import com.algorithms.graph.visualisation.nodes.GraphNode;
import com.algorithms.graph.visualisation.states.GraphStateContext;

/**
 * @author slidem
 */
public class SearchFromState extends AbstractGraphState {

    @Override
    public void nodeClicked(GraphNode graphNode, GraphStateContext context) {
        graphNode.select();
        context.changeState(new SearchToState(graphNode));
        context.getInformationBar().changeMessage("Click on a target node you wish to search to");
    }

}
