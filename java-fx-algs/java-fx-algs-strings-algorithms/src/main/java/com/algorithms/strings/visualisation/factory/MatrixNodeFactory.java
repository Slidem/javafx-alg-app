package com.algorithms.strings.visualisation.factory;

import com.algorithms.graphics.canvas.Canvas;
import com.algorithms.graphics.canvas.nodes.CanvasNode;
import com.algorithms.graphics.canvas.nodes.CanvasNodeBuilder;
import com.algorithms.graphics.canvas.nodes.CanvasNodeFactory;
import com.algorithms.strings.visualisation.objects.RowColumnHolder;
import com.algorithms.strings.visualisation.utils.MatrixIdComputer;
import com.algorithms.utils.geometry.Point;
import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;

import static com.algorithms.strings.visualisation.objects.Constants.Matrix.DEFAULT_NODE_COLOR;
import static com.algorithms.strings.visualisation.objects.Constants.Matrix.DEFAULT_SQUARE_SIDE;
import static java.util.Objects.requireNonNull;

/**
 * @author Mihai Alexandru
 * @date 20.10.2018
 */
public class MatrixNodeFactory implements CanvasNodeFactory<RowColumnHolder> {

    private final RowColumnHolder rowColumnHolder;

    public MatrixNodeFactory(RowColumnHolder rowColumnHolder) {
        this.rowColumnHolder = requireNonNull(rowColumnHolder);
    }

    @Override
    public CanvasNode<RowColumnHolder> createNode(Canvas<RowColumnHolder> canvas, Point2D point) {
        String id = MatrixIdComputer.create(rowColumnHolder);
        return new CanvasNodeBuilder<RowColumnHolder>()
                .withPoint(point)
                .withCanvas(canvas)
                .withShape(new Rectangle(DEFAULT_SQUARE_SIDE, DEFAULT_SQUARE_SIDE))
                .withDefaultColor(DEFAULT_NODE_COLOR)
                .withValue(rowColumnHolder)
                .withAllowIntersections(true)
                .withId(id)
                .withText(id)
                .build();
    }

}
