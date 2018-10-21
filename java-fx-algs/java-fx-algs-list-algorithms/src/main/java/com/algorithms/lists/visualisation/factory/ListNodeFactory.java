package com.algorithms.lists.visualisation.factory;

import com.algorithms.graphics.canvas.Canvas;
import com.algorithms.graphics.canvas.nodes.CanvasNode;
import com.algorithms.graphics.canvas.nodes.CanvasNodeBuilder;
import com.algorithms.graphics.canvas.nodes.CanvasNodeFactory;
import com.algorithms.lists.node.Node;
import com.algorithms.utils.geometry.Point;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import static com.algorithms.lists.visualisation.objects.Constants.Node.DEFAULT_COLOR;
import static com.algorithms.lists.visualisation.objects.Constants.Node.DEFAULT_SQUARE_SIDE;

/**
 * @author Mihai Alexandru
 * @date 17.10.2018
 */
public class ListNodeFactory implements CanvasNodeFactory<Node<String>> {

    private Node<String> node;

    private ListNodeFactory(Node<String> node) {
        this.node = node;
    }

    public static ListNodeFactory of(Node<String> node) {
        return new ListNodeFactory(node);
    }

    @Override
    public CanvasNode<Node<String>> createNode(Canvas<Node<String>> canvas, Point point) {
        Shape listShape = new Rectangle(DEFAULT_SQUARE_SIDE, DEFAULT_SQUARE_SIDE);
        return new CanvasNodeBuilder<Node<String>>()
                .withValue(node)
                .withText(node.getValue())
                .withShape(listShape)
                .withId(node.getId())
                .withDefaultColor(DEFAULT_COLOR)
                .withPoint(point)
                .withCanvas(canvas)
                .build();
    }
}
