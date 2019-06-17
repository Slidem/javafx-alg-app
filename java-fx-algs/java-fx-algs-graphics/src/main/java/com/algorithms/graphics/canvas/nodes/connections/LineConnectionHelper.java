package com.algorithms.graphics.canvas.nodes.connections;


import javafx.geometry.Point2D;

/**
 * @author Alexandru Mihai
 */
public interface LineConnectionHelper {

    Point2D adjustStartingPoint(Point2D fromNodeOrigin);

    Point2D adjustEndPoint(Point2D toNodeOrigin);

}
