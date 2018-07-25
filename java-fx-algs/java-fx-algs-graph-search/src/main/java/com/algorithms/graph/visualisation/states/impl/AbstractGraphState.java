package com.algorithms.graph.visualisation.states.impl;


import com.algorithms.graph.visualisation.nodes.GraphNode;
import com.algorithms.graph.visualisation.objects.Point;
import com.algorithms.graph.visualisation.states.GraphState;
import com.algorithms.graph.visualisation.states.GraphStateContext;
import com.algorithms.graph.visualisation.toolbars.GraphToolbar;

/**
 * @author slidem
 */
public abstract class AbstractGraphState implements GraphState {

    @Override
    public void buttonClicked(GraphToolbar.ButtonType buttonType, GraphStateContext context) {
    }

    @Override
    public void canvasClicked(Point point, GraphStateContext context) {
    }

    @Override
    public void nodeClicked(GraphNode graphNode, GraphStateContext context) {
    }

}
