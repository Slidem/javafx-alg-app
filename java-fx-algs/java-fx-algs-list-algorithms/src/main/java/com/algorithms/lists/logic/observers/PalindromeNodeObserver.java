package com.algorithms.lists.logic.observers;

import com.algorithms.lists.node.Node;

/**
 * @author Mihai Alexandru
 * @date 09.08.2018
 */
public interface PalindromeNodeObserver<T> {

    void runnerVisited(Node<T> runner);

    void nodeVisited(Node<T> n);

    void nodeAddedToStack(Node<T> n);

    void compareCharactersResult(Node<T> a, Node<T> b, boolean result);

    void isPalindrome();

    boolean isStopped();
}
