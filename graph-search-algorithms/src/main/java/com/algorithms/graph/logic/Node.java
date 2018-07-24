package com.algorithms.graph.logic;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author slidem
 *
 * @param <T>
 */
public class Node<T> {
	
	private static final String CONNECTION_ERROR_MESSAGE = "Cannot connect a null node";

	private final String id;

	private final T value;

	private final Set<Node<T>> neighbours;
	
	private final Connection<T> connection;
	
	private Node(String id, T value, Connection<T> connection) {
		this.id = id;
		this.value = value;
		this.neighbours = new HashSet<>(2);
		this.connection = connection;
	}

	public String getId() {
		return id;
	}

	public Set<Node<T>> getNeighbours() {
		return Collections.unmodifiableSet(neighbours);
	}

	public T getValue() {
		return this.value;
	}

	public boolean addNeighbour(Node<T> neighbour) {
		return connection.connect(this, neighbour);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("unchecked")
		Node<T> other = (Node<T>) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	public static interface Connection<T> {
		boolean connect(Node<T> a, Node<T> b);
	}

	public static final class UndirectedConnection<T> implements Connection<T> {

		@Override
		public boolean connect(Node<T> a, Node<T> b) {
			Objects.requireNonNull(a, CONNECTION_ERROR_MESSAGE);
			Objects.requireNonNull(b, CONNECTION_ERROR_MESSAGE);
			return a.neighbours.add(b) && b.neighbours.add(a);
		}
	}

	public static final class AdjacentConnection<T> implements Connection<T> {

		@Override
		public boolean connect(Node<T> a, Node<T> b) {
			Objects.requireNonNull(a, CONNECTION_ERROR_MESSAGE);
			Objects.requireNonNull(b, CONNECTION_ERROR_MESSAGE);
			return a.neighbours.add(b);
		}
	}

	public static final class Builder<T> {

		private T value;

		private String id;
		
		private Connection<T> connection;
		
		public Builder<T> withValue(T value) {
			this.value = value;
			return this;
		}

		public Builder<T> withId(String id) {
			this.id = Objects.requireNonNull(id);
			return this;
		}
		
		public Builder<T> withConnection(Connection<T> connection){
			this.connection = Objects.requireNonNull(connection);
			return this;
		}
		
		public Node<T> build() {
			Objects.requireNonNull(this.id);
			return new Node<>(id, value, connection);
		}
	}

}
