package com.algorithms.graphics;

import com.algorithms.graphics.canvas.nodes.CanvasNode;
import com.algorithms.graphics.canvas.observers.CanvasObserver;
import com.algorithms.graphics.constants.GraphicsConstants;
import com.algorithms.graphics.toolbar.control.ToolbarControl;
import com.algorithms.graphics.toolbar.observers.ToolbarObserver;
import com.algorithms.utils.geometry.Point;
import javafx.scene.layout.VBox;

/**
 * @author Mihai Alexandru
 * @date 13.10.2018
 */
public class AlgorithmVisualisationNode<T, C extends AlgorithmVisualisationContext<T>> extends VBox implements CanvasObserver<T>, ToolbarObserver {

    private AlgorithmState<T, C> algorithmState;

    private C context;

    AlgorithmVisualisationNode(AlgorithmNodeBuilder<T, C> builder) {
        this.algorithmState = builder.initialState;
        this.context = builder.context;
        builder.toolbar.setToolbarObserver(this);
        builder.canvas.setCanvasObserver(this);
        this.context.setChangeStateConsumer(s -> this.algorithmState = (AlgorithmState<T, C>) s);
        getChildren().addAll(builder.toolbar, builder.canvas);
        setPrefSize(GraphicsConstants.AlgorithmVisualisation.PREF_WIDTH, GraphicsConstants.AlgorithmVisualisation.PREF_HEIGHT);
    }

    @Override
    public void nodeClicked(CanvasNode<T> node) {
        algorithmState.canvasNodeClicked(node, context);
    }

    @Override
    public void canvasClicked(Point point) {
        algorithmState.canvasClicked(point, context);
    }

    @Override
    public void controlClicked(ToolbarControl toolbarControl) {
        algorithmState.controlClicked(toolbarControl, context);
    }

    public void changeState(AlgorithmState<T, C> state) {
        this.algorithmState = state;
    }
}
