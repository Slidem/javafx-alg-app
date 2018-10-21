package com.algorithms.graphics.canvas.nodes;

import com.algorithms.graphics.canvas.Canvas;
import com.algorithms.utils.geometry.Point;
import javafx.scene.Cursor;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

import java.util.HashMap;
import java.util.Map;

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

    private Point point;

    private Color nodeDefaultColor;

    private StackPane shapeAndTextPane;

    private Map<String, Line> connections;

    CanvasNode(CanvasNodeBuilder<T> builder) {
        this.value = builder.value;
        this.id = builder.id;
        this.shape = builder.shape;
        this.point = builder.point;
        this.nodeDefaultColor = builder.nodeDefaultColor;
        this.canvas = builder.canvas;
        this.text = new Text(builder.text);
        this.connections = new HashMap<>();
        setupShape();
    }

    public boolean draw() {
        if (intersectsOtherShapes()) {
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
        Line connectionLine = createLine(this, node);
        this.connections.put(node.getId(), connectionLine);
        node.connections.put(this.getId(), connectionLine);
        canvas.getChildren().add(connectionLine);
        connectionLine.toBack();
    }

    // ------------- getters ----------------------------

    public T getValue() {
        return value;
    }

    public Shape getShape() {
        return shape;
    }

    public Point getPoint() {
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

        this.shapeAndTextPane.setOnMouseDragExited(mouseEvent -> {
            StackPane sp = (StackPane) mouseEvent.getSource();
            sp.getScene().setCursor(Cursor.DEFAULT);
        });
    }

    private Line createLine(CanvasNode<T> connectFrom, CanvasNode<T> connectTo) {
        Line line = new Line();
        line.setStartX(connectFrom.getPoint().getX());
        line.setStartY(connectFrom.getPoint().getY());
        line.setEndX(connectTo.getPoint().getX());
        line.setEndY(connectTo.getPoint().getY());
        return line;
    }

    private boolean intersectsOtherShapes() {
        return canvas.getAllNodes().stream().anyMatch(n -> n.getShape().intersects(this.getShape().getBoundsInParent()));
    }
}
