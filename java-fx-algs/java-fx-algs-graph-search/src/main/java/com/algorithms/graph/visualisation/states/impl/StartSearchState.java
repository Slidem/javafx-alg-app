package com.algorithms.graph.visualisation.states.impl;

import com.algorithms.graph.logic.GraphSearch;
import com.algorithms.graph.logic.Node;
import com.algorithms.graph.logic.NodeVisitObserver;
import com.algorithms.graph.visualisation.nodes.GraphNode;
import com.algorithms.graph.visualisation.states.GraphStateContext;
import com.algorithms.graph.visualisation.toolbars.GraphToolbar;
import javafx.concurrent.Task;

import java.util.List;

public class StartSearchState extends AbstractGraphState {

    private GraphNode from;

    private GraphNode to;

    public StartSearchState(GraphNode from, GraphNode to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public void buttonClicked(GraphToolbar.ButtonType buttonType, GraphStateContext context) {
        context.getToolbar().changeButtonLabel(GraphToolbar.ButtonType.SEARCH, "Stop search");
        GraphSearch<String> gs = context.getSearchSupplier().apply(from.getNode(), to.getNode());

        var task = new Task<Node<String>>() {

            @Override
            protected Node<String> call() throws Exception {
                NodeVisitObserver<String> nodeVisitObserver = n -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    if (!isCancelled()) {
                        updateValue(n);
                    }
                };

                gs.nodeVisitors(List.of(nodeVisitObserver));
                gs.beginSearch();
                return null;
            }
        };

        task.valueProperty().addListener((observebale, oldValue, newValue) -> {
            if (newValue != null) {
                GraphNode gn = context.getGraphCanvas().getFromAlgNode(newValue);
                gn.visit();
            } else {
                context.changeState(new DefaultState());
                context.getToolbar().enableAll();
                context.getInformationBar().resetToDefault();
                context.getToolbar().changeButtonLabel(GraphToolbar.ButtonType.SEARCH, "Search");
                gs.getNodePath().ifPresentOrElse(pathIterator -> {
                    context.getInformationBar().changeToInfoIcon();
                    context.getInformationBar().changeMessage("Path found between node " + from.getId() + " to " + to.getId());
                    while (pathIterator.hasNext()) {
                        Node<String> n = pathIterator.next();
                        context.getGraphCanvas().getFromAlgNode(n).setAsTarget();
                    }
                }, () -> {
                    context.getInformationBar().changeToAttentionIcon();
                    context.getInformationBar().changeMessage("NO path found between node " + from.getId() + " to " + to.getId());
                });
                stopSearch(gs, task);
            }
        });

        context.changeState(new StopSearchState(() -> {
            stopSearch(gs, task);
        }));

        new Thread(task).start();
    }

    private void stopSearch(GraphSearch<String> gs, Task<Node<String>> task) {
        task.cancel();
        gs.stopSearch();
    }


}
