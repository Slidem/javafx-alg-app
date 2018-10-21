package com.algorithms.lists.logic;

import com.algorithms.lists.node.Node;

import static java.util.Objects.nonNull;

/**
 * @author Mihai Alexandru
 * @date 27.07.2018
 */
public class DeleteMiddleNode {

    /**
     * This approach is wrong... we can do this by just one simple operation c1 -> c2 -> c3, copy data from c2 to c1, and point c1.next to c3
     *
     * @param head
     * @param <T>
     */
    public static <T> void deleteMiddleNode(Node<T> head) {

        Node<T> prev = head;
        Node<T> n = head.getNext();

        while (nonNull(n.getNext())) {
            prev.setNext(n.getNext());
            prev.setValue(n.getValue());
            prev = n;
            n = n.getNext();
        }

        prev.setNext(null);
    }
}
