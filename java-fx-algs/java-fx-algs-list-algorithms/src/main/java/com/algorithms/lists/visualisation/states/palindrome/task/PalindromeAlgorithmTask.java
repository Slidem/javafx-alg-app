package com.algorithms.lists.visualisation.states.palindrome.task;

import com.algorithms.lists.logic.observers.PalindromeNodeObserver;
import com.algorithms.lists.node.Node;
import javafx.concurrent.Task;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.algorithms.lists.logic.Palindrome.isPalindromeIterative;
import static com.algorithms.lists.visualisation.states.palindrome.task.PalindromeAlgorithmTask.PalindromeVisitEventType.*;

/**
 * @author Mihai Alexandru
 * @date 20.10.2018
 */
public class PalindromeAlgorithmTask extends Task<PalindromeAlgorithmTask.PalindromeVisitEvent> implements PalindromeNodeObserver<String> {

    private final Node<String> headNode;

    private final AtomicBoolean stopped;

    public PalindromeAlgorithmTask(Node<String> headNode) {
        this.headNode = headNode;
        this.stopped = new AtomicBoolean(false);
    }

    @Override
    protected PalindromeVisitEvent call() throws Exception {
        isPalindromeIterative(headNode, this);
        return null;
    }


    @Override
    public void runnerVisited(Node<String> runner) {
        fireEvent(new PalindromeVisitEvent(RUNNER_VISITED, runner, null));
    }

    @Override
    public void nodeVisited(Node<String> n) {
        fireEvent(new PalindromeVisitEvent(NODE_VISITED, n, null));
    }

    @Override
    public void nodeAddedToStack(Node<String> n) {
        fireEvent(new PalindromeVisitEvent(NODE_ADDED_TO_STACK, n, null));
    }

    @Override
    public void compareCharactersResult(Node<String> a, Node<String> b, boolean result) {
        fireEvent(new PalindromeVisitEvent(NODE_ADDED_TO_STACK, null, new CompareCharacterResult(a, b, result)));
    }

    @Override
    public void isPalindrome() {
        fireEvent(new PalindromeVisitEvent(VALID_PALINDROME, null, null));
    }

    @Override
    public boolean isStopped() {
        return stopped.get();
    }

    public void stop() {
        this.stopped.set(true);
    }

    private void fireEvent(PalindromeVisitEvent palindromeVisitEvent) {
        if (this.stopped.get()) {
            return;
        }
        updateValue(palindromeVisitEvent);
    }

    public static class PalindromeVisitEvent {

        private PalindromeVisitEventType type;

        private Node<String> node;

        private CompareCharacterResult compareCharacterResult;

        public PalindromeVisitEvent(PalindromeVisitEventType type, Node<String> node, CompareCharacterResult compareCharacterResult) {
            this.type = type;
            this.node = node;
            this.compareCharacterResult = compareCharacterResult;
        }

        public PalindromeVisitEventType getType() {
            return type;
        }

        public Node<String> getNode() {
            return node;
        }

        public CompareCharacterResult getCompareCharacterResult() {
            return compareCharacterResult;
        }
    }

    public static class CompareCharacterResult {

        private Node<String> nodeA;

        private Node<String> nodeB;

        private boolean areEqual;

        public CompareCharacterResult(Node<String> nodeA, Node<String> nodeB, boolean areEqual) {
            this.nodeA = nodeA;
            this.nodeB = nodeB;
            this.areEqual = areEqual;
        }

        public Node<String> getNodeA() {
            return nodeA;
        }

        public Node<String> getNodeB() {
            return nodeB;
        }

        public boolean nodesEqual() {
            return areEqual;
        }
    }

    public enum PalindromeVisitEventType {
        RUNNER_VISITED,
        NODE_VISITED,
        NODE_ADDED_TO_STACK,
        COMPARE_CHARACTER_RESULT,
        VALID_PALINDROME;
    }
}
