package com.algorithms.graph.visualisation.factory;

import ami.lightdi.annotations.Component;
import ami.lightdi.annotations.Inject;
import com.algorithms.graph.logic.Node;
import com.algorithms.graphics.canvas.Canvas;
import com.algorithms.graphics.canvas.nodes.CanvasNode;
import com.algorithms.graphics.canvas.nodes.CanvasNodeBuilder;
import com.algorithms.graphics.canvas.nodes.CanvasNodeFactory;
import com.algorithms.utils.geometry.Point;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Mihai Alexandru
 * @date 16.10.2018
 */
@Component
public class GraphNodeFactory implements CanvasNodeFactory<Node<String>> {

    static final double NODE_RADIUS = 25;

    static final Color DEFAULT_NODE_COLOR = Color.GOLD;

    private AtomicInteger nodeIdHolder;

    private GraphNodeLineConnectionHelper lineConnectionHelper;

    @Inject
    public GraphNodeFactory(GraphNodeLineConnectionHelper graphNodeLineConnectionHelper) {
        this.nodeIdHolder = new AtomicInteger(0);
        this.lineConnectionHelper = graphNodeLineConnectionHelper;
    }

    @Override
    public CanvasNode<Node<String>> createNode(Canvas<Node<String>> canvas, Point2D point) {
        String id = String.valueOf(nodeIdHolder.incrementAndGet());
        Node<String> nodeValue = new Node.Builder<String>()
                .withId(id)
                .withValue(id)
                .withConnection(new Node.UndirectedConnection<>())
                .build();

        Circle nodeShape = new Circle();
        nodeShape.setRadius(NODE_RADIUS);

        return new CanvasNodeBuilder<Node<String>>()
                .withPoint(adjustShapePoint(point))
                .withCanvas(canvas)
                .withDefaultColor(DEFAULT_NODE_COLOR)
                .withId(id)
                .withShape(nodeShape)
                .withText(id)
                .withValue(nodeValue)
                .withLineConnectionHelper(lineConnectionHelper)
                .build();
    }

    /**
     * Adjusting point so that the shape will be drawn from the center of the point.
     */
    private Point2D adjustShapePoint(Point2D point){
        double x = point.getX() - NODE_RADIUS;
        double y = point.getY() - NODE_RADIUS;
        return new Point2D(x, y);
    }

}
