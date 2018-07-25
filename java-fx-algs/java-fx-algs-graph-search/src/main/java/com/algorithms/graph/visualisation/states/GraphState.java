package com.algorithms.graph.visualisation.states;

import com.algorithms.graph.visualisation.nodes.GraphNode;
import com.algorithms.graph.visualisation.objects.Point;
import com.algorithms.graph.visualisation.toolbars.GraphToolbar;

/**
 * @author slidem
 */
public interface GraphState {

    /**
     * @param buttonType
     * @param context    graph context holding the currentState
     */
    public void buttonClicked(GraphToolbar.ButtonType buttonType, GraphStateContext context);

    /**
     * @param point
     * @param context graph context holding the currentState
     */
    public void canvasClicked(Point point, GraphStateContext context);

    /**
     * @param graphNode
     * @param context   graph context holding the currentState
     */
    public void nodeClicked(GraphNode graphNode, GraphStateContext context);

}
