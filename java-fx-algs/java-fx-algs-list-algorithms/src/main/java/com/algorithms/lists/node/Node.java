package com.algorithms.lists.node;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

/**
 * @author Mihai Alexandru
 * @date 27.07.2018
 */
public class Node<T> implements Iterable<T> {

    private String id;

    private T value;

    private Node<T> next;

    public Node() {
    }

    public Node(T value) {
        this.value = value;
    }

    public Node(T value, String id) {
        this.value = value;
        this.id = id;
    }


    public void appendToTail(T value) {
        requireNonNull(value, "Cannot add a null value");

        Node<T> end = new Node<>(value);
        Node<T> n = this;
        while (n.next != null) {
            n = n.next;
        }
        n.next = end;
    }

    public void appendToTail(Node<T> node) {
        requireNonNull(node, "Cannot add a null node");

        Node<T> n = this;
        while (n.next != null) {
            n = n.next;
        }
        n.next = node;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Node<?> that = (Node<?>) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {

            private Node<T> current = Node.this;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<T> n = current;
                current = current.next;
                return n.getValue();
            }
        };
    }
}
