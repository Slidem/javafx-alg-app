package com.algorithms.graphics.canvas;

import com.algorithms.graphics.canvas.nodes.CanvasNode;
import com.algorithms.graphics.canvas.nodes.CanvasNodeFactory;
import com.algorithms.graphics.canvas.observers.CanvasObserver;
import com.algorithms.graphics.constants.GraphicsConstants;
import com.algorithms.utils.geometry.Point;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static java.util.Objects.requireNonNull;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toUnmodifiableList;

/**
 * @author Mihai Alexandru
 * @date 10.10.2018
 */
public class Canvas<T> extends Pane {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final Map<String, CanvasNode<T>> canvasNodes;

    private CanvasObserver<T> canvasObserver;

    public Canvas() {
        this.canvasNodes = new HashMap<>();
        setupCanvas();
    }

    public void drawNode(Point2D point, CanvasNodeFactory<T> canvasNodeFactory) {
        var canvasNode = canvasNodeFactory.createNode(this, point);
        boolean drawn = canvasNode.draw();
        if (drawn) {
            canvasNodes.put(canvasNode.getId(), canvasNode);
        } else {
            logger.debug("Canvas node was not drawn. It may intersect other nodes on the canvas");
        }
    }

    public void deleteNode(CanvasNode<T> canvasNode) {
        requireNonNull(canvasNode, "canvasNode cannot be null.");
        if (canvasNodes.size() == 0) {
            throw new IllegalStateException("Cannot delete anymore nodes. Canvas contains 0 nodes");
        }
        ofNullable(canvasNodes.remove(canvasNode.getId())).ifPresent(CanvasNode::delete);
    }

    public void deleteAllNodes() {
        canvasNodes.values().forEach(CanvasNode::delete);
        canvasNodes.clear();
    }

    public Optional<CanvasNode<T>> getNode(String id) {
        return ofNullable(canvasNodes.get(id));
    }

    public Collection<CanvasNode<T>> getAllNodes() {
        return canvasNodes.values().stream().collect(toUnmodifiableList());
    }

    public void setCanvasObserver(CanvasObserver<T> canvasObserver) {
        this.canvasObserver = canvasObserver;
    }

    private void setupCanvas() {
        setBackground(GraphicsConstants.Canvas.BACKGROUND);
        setMinHeight(GraphicsConstants.Canvas.MIN_HEIGHT);
        setMaxHeight(GraphicsConstants.Canvas.MAX_HEIGHT);
        setBorder(GraphicsConstants.Canvas.BORDER);
        setOnMouseClicked(getCanvasClickEventHandler());
    }

    private EventHandler<MouseEvent> getCanvasClickEventHandler() {
        return event -> {
            Point2D point = new Point2D(event.getX(), event.getY());
            nodeClicked(point).ifPresentOrElse(this::nodeClicked, ()->canvasClicked(point));
        };
    }

    private Optional<CanvasNode<T>> nodeClicked(Point2D point) {
        return canvasNodes.values()
                .stream()
                .filter(canvasNode -> canvasNode.contains(point))
                .findFirst();
    }

    private void nodeClicked(CanvasNode<T> node) {
        ofNullable(canvasObserver).ifPresent(o -> o.nodeClicked(node));
    }

    private void canvasClicked(Point2D point) {
        ofNullable(canvasObserver).ifPresent(o -> o.canvasClicked(point));
    }

}
