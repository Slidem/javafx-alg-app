package com.algorithms.graph.visualisation.context;

import com.algorithms.graph.logic.GraphSearch;
import com.algorithms.graph.logic.Node;
import com.algorithms.graphics.AlgorithmVisualisationContext;
import com.algorithms.graphics.canvas.Canvas;
import com.algorithms.graphics.canvas.nodes.CanvasNodeFactory;
import com.algorithms.graphics.toolbar.Toolbar;

import java.util.function.BiFunction;

/**
 * @author Mihai Alexandru
 * @date 14.10.2018
 */
public class GraphSearchAlgorithmContext extends AlgorithmVisualisationContext<Node<String>> {

    private BiFunction<Node<String>, Node<String>, GraphSearch<String>> graphSearchSupplier;

    private CanvasNodeFactory<Node<String>> canvasNodeFactory;

    private GraphSearchAlgorithmContext(Builder builder) {
        super(builder.canvas, builder.toolbar);
        this.graphSearchSupplier = builder.graphSearchSupplier;
        this.canvasNodeFactory = builder.canvasNodeFactory;
    }

    public BiFunction<Node<String>, Node<String>, GraphSearch<String>> getGraphSearchSupplier() {
        return graphSearchSupplier;
    }

    public CanvasNodeFactory<Node<String>> getCanvasNodeFactory() {
        return canvasNodeFactory;
    }

    public static class Builder {

        private Canvas<Node<String>> canvas;

        private Toolbar toolbar;

        private BiFunction<Node<String>, Node<String>, GraphSearch<String>> graphSearchSupplier;

        private CanvasNodeFactory<Node<String>> canvasNodeFactory;

        public Builder withCanvas(Canvas<Node<String>> canvas) {
            this.canvas = canvas;
            return this;
        }

        public Builder withToolbar(Toolbar toolbar) {
            this.toolbar = toolbar;
            return this;
        }

        public Builder withGraphSearchSupplier(BiFunction<Node<String>, Node<String>, GraphSearch<String>> graphSearchSupplier) {
            this.graphSearchSupplier = graphSearchSupplier;
            return this;
        }

        public Builder withCanvasNodeFactory(CanvasNodeFactory<Node<String>> canvasNodeFactory) {
            this.canvasNodeFactory = canvasNodeFactory;
            return this;
        }

        public GraphSearchAlgorithmContext build() {
            return new GraphSearchAlgorithmContext(this);
        }


    }
}
