package com.algorithms.graph.visualisation.factory;

import com.algorithms.graph.logic.Node;
import com.algorithms.graphics.canvas.Canvas;
import com.algorithms.graphics.canvas.nodes.CanvasNode;
import com.algorithms.graphics.canvas.nodes.CanvasNodeBuilder;
import com.algorithms.graphics.canvas.nodes.CanvasNodeFactory;
import com.algorithms.utils.geometry.Point;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Mihai Alexandru
 * @date 16.10.2018
 */
public class GraphNodeFactory implements CanvasNodeFactory<Node<String>> {

    private static final double NODE_RADIUS = 25;

    private static final Color DEFAULT_NODE_COLOR = Color.GOLD;

    private AtomicInteger nodeIdHolder;

    public GraphNodeFactory() {
        this.nodeIdHolder = new AtomicInteger(0);
    }

    @Override
    public CanvasNode<Node<String>> createNode(Canvas<Node<String>> canvas, Point point) {
        String id = String.valueOf(nodeIdHolder.incrementAndGet());
        Node<String> nodeValue = new Node.Builder<String>()
                .withId(id)
                .withValue(id)
                .withConnection(new Node.UndirectedConnection<String>())
                .build();

        Circle nodeShape = new Circle();
        nodeShape.setRadius(NODE_RADIUS);

        return new CanvasNodeBuilder<Node<String>>()
                .withPoint(point)
                .withCanvas(canvas)
                .withDefaultColor(DEFAULT_NODE_COLOR)
                .withId(id)
                .withShape(nodeShape)
                .withText(id)
                .withValue(nodeValue)
                .build();
    }
}
