package com.algorithms.graph.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public abstract class AbstractGraphSearch<T> implements GraphSearch<T> {

	protected Node<T> root;
	
	protected Node<T> target;

	protected boolean found;

	protected LinkedList<Node<T>> path;
	
	protected Map<Node<T>, Node<T>> links;

	private List<NodeVisitObserver<T>> nodeVisitObservers;
	
	protected boolean searchStopped;

	public AbstractGraphSearch(Node<T> from, Node<T> to) {
		this.target = to;
		this.root = from;
		this.nodeVisitObservers = Collections.emptyList();
		this.links = new HashMap<>();
		setup();
	}
	
	@Override
	public GraphSearch<T> beginSearch() {
		search(root);
		return this;
	}

	@Override
	public boolean pathExists() {
		return found;
	}

	@Override
	public void nodeVisitors(List<NodeVisitObserver<T>> nodeVisitObservers) {
		Objects.requireNonNull(nodeVisitObservers, "Cannot pass a null list of node visit observers");
		this.nodeVisitObservers = new ArrayList<>(nodeVisitObservers);
	}

	@Override
	public Optional<Iterator<Node<T>>> getNodePath() {
		if (!found) {
			return Optional.empty();
		}
		return Optional.of(path.iterator());
	}
	
	@Override
	public void stopSearch() {
		this.searchStopped = true;
	}

	// -----------------------------------------------

	protected abstract void setup();

	protected abstract void search(Node<T> from);

	// -----------------------------------------------

	protected void visit(Node<T> n) {
		nodeVisitObservers.stream().forEach(obs -> obs.nodeVisited(n));
	}
	
	protected void computePath() {
		if(!found) {
			return;
		}
		this.path = new LinkedList<>();
		Node<T> n = target;
		while(n != null) {
			path.addFirst(n);
			n = links.get(n);
		}
	}

}
