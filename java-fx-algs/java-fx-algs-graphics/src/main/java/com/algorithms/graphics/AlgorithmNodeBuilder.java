package com.algorithms.graphics;

import com.algorithms.graphics.canvas.Canvas;
import com.algorithms.graphics.toolbar.Toolbar;

/**
 * @author Mihai Alexandru
 * @date 14.10.2018
 */
public class AlgorithmNodeBuilder<T, C extends AlgorithmVisualisationContext<T>> {

    Toolbar toolbar;

    Canvas<T> canvas;

    AlgorithmState<T, C> initialState;

    C context;

    public AlgorithmNodeBuilder<T, C> setToolbar(Toolbar toolbar) {
        this.toolbar = toolbar;
        return this;
    }

    public AlgorithmNodeBuilder<T, C> setCanvas(Canvas<T> canvas) {
        this.canvas = canvas;
        return this;
    }

    public AlgorithmNodeBuilder<T, C> setInitialState(AlgorithmState<T, C> initialState) {
        this.initialState = initialState;
        return this;
    }

    public AlgorithmNodeBuilder<T, C> setContext(C context) {
        this.context = context;
        return this;
    }

    public AlgorithmVisualisationNode<T, C> build() {
        return new AlgorithmVisualisationNode<>(this);
    }


}
