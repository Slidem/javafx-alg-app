package com.algorithms.graph.visualisation.factory;

import ami.lightdi.annotations.Component;
import ami.lightdi.annotations.Scope;
import com.algorithms.graphics.canvas.nodes.connections.LineConnectionHelper;
import javafx.geometry.Point2D;

import java.util.function.DoubleUnaryOperator;

import static com.algorithms.graph.visualisation.factory.GraphNodeFactory.NODE_RADIUS;

/**
 * @author Alexandru Mihai
 */
@Component(scope = Scope.SINGLETON)
public class GraphNodeLineConnectionHelper implements LineConnectionHelper {

    @Override
    public Point2D adjustStartingPoint(Point2D fromNodeOrigin) {
        return adjustPoint(fromNodeOrigin);
    }

    @Override
    public Point2D adjustEndPoint(Point2D toNodeOrigin) {
        return adjustPoint(toNodeOrigin);
    }

    private Point2D adjustPoint(Point2D point2D){
        double x = point2D.getX() + NODE_RADIUS;
        double y = point2D.getY() + NODE_RADIUS;
        return new Point2D(x, y);
    }

}
