package com.algorithms.lists.logic;

import com.algorithms.lists.node.Node;

import java.util.HashSet;
import java.util.Objects;

import static java.util.Objects.nonNull;

/**
 * Removes duplicates from a singly linked list
 *
 * @author Mihai Alexandru
 * @date 27.07.2018
 */
public class RemoveDups {

    /**
     * Uses a hashset to track the duplicate values. This algorithm takes O(n) time complexity.
     *
     * @param head
     * @param <T>
     * @return head of the node containing no duplicate value nodes.
     */
    public static <T> Node<T> removeDups(Node<T> head) {

        var values = new HashSet<>();

        Node<T> current = head;
        Node<T> prev = null;

        while (current != null) {
            boolean duplicate = !values.add(current.getValue());
            if (duplicate) {
                prev.setNext(current.getNext());
            }

            prev = current;
            current = current.getNext();
        }

        return head;
    }

    /**
     * Does not use any temporary buffer to track duplicates. This algorithm has O(n^2) time complexity.
     *
     * @param <T>
     * @return
     */
    public static <T> Node<T> removeDupsNoBuffer(Node<T> head) {

        Node<T> current = head;

        while (nonNull(current)) {

            Node<T> prev = current;
            Node<T> n = current.getNext();

            while (nonNull(n)) {
                if (Objects.equals(current.getValue(), n.getValue())) {
                    prev.setNext(n.getNext());
                } else {
                    prev = n;
                }
                n = n.getNext();
            }

            current = current.getNext();
        }

        return head;
    }
}
