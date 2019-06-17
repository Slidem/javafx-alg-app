package com.algorithms.graphics.canvas.observers;

import com.algorithms.graphics.canvas.nodes.CanvasNode;
import com.algorithms.utils.geometry.Point;
import javafx.geometry.Point2D;

/**
 * @author Mihai Alexandru
 * @date 10.10.2018
 */
public interface CanvasObserver<T> {

    void nodeClicked(CanvasNode<T> node);

    void canvasClicked(Point2D point);
}
