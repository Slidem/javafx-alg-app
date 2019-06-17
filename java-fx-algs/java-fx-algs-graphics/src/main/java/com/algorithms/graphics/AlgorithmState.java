package com.algorithms.graphics;

import com.algorithms.graphics.canvas.nodes.CanvasNode;
import com.algorithms.graphics.toolbar.control.ToolbarControl;
import com.algorithms.utils.geometry.Point;
import javafx.geometry.Point2D;

/**
 * @author Mihai Alexandru
 * @date 13.10.2018
 */
public interface AlgorithmState<V, C extends AlgorithmVisualisationContext<V>> {

    void controlClicked(ToolbarControl control, C context);

    void canvasClicked(Point2D point, C context);

    void canvasNodeClicked(CanvasNode<V> canvasNode, C context);

}
