package com.algorithms.graphics.canvas.nodes;

import com.algorithms.graphics.canvas.Canvas;
import com.algorithms.utils.geometry.Point;

/**
 * @author Mihai Alexandru
 * @date 11.10.2018
 */
public interface CanvasNodeFactory<T> {

    CanvasNode<T> createNode(Canvas<T> canvas, Point point);

}
