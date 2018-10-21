package com.algorithms.lists.logic;

import com.algorithms.lists.logic.observers.PalindromeNodeObserver;
import com.algorithms.lists.node.Node;
import javafx.util.Pair;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;

/**
 * @author Mihai Alexandru
 * @date 27.07.2018
 */
public class Palindrome {

    private Palindrome() {
    }

    public static boolean isPalindromeReverse(Node<String> node) {

        Pair<Integer, Node<String>> revResult = reverseList(node);
        int listLength = revResult.getKey();
        Node<String> rev = revResult.getValue();

        int begin = 0;
        int end = listLength - 1;
        Iterator<String> nodeIterator = node.iterator();
        Iterator<String> revIterator = rev.iterator();
        while (begin < end) {
            String a = nodeIterator.next();
            String b = revIterator.next();
            if (!Objects.equals(a, b)) {
                return false;
            }
            begin += 1;
            end -= 1;
        }

        return true;
    }

    public static boolean isPalindromeIterative(Node<String> head, PalindromeNodeObserver<String> palindromeNodeObserver) {
        Node<String> n = head;
        Node<String> runner = head;
        Deque<Node<String>> stack = new LinkedList<>();

        int i = 1;
        int rIndex = 1;
        while (Objects.nonNull(runner)) {
            int rMax = i * 2;
            while (runner != null && rIndex < rMax) {
                runner = runner.getNext();
                rIndex++;
            }

            if (palindromeNodeObserver.isStopped()) {
                return true;
            }

            palindromeNodeObserver.runnerVisited(runner);
            palindromeNodeObserver.nodeVisited(n);
            palindromeNodeObserver.nodeAddedToStack(n);

            stack.push(n);
            if (runner != null) {
                n = n.getNext();
            }
            i++;
        }

        if (rIndex % 2 == 1) {
            stack.pop();
        }

        while (Objects.nonNull(n)) {
            palindromeNodeObserver.nodeVisited(n);
            if (palindromeNodeObserver.isStopped()) {
                return true;
            }
            Node<String> other = stack.pop();
            boolean nodesAreEqual = Objects.equals(n, other);
            palindromeNodeObserver.compareCharactersResult(n, other, nodesAreEqual);
            if (!nodesAreEqual) {
                return false;
            }
            n = n.getNext();
        }

        palindromeNodeObserver.isPalindrome();

        return true;
    }


    private static Pair<Integer, Node<String>> reverseList(Node<String> node) {
        Node<String> rev = new Node<>(node.getValue());
        Node<String> n = node.getNext();

        int length = 1;
        while (n != null) {
            Node<String> r = new Node<>(n.getValue());
            r.setNext(rev);
            rev = r;
            length++;
            n = n.getNext();
        }

        return new Pair<>(length, rev);

    }
}
