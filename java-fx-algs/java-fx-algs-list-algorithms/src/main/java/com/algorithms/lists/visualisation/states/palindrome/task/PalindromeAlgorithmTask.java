package com.algorithms.lists.visualisation.states.palindrome.task;

import com.algorithms.lists.logic.observers.PalindromeNodeObserver;
import com.algorithms.lists.node.Node;
import com.algorithms.lists.visualisation.context.ListNodeAlgorithmContext;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.paint.Color;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.algorithms.lists.logic.Palindrome.isPalindromeIterative;
import static javafx.scene.paint.Color.*;

/**
 * @author Mihai Alexandru
 * @date 20.10.2018
 */
public class PalindromeAlgorithmTask extends Task<Void> implements PalindromeNodeObserver<String> {

    private final Node<String> headNode;

    private final AtomicBoolean stopped;

    private ListNodeAlgorithmContext context;

    private Runnable algorithmFinishedAction;

    public PalindromeAlgorithmTask(Node<String> headNode, ListNodeAlgorithmContext context, Runnable algorithmFinishedAction) {
        this.algorithmFinishedAction = algorithmFinishedAction;
        this.context = context;
        this.headNode = headNode;
        this.stopped = new AtomicBoolean(false);
    }

    @Override
    protected Void call() {
        isPalindromeIterative(headNode, this);
        Platform.runLater(algorithmFinishedAction);
        return null;
    }


    @Override
    public void runnerVisited(Node<String> runner) {
        visitNode(runner, TURQUOISE);
    }

    @Override
    public void nodeVisited(Node<String> n) {
        visitNode(n, DEEPSKYBLUE);
    }

    @Override
    public void nodeAddedToStack(Node<String> n) {
        visitNode(n, GREY);
    }

    @Override
    public void compareCharactersResult(Node<String> a, Node<String> b, boolean nodesAreEqual) {
        visitNodeNoSleep(a, Color.BLUE);
        visitNodeNoSleep(b, Color.BLUE);

        Color comparisonColor = nodesAreEqual ? GREEN : RED;

        sleepForOneSecond();

        visitNodeNoSleep(a, comparisonColor);
        visitNodeNoSleep(b, comparisonColor);

        if (!nodesAreEqual) {
            Platform.runLater(() -> {
                context.getToolbar().getInformationBar().changeMessage("List is NOT a valid palindrome");
                context.getToolbar().getInformationBar().changeToAttentionIcon();
            });
        }
    }

    @Override
    public void isPalindrome() {
        Platform.runLater(() -> {
            context.getToolbar().getInformationBar().changeToInfoIcon();
            context.getToolbar().getInformationBar().changeMessage("List is a palindrome");
        });
    }

    @Override
    public boolean isStopped() {
        return stopped.get();
    }

    public void stop() {
        this.stopped.set(true);
    }

    private void visitNode(Node<String> node, Color color) {
        if (this.stopped.get() || Objects.isNull(node)) {
            return;
        }
        sleepForOneSecond();
        context.getCanvas().getNode(node.getId()).ifPresent(cn -> Platform.runLater(() -> cn.visit(color)));
    }

    private void visitNodeNoSleep(Node<String> node, Color color){
        if (this.stopped.get() || Objects.isNull(node)) {
            return;
        }
        context.getCanvas().getNode(node.getId()).ifPresent(cn -> Platform.runLater(() -> cn.visit(color)));
    }

    private void sleepForOneSecond() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
