package com.algorithms.graph.logic;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * @param <T>
 * @author slidem
 */
public interface GraphSearch<T> {

    GraphSearch<T> beginSearch();

    void stopSearch();

    boolean pathExists();

    Optional<Iterator<Node<T>>> getNodePath();

    void nodeVisitors(List<NodeVisitObserver<T>> nodeVisitObservers);
}
