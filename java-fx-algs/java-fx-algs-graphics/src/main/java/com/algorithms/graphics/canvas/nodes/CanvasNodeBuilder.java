package com.algorithms.graphics.canvas.nodes;

import com.algorithms.graphics.canvas.Canvas;
import com.algorithms.utils.geometry.Point;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

/**
 * @author Mihai Alexandru
 * @date 13.10.2018
 */
public class CanvasNodeBuilder<T> {

    T value;

    String id;

    Shape shape;

    String text;

    Canvas<T> canvas;

    Point point;

    Color nodeDefaultColor;

    public CanvasNodeBuilder<T> withId(String id) {
        this.id = id;
        return this;
    }

    public CanvasNodeBuilder<T> withShape(Shape shape) {
        this.shape = shape;
        return this;
    }

    public CanvasNodeBuilder<T> withText(String text) {
        this.text = text;
        return this;
    }

    public CanvasNodeBuilder<T> withCanvas(Canvas<T> canvas) {
        this.canvas = canvas;
        return this;
    }

    public CanvasNodeBuilder<T> withPoint(Point point) {
        this.point = point;
        return this;
    }

    public CanvasNodeBuilder<T> withDefaultColor(Color color) {
        this.nodeDefaultColor = color;
        return this;
    }

    public CanvasNodeBuilder<T> withValue(T value) {
        this.value = value;
        return this;
    }

    public CanvasNode<T> build() {
        return new CanvasNode<>(this);
    }

}
