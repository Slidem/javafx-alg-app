package com.algorithms.lists.logic;

import com.algorithms.lists.logic.observers.KthToLastRecursiveNodeObserver;
import com.algorithms.lists.logic.observers.impl.EmptyKthToLastObserver;
import com.algorithms.lists.node.Node;

import static java.util.Objects.*;

/**
 * @author Mihai Alexandru
 * @date 27.07.2018
 */
public class ReturnKthToLast {

    public static <T> Node<T> getKthToLast(Node<T> head, int k) {
        Node<T> current = requireNonNull(head);
        Node<T> kth = head;

        int count = 1;
        while (nonNull(kth.getNext()) && count < k) {
            count++;
            kth = kth.getNext();
        }

        if (isNull(kth)) {
            throw new IndexOutOfBoundsException();
        }

        while (nonNull(kth.getNext())) {
            current = current.getNext();
            kth = kth.getNext();
        }
        return current;
    }

    // implement recursive solution

    public static <T extends Object> Node<T> getKthToLastRecursive(Node<T> head, int k) {
        return kth(head, k, new EmptyKthToLastObserver<>()).getNode();

    }

    public static <T> Node<T> getKthToLastRecursive(Node<T> head, int k, KthToLastRecursiveNodeObserver<T> observer) {
        Result<T> result = kth(head, k, observer);
        observer.finalResultObtained(result);
        return result.getNode();
    }

    private static <T extends Object> Result<T> kth(Node<T> node, int k, KthToLastRecursiveNodeObserver<T> observer) {
        if (observer.isStopped()) {
            return Result.of(k, node);
        }

        if (node == null) {
            return Result.of(0);
        }

        observer.nodeVisited(Result.of(k, node));
        var res = kth(node.getNext(), k, observer);
        observer.resultObtained(res);

        if (k == res.getK()) {
            return res;
        }
        return Result.of(res.getK() + 1, node);
    }

    public static final class Result<T> {
        private int k;
        private Node<T> node;

        Result(int k, Node<T> node) {
            this.k = k;
            this.node = node;
        }

        static <T> Result<T> of(int k) {
            return new Result(k, null);
        }

        static <T> Result<T> of(int k, Node<T> node) {
            return new Result(k, node);
        }

        public int getK() {
            return k;
        }

        public Node<T> getNode() {
            return node;
        }

    }
}
