package com.algorithms.lists.logic;

import com.algorithms.lists.node.Node;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;
import static java.util.Objects.requireNonNull;

/**
 * @author Mihai Alexandru
 * @date 27.07.2018
 */
public class SumLists {

    private SumLists() {
    }

    public static Node<Integer> sumListsReverseOrder(Node<Integer> headA, Node<Integer> headB) {
        requireNonNull(headA);
        requireNonNull(headB);

        Node<Integer> a = headA;
        Node<Integer> b = headB;
        Node<Integer> nodeSum = null;

        int reminder = 0;

        while (nonNull(a) || nonNull(b)) {
            int sum = reminder;
            if (nonNull(a)) {
                sum += a.getValue();
                a = a.getNext();
            }
            if (nonNull(b)) {
                sum += b.getValue();
                b = b.getNext();
            }
            int digit = sum % 10;
            reminder = sum / 10;
            nodeSum = addToTail(nodeSum, digit);
        }

        return nodeSum;
    }


    public static Node<Integer> sumListsForwardOrder(Node<Integer> headA, Node<Integer> headB) {
        requireNonNull(headA);
        requireNonNull(headB);

        int sum = getSum(headA) + getSum(headB);

        List<Integer> digits = new ArrayList<>();
        while (sum != 0) {
            int c = sum % 10;
            digits.add(c);
            sum /= 10;
        }

        Node<Integer> sumNode = null;
        for (int i = digits.size() - 1; i >= 0; i--) {
            sumNode = addToTail(sumNode, digits.get(i));
        }

        return sumNode;
    }

    private static int getSum(Node<Integer> head) {
        int sum = 0;
        Node<Integer> n = head;
        while (nonNull(n)) {
            sum = sum * 10 + n.getValue();
            n = n.getNext();
        }
        return sum;
    }


    private static Node<Integer> addToTail(Node<Integer> n, int value) {
        if (n == null) {
            return new Node<>(value);
        } else {
            n.appendToTail(value);
            return n;
        }
    }

}
