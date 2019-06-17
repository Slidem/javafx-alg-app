package com.algorithms.lists.visualisation.states;

import com.algorithms.graphics.AlgorithmState;
import com.algorithms.graphics.canvas.nodes.CanvasNode;
import com.algorithms.graphics.toolbar.control.ToolbarControl;
import com.algorithms.lists.node.Node;
import com.algorithms.lists.visualisation.context.ListNodeAlgorithmContext;
import com.algorithms.utils.geometry.Point;
import javafx.geometry.Point2D;

/**
 * @author Mihai Alexandru
 * @date 17.10.2018
 */
public abstract class ListAbstractState implements AlgorithmState<Node<String>, ListNodeAlgorithmContext> {

    @Override
    public void controlClicked(ToolbarControl control, ListNodeAlgorithmContext context) {
        //do nothing
    }

    @Override
    public final void canvasClicked(Point2D point, ListNodeAlgorithmContext context) {
        //do nothing -> ALWAYS
    }

    @Override
    public final void canvasNodeClicked(CanvasNode<Node<String>> canvasNode, ListNodeAlgorithmContext context) {
        //do nothing -> ALWAYS
    }
}
