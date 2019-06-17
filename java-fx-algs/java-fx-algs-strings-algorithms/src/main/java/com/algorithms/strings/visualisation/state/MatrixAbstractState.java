package com.algorithms.strings.visualisation.state;

import com.algorithms.graphics.AlgorithmState;
import com.algorithms.graphics.canvas.nodes.CanvasNode;
import com.algorithms.strings.visualisation.context.MatrixRotationContext;
import com.algorithms.strings.visualisation.objects.RowColumnHolder;
import com.algorithms.utils.geometry.Point;
import javafx.geometry.Point2D;

/**
 * @author Mihai Alexandru
 * @date 20.10.2018
 */
public abstract class MatrixAbstractState implements AlgorithmState<RowColumnHolder, MatrixRotationContext> {

    @Override
    public final void canvasClicked(Point2D point, MatrixRotationContext context) {
        //-> nothing to do there
    }

    @Override
    public final void canvasNodeClicked(CanvasNode<RowColumnHolder> canvasNode, MatrixRotationContext context) {
        //-> nothing to do here
    }
}
