package com.algorithms.graph.logic.bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;

import com.algorithms.graph.logic.AbstractGraphSearch;
import com.algorithms.graph.logic.Node;

/**
 * @author slidem
 *
 * @param <T> type of node value
 */
public class BFSSearch<T> extends AbstractGraphSearch<T> {
	
	public BFSSearch(Node<T> from, Node<T> to) {
		super(from, to);
	}
	
	@Override
	protected void setup() {
		// Nothing to do here.
	}
	
	@Override
	protected void search(Node<T> root) {
		if(Objects.isNull(root)) {
			return;
		}
		
		final var visited = new HashSet<Node<T>>();
		final var toVisit = new LinkedList<Node<T>>();
		toVisit.addFirst(root);
		visited.add(root);
		
		search(visited, toVisit);
		
		computePath();
	}

	private void search(HashSet<Node<T>> visited, LinkedList<Node<T>> toVisit) {
		while(!toVisit.isEmpty() && !found && !searchStopped) {
			Node<T> n = toVisit.removeLast();
			visit(n);
			if(n.equals(target)) {
				this.found = true;
			}
			for(Node<T> neighbour : n.getNeighbours()) {
				if(visited.add(neighbour)){
					toVisit.addFirst(neighbour);
					links.put(neighbour, n);
				}
			}
		}
	}
	
	

}
