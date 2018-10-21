package com.algorithms.graph.visualisation.states;


import com.algorithms.graph.logic.Node;
import com.algorithms.graph.visualisation.context.GraphSearchAlgorithmContext;
import com.algorithms.graphics.AlgorithmState;
import com.algorithms.graphics.canvas.nodes.CanvasNode;
import com.algorithms.graphics.toolbar.control.ToolbarControl;
import com.algorithms.utils.geometry.Point;

/**
 * @author slidem
 */
public abstract class AbstractGraphState implements AlgorithmState<Node<String>, GraphSearchAlgorithmContext> {

    @Override
    public void controlClicked(ToolbarControl control, GraphSearchAlgorithmContext context) {
        //do nothing
    }

    @Override
    public void canvasClicked(Point point, GraphSearchAlgorithmContext context) {
        //do nothing
    }

    @Override
    public void canvasNodeClicked(CanvasNode<Node<String>> canvasNode, GraphSearchAlgorithmContext context) {
        //do nothing
    }
}
