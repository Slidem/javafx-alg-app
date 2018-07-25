package com.algorithms.graph.visualisation.states;

import com.algorithms.graph.logic.GraphSearch;
import com.algorithms.graph.logic.Node;
import com.algorithms.graph.visualisation.canvas.GraphCanvas;
import com.algorithms.graph.visualisation.toolbars.GraphToolbar;
import com.algorithms.toolbar.information.InformationBar;

import java.util.function.BiFunction;

/**
 * @author slidem
 */
public interface GraphStateContext {

    public void changeState(GraphState newState);

    public GraphCanvas getGraphCanvas();

    public GraphToolbar getToolbar();

    public InformationBar getInformationBar();

    public BiFunction<Node<String>, Node<String>, GraphSearch<String>> getSearchSupplier();

}
