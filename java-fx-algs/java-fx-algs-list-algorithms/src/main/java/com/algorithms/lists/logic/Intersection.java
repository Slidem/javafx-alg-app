package com.algorithms.lists.logic;

import com.algorithms.lists.node.Node;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author Mihai Alexandru
 * @date 27.07.2018
 */
public class Intersection {

    public static Optional<Node<String>> intersectionBruteForce(Node<String> headA, Node<String> headB) {

        Node<String> a = headA;
        while (a != null) {
            Node<String> b = headB;
            while (b != null) {
                if (a == b) {
                    return Optional.of(a);
                }
                b = b.getNext();
            }
            a = a.getNext();
        }

        return Optional.empty();
    }

    public static Optional<Node<String>> intersection(Node<String> headA, Node<String> headB) {

        Set<Node<String>> aSet = new HashSet<>();

        Node<String> a = headA;
        while (a != null) {
            aSet.add(a);
            a = a.getNext();
        }

        Node<String> b = headB;
        while (b != null) {
            if (!aSet.add(b)) {
                return Optional.of(b);
            }
            b = b.getNext();
        }
        return Optional.empty();
    }

    public static Optional<Node<String>> intersectionOptimized(Node<String> headA, Node<String> headB) {
        Set<Node<String>> aSet = new HashSet<>();
        Set<Node<String>> bSet = new HashSet<>();

        Node<String> a = headA;
        Node<String> b = headB;

        while (a != null || b != null) {
            if (a != null) {
                aSet.add(a);
                if (bSet.contains(a)) {
                    return Optional.of(a);
                }
            }
            if (b != null) {
                bSet.add(b);
                if (aSet.contains(b)) {
                    return Optional.of(b);
                }
            }
            a = a.getNext();
            b = b.getNext();
        }

        return Optional.empty();
    }

}
