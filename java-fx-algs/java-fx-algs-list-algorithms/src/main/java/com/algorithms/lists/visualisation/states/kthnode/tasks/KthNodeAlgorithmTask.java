package com.algorithms.lists.visualisation.states.kthnode.tasks;

import com.algorithms.graphics.toolbar.Toolbar;
import com.algorithms.lists.logic.ReturnKthToLast;
import com.algorithms.lists.logic.observers.KthToLastRecursiveNodeObserver;
import com.algorithms.lists.node.Node;
import com.algorithms.lists.visualisation.context.ListNodeAlgorithmContext;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.paint.Color;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.algorithms.lists.logic.ReturnKthToLast.getKthToLastRecursive;

/**
 * @author Mihai Alexandru
 * @date 18.10.2018
 */
public class KthNodeAlgorithmTask extends Task<Void> implements KthToLastRecursiveNodeObserver<String> {

    private Node<String> headNode;

    private int kth;

    private final AtomicBoolean stopped;

    private Runnable algorithmFinishedAction;

    private ListNodeAlgorithmContext listNodeAlgorithmContext;

    public KthNodeAlgorithmTask(Node<String> headNode, int kth, Runnable algorithmFinishedAction, ListNodeAlgorithmContext listNodeAlgorithmContext) {
        this.headNode = headNode;
        this.kth = kth;
        this.stopped = new AtomicBoolean(false);
        this.algorithmFinishedAction = algorithmFinishedAction;
        this.listNodeAlgorithmContext = listNodeAlgorithmContext;
    }

    @Override
    protected Void call() {
        getKthToLastRecursive(headNode, kth, this);
        Platform.runLater(algorithmFinishedAction);
        return null;
    }

    @Override
    public void nodeVisited(ReturnKthToLast.Result<String> result) {
        visitNode(result.getNode(), Color.BLUE);
    }

    @Override
    public void resultObtained(ReturnKthToLast.Result<String> result) {
        visitNode(result.getNode(), Color.RED);
    }

    @Override
    public void finalResultObtained(ReturnKthToLast.Result<String> finalResult) {
        visitNode(finalResult.getNode(), Color.GREEN);
        displayNodeFound(finalResult);
    }

    @Override
    public boolean isStopped() {
        return stopped.get();
    }

    public void stop() {
        this.stopped.set(true);
    }

    private void visitNode(Node<String> node, Color color) {
        sleepOneSecond();
        if (isStopped() || Objects.isNull(node)) {
            return;
        }
        listNodeAlgorithmContext.getCanvas().getNode(node.getId()).ifPresent(n -> Platform.runLater(() -> n.visit(color)));
    }

    private void displayNodeFound(ReturnKthToLast.Result<String> finalResult) {
        if (isStopped()) {
            return;
        }

        Platform.runLater(() -> {
            Toolbar toolbar = listNodeAlgorithmContext.getToolbar();
            toolbar.getInformationBar().changeToInfoIcon();
            toolbar.getInformationBar().changeMessage("Kth node [" + finalResult.getK() + "] found!");
        });
    }

    private void sleepOneSecond() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }


}
