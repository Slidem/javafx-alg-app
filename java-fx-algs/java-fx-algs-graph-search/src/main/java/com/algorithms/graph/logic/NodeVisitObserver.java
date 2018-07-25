package com.algorithms.graph.logic;

/**
 * @param <T>
 * @author slidem
 */
@FunctionalInterface
public interface NodeVisitObserver<T> {

    void nodeVisited(Node<T> node);

}
