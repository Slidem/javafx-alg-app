package com.algorithms.graph.visualisation.nodes;

import com.algorithms.graph.logic.GraphSearch;
import com.algorithms.graph.logic.Node;
import com.algorithms.graph.visualisation.canvas.GraphCanvas;
import com.algorithms.graph.visualisation.objects.Point;
import com.algorithms.graph.visualisation.observers.GraphAlgVisualisationObserver;
import com.algorithms.graph.visualisation.states.GraphState;
import com.algorithms.graph.visualisation.states.GraphStateContext;
import com.algorithms.graph.visualisation.states.impl.DefaultState;
import com.algorithms.graph.visualisation.toolbars.GraphToolbar;
import com.algorithms.toolbar.information.InformationBar;
import javafx.scene.layout.VBox;

import java.util.function.BiFunction;

/**
 * @author slidem
 */
public class GraphAlgorithmVisNode extends VBox implements GraphAlgVisualisationObserver, GraphStateContext {

    private final GraphToolbar graphToolbar;

    private final GraphCanvas graphCanvas;

    private GraphState graphState;

    private final BiFunction<Node<String>, Node<String>, GraphSearch<String>> graphSearchSupplier;


    public GraphAlgorithmVisNode(GraphToolbar graphToolbar, GraphCanvas graphCanvas, BiFunction<Node<String>, Node<String>, GraphSearch<String>> graphSearchSupplier) {
        this.graphToolbar = graphToolbar;
        this.graphCanvas = graphCanvas;
        this.graphSearchSupplier = graphSearchSupplier;
        graphToolbar.setGraphObserver(this);
        graphCanvas.setGraphObserver(this);
        graphState = new DefaultState();
        getChildren().addAll(graphToolbar, graphCanvas);
        setPrefSize(790, 700);
    }

    @Override
    public void graphNodeClicked(GraphNode graphNode) {
        graphState.nodeClicked(graphNode, this);
    }

    @Override
    public void buttonClicked(GraphToolbar.ButtonType type) {
        graphState.buttonClicked(type, this);
    }

    @Override
    public void graphCanvasClicked(Point point) {
        graphState.canvasClicked(point, this);
    }

    @Override
    public void changeState(GraphState newState) {
        graphState = newState;
    }

    @Override
    public GraphCanvas getGraphCanvas() {
        return graphCanvas;
    }

    @Override
    public GraphToolbar getToolbar() {
        return graphToolbar;
    }

    @Override
    public InformationBar getInformationBar() {
        return graphToolbar.getInformationBar();
    }

    @Override
    public BiFunction<Node<String>, Node<String>, GraphSearch<String>> getSearchSupplier() {
        return graphSearchSupplier;
    }

}
