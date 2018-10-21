package com.algorithms.graph.visualisation.factory;

import ami.lightdi.annotations.Component;
import com.algorithms.graph.logic.GraphSearch;
import com.algorithms.graph.logic.Node;
import com.algorithms.graph.visualisation.context.GraphSearchAlgorithmContext;
import com.algorithms.graph.visualisation.controls.ButtonType;
import com.algorithms.graph.visualisation.states.DefaultState;
import com.algorithms.graphics.AlgorithmNodeBuilder;
import com.algorithms.graphics.AlgorithmVisualisationNode;
import com.algorithms.graphics.canvas.Canvas;
import com.algorithms.graphics.toolbar.Toolbar;
import com.algorithms.graphics.toolbar.ToolbarBuilder;
import com.algorithms.graphics.toolbar.ToolbarControlCustomizer;
import com.algorithms.graphics.toolbar.control.ToolbarControl;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.util.List;
import java.util.function.BiFunction;

/**
 * @author Mihai Alexandru
 * @date 14.10.2018
 */
@Component
public class GraphSearchAlgorithmFactory {

    public AlgorithmVisualisationNode<Node<String>, GraphSearchAlgorithmContext> create(BiFunction<Node<String>, Node<String>, GraphSearch<String>> graphSearchSupplier) {
        Toolbar toolbar = new ToolbarBuilder()
                .withControls(getControls())
                .withToolbarControlCustomizer(getCustomizer())
                .withInitialInfoText("Create nodes to form a graph.")
                .build();
        Canvas<Node<String>> canvas = new Canvas<>();
        GraphNodeFactory graphNodeFactory = new GraphNodeFactory();
        GraphSearchAlgorithmContext context = new GraphSearchAlgorithmContext.Builder()
                .withCanvas(canvas)
                .withToolbar(toolbar)
                .withGraphSearchSupplier(graphSearchSupplier)
                .withCanvasNodeFactory(graphNodeFactory)
                .build();
        return new AlgorithmNodeBuilder<Node<String>, GraphSearchAlgorithmContext>()
                .setToolbar(toolbar)
                .setCanvas(canvas)
                .setContext(context)
                .setInitialState(new DefaultState())
                .build();
    }

    // --------------------------------------------------------------------------------------------

    private List<ToolbarControl> getControls() {
        return List.of(
                ToolbarControl.of(ButtonType.NEW.name(), new Button("Create"), 1),
                ToolbarControl.of(ButtonType.CONNECT.name(), new Button("Connect"), 2),
                ToolbarControl.of(ButtonType.SEARCH.name(), new Button("Search"), 3),
                ToolbarControl.of(ButtonType.DELETE.name(), new Button("Delete"), 4)
        );
    }

    private ToolbarControlCustomizer getCustomizer() {
        return new ToolbarControlCustomizer() {
            @Override
            public void customizeToolbar(Toolbar toolbar) {
                toolbar.setSpacing(5);
                toolbar.setPadding(new Insets(10));
            }

            @Override
            public void customizeControlBar(HBox controlBar) {
                controlBar.setPadding(new Insets(15));
                controlBar.setSpacing(30);
            }

            @Override
            public void customizeControls(HBox controlBar, Toolbar toolbar) {
                //do nothing
            }

            @Override
            public void customizeControlsBehaviour(HBox controlBar, Toolbar toolbar) {
                //do nothing
            }
        };
    }

}
