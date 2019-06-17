package com.algorithms.graphics.canvas.nodes;

import com.algorithms.graphics.canvas.Canvas;
import com.algorithms.graphics.canvas.nodes.connections.LineConnectionHelper;
import com.algorithms.utils.geometry.Point;
import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;
import javafx.scene.Cursor;
import javafx.scene.effect.BlendMode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Mihai Alexandru
 * @date 09.10.2018
 */
public class CanvasNode<T> {

    private T value;

    private String id;

    private Shape shape;

    private Text text;

    private Canvas<T> canvas;

    private Point2D point;

    private Color nodeDefaultColor;

    private StackPane shapeAndTextPane;

    private LineConnectionHelper lineConnectionHelper;

    private Map<String, Line> connections;

    private boolean allowIntersections;

    CanvasNode(CanvasNodeBuilder<T> builder) {
        this.value = builder.value;
        this.id = builder.id;
        this.shape = builder.shape;
        this.point = builder.point;
        this.nodeDefaultColor = builder.nodeDefaultColor;
        this.canvas = builder.canvas;
        this.text = new Text(builder.text);
        this.connections = new HashMap<>();
        this.lineConnectionHelper = builder.lineConnectionHelper;
        this.allowIntersections = builder.allowIntersections;
        setupShape();
    }

    public boolean draw() {
        if (intersectsOtherShapes() && !allowIntersections) {
            return false;
        }
        canvas.getChildren().add(shapeAndTextPane);
        return true;
    }

    public void visit(Color color) {
        shape.setFill(color);
    }

    public void select(Color color) {
        visit(color);
        shape.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 10, 0, 0, 0);");
    }

    public void changeNodeText(String newText) {
        text.setText(newText);
    }

    public void reset() {
        shape.setFill(nodeDefaultColor);
        shape.setStyle("");
    }

    public void delete() {
        canvas.getChildren().removeAll(shapeAndTextPane);
        canvas.getChildren().removeAll(connections.values());
        connections.clear();
    }

    public void addConnection(CanvasNode<T> node) {
        if (connections.containsKey(node.id)) {
            return;
        }
        Line connectionLine = createLine(this, node);
        this.connections.put(node.id, connectionLine);
        node.connections.put(this.id, connectionLine);
        canvas.getChildren().add(connectionLine);
        connectionLine.toBack();
    }

    public boolean contains(Point2D point2D) {
        return shapeAndTextPane.getBoundsInParent().contains(point2D);
    }

    // ------------- getters ----------------------------

    public T getValue() {
        return value;
    }

    public Shape getShape() {
        return shape;
    }

    public Point2D getPoint() {
        return point;
    }

    public String getId() {
        return id;
    }

    // ----------------------------------------------------

    private void setupShape() {
        this.shape.setFill(nodeDefaultColor);
        this.shape.setStroke(Color.BLACK);
        this.shapeAndTextPane = new StackPane(this.shape, this.text);
        this.shapeAndTextPane.setLayoutX(this.point.getX());
        this.shapeAndTextPane.setLayoutY(this.point.getY());

        this.shapeAndTextPane.setOnMouseEntered(mouseEvent -> {
            StackPane sp = (StackPane) mouseEvent.getSource();
            sp.getScene().setCursor(Cursor.HAND);
        });

        this.shapeAndTextPane.setOnMouseExited(mouseEvent -> {
            StackPane sp = (StackPane) mouseEvent.getSource();
            sp.getScene().setCursor(Cursor.DEFAULT);
        });
    }

    private Line createLine(CanvasNode<T> connectFrom, CanvasNode<T> connectTo) {
        Line line = new Line();
        Point2D start = connectFrom.getPoint();
        Point2D end = connectTo.getPoint();
        if (Objects.nonNull(lineConnectionHelper)) {
            start = lineConnectionHelper.adjustStartingPoint(start);
            end = lineConnectionHelper.adjustEndPoint(end);
        }
        line.setStartX(start.getX());
        line.setStartY(start.getY());
        line.setEndX(end.getX());
        line.setEndY(end.getY());
        return line;
    }

    private boolean intersectsOtherShapes() {
        return canvas.getAllNodes().stream().anyMatch(n -> n.shapeAndTextPane.getBoundsInParent().intersects(this.shapeAndTextPane.getBoundsInParent()));
    }
}
