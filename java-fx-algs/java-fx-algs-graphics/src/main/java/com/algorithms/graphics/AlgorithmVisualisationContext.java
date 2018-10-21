package com.algorithms.graphics;

import com.algorithms.graphics.canvas.Canvas;
import com.algorithms.graphics.toolbar.Toolbar;

import java.util.function.Consumer;

/**
 * @author Mihai Alexandru
 * @date 14.10.2018
 */
public abstract class AlgorithmVisualisationContext<T> {

    protected Canvas<T> canvas;

    protected Toolbar toolbar;

    private Consumer<AlgorithmState<T, ? extends AlgorithmVisualisationContext<T>>> changeStateConsumer;

    public AlgorithmVisualisationContext(Canvas<T> canvas, Toolbar toolbar) {
        this.canvas = canvas;
        this.toolbar = toolbar;
    }

    public Canvas<T> getCanvas() {
        return canvas;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public void changeState(AlgorithmState<T, ? extends AlgorithmVisualisationContext<T>> state) {
        changeStateConsumer.accept(state);
    }

    void setChangeStateConsumer(Consumer<AlgorithmState<T, ? extends AlgorithmVisualisationContext<T>>> changeStateConsumer) {
        this.changeStateConsumer = changeStateConsumer;
    }
}
