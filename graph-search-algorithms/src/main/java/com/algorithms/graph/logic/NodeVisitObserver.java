package com.algorithms.graph.logic;

/**
 * @author slidem
 *
 * @param <T>
 */
@FunctionalInterface
public interface NodeVisitObserver<T> {
	
	void nodeVisited(Node<T> node);

}
