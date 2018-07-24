package com.algorithms.graph.logic.dfs;

import static java.util.Objects.isNull;

import java.util.HashSet;
import java.util.Set;

import com.algorithms.graph.logic.AbstractGraphSearch;
import com.algorithms.graph.logic.Node;

/**
 * @author slidem
 *
 * @param <T> type of node value
 */
public class DFSSearch<T> extends AbstractGraphSearch<T>{
	
	private Set<Node<T>> searched;
	
	public DFSSearch(Node<T> from, Node<T> to) {
		super(from, to);
	}
	
	@Override
	protected void setup() {
		this.searched = new HashSet<>();
	}
	
	@Override
	protected void search(Node<T> from) {
		searchInternally(from);
		computePath();
	}
	
	private void searchInternally(Node<T> root){
		if(found || isNull(root) || searchStopped) {
			return;
		}
		visit(root);
		if(root.equals(target)) {
			this.found = true;
			return;
		}
		searched.add(root);
		for(Node<T> n : root.getNeighbours()) {
			if(searched.add(n)) {
				links.put(n, root);
				searchInternally(n);
			}
		}
	}
}
