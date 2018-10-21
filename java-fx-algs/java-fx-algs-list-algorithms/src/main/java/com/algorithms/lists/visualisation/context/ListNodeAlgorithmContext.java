package com.algorithms.lists.visualisation.context;

import com.algorithms.graphics.AlgorithmVisualisationContext;
import com.algorithms.graphics.canvas.Canvas;
import com.algorithms.graphics.toolbar.Toolbar;
import com.algorithms.lists.node.Node;
import com.algorithms.lists.visualisation.factory.ListFactory;

/**
 * @author Mihai Alexandru
 * @date 17.10.2018
 */
public class ListNodeAlgorithmContext extends AlgorithmVisualisationContext<Node<String>> {

    private ListFactory listFactory;

    private ListNodeAlgorithmContext(Builder builder) {
        super(builder.canvas, builder.toolbar);
        this.listFactory = builder.listFactory;
    }

    public ListFactory getListFactory() {
        return listFactory;
    }

    public static final class Builder {

        private ListFactory listFactory;

        private Canvas<Node<String>> canvas;

        private Toolbar toolbar;

        public Builder withListFactory(ListFactory listFactory) {
            this.listFactory = listFactory;
            return this;
        }

        public Builder withCanvas(Canvas<Node<String>> canvas) {
            this.canvas = canvas;
            return this;
        }

        public Builder withToolbar(Toolbar toolbar) {
            this.toolbar = toolbar;
            return this;
        }

        public ListNodeAlgorithmContext build() {
            return new ListNodeAlgorithmContext(this);
        }
    }
}
