package com.algorithms.lists.visualisation.states.kthnode.tasks;

import com.algorithms.lists.logic.ReturnKthToLast;
import com.algorithms.lists.logic.observers.KthToLastRecursiveNodeObserver;
import com.algorithms.lists.node.Node;
import javafx.concurrent.Task;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.algorithms.lists.logic.ReturnKthToLast.getKthToLastRecursive;
import static com.algorithms.lists.visualisation.states.kthnode.tasks.KthNodeAlgorithmTask.KthNodeVisitEventType.*;

/**
 * @author Mihai Alexandru
 * @date 18.10.2018
 */
public class KthNodeAlgorithmTask extends Task<KthNodeAlgorithmTask.KthNodeVisitEvent> implements KthToLastRecursiveNodeObserver<String> {

    private Node<String> headNode;

    private int kth;

    private final AtomicBoolean stopped;

    public KthNodeAlgorithmTask(Node<String> headNode, int kth) {
        this.headNode = headNode;
        this.kth = kth;
        this.stopped = new AtomicBoolean(false);
    }

    @Override
    protected KthNodeAlgorithmTask.KthNodeVisitEvent call() throws Exception {
        getKthToLastRecursive(headNode, kth, this);
        return null;
    }

    @Override
    public void nodeVisited(ReturnKthToLast.Result<String> result) {
        fireEvent(NODE_VISITED, result);
    }

    @Override
    public void resultObtained(ReturnKthToLast.Result<String> result) {
        fireEvent(RESULT_OBTAINED, result);
    }

    @Override
    public void finalResultObtained(ReturnKthToLast.Result<String> finalResult) {
        fireEvent(FINAL_RESULT_OBTAINED, finalResult);
    }

    private void fireEvent(KthNodeVisitEventType t, ReturnKthToLast.Result<String> r) {
        if (this.stopped.get()) {
            return;
        }
        updateValue(new KthNodeVisitEvent(t, r));
    }

    @Override
    public boolean isStopped() {
        return stopped.get();
    }

    public void stop() {
        this.stopped.set(true);
    }

    public enum KthNodeVisitEventType {

        NODE_VISITED,

        RESULT_OBTAINED,

        FINAL_RESULT_OBTAINED;

    }

    public static class KthNodeVisitEvent {

        private KthNodeVisitEventType type;

        private ReturnKthToLast.Result<String> result;

        private KthNodeVisitEvent(KthNodeVisitEventType type, ReturnKthToLast.Result<String> result) {
            this.type = type;
            this.result = result;
        }

        public KthNodeVisitEventType getType() {
            return type;
        }

        public ReturnKthToLast.Result<String> getResult() {
            return result;
        }
    }

}
