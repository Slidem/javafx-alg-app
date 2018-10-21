package com.algorithms.graph.visualisation.states;

import com.algorithms.graph.logic.GraphSearch;
import com.algorithms.graph.logic.Node;
import com.algorithms.graph.logic.NodeVisitObserver;
import com.algorithms.graph.visualisation.context.GraphSearchAlgorithmContext;
import com.algorithms.graph.visualisation.controls.ButtonType;
import com.algorithms.graphics.canvas.nodes.CanvasNode;
import com.algorithms.graphics.toolbar.control.ToolbarControl;
import javafx.concurrent.Task;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

import java.util.Iterator;
import java.util.List;

public class StartSearchState extends AbstractGraphState {

    private CanvasNode<Node<String>> from;

    private CanvasNode<Node<String>> to;

    public StartSearchState(CanvasNode<Node<String>> from, CanvasNode<Node<String>> to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public void controlClicked(ToolbarControl control, GraphSearchAlgorithmContext context) {
        changeSearchButtonLabel(context, "Stop search");
        GraphSearch<String> graphSearch = context.getGraphSearchSupplier().apply(from.getValue(), to.getValue());
        var task = createSearchTask(graphSearch);
        task.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                context.getCanvas().getNode(newValue.getId()).ifPresent(n -> n.visit(Color.CADETBLUE));
            } else {
                context.changeState(new DefaultState());
                context.getToolbar().enableAllControls();
                context.getToolbar().getInformationBar().resetToDefault();
                changeSearchButtonLabel(context, "Search");
                graphSearch.getNodePath().ifPresentOrElse(pathIterator -> pathFound(context, pathIterator), () -> noPathFound(context));
                stopSearch(graphSearch, task);
            }
        });
        context.changeState(new StopSearchState(() -> stopSearch(graphSearch, task)));
        new Thread(task).start();
    }

    private Task<Node<String>> createSearchTask(GraphSearch<String> graphSearch) {
        return new Task<Node<String>>() {
            @Override
            protected Node<String> call() throws Exception {
                NodeVisitObserver<String> nodeVisitObserver = n -> {
                    sleepThread();
                    if (!isCancelled()) {
                        updateValue(n);
                    }
                };
                graphSearch.nodeVisitors(List.of(nodeVisitObserver));
                graphSearch.beginSearch();
                return null;
            }
        };
    }

    private void sleepThread() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void changeSearchButtonLabel(GraphSearchAlgorithmContext context, String text) {
        context.getToolbar().getControl(ButtonType.SEARCH.name()).getControlAs(Button.class).setText(text);
    }

    private void pathFound(GraphSearchAlgorithmContext context, Iterator<Node<String>> pathIterator) {
        context.getToolbar().getInformationBar().changeToInfoIcon();
        context.getToolbar().getInformationBar().changeMessage("Path found between node " + from.getId() + " to " + to.getId());
        while (pathIterator.hasNext()) {
            Node<String> n = pathIterator.next();
            context.getCanvas().getNode(n.getId()).ifPresent(node -> node.visit(Color.INDIANRED));
        }
    }

    private void noPathFound(GraphSearchAlgorithmContext context) {
        context.getToolbar().getInformationBar().changeToAttentionIcon();
        context.getToolbar().getInformationBar().changeMessage("NO path found between node " + from.getId() + " to " + to.getId());
    }

    private void stopSearch(GraphSearch<String> gs, Task<Node<String>> task) {
        task.cancel();
        gs.stopSearch();
    }


}
