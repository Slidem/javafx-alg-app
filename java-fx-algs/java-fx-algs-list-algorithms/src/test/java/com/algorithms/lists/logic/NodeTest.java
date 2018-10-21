package com.algorithms.lists.logic;

import com.algorithms.lists.node.Node;

import java.util.List;

/**
 * @author Mihai Alexandru
 * @date 27.07.2018
 */
public abstract class NodeTest {

    protected Node<String> fromStringValues(List<String> values) {
        Node<String> head = null;

        for (String value : values) {
            if (head == null) {
                head = new Node<>(value);
            } else {
                head.appendToTail(value);
            }
        }

        return head;
    }

    protected Node<String> getNode(Node<String> head, int index) {
        int count = 0;
        Node<String> current = head;
        while (count++ < index) {
            current = current.getNext();
        }
        return current;
    }
}
