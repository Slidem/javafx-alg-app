package com.algorithms.lists.logic.observers.impl;

import com.algorithms.lists.logic.observers.PalindromeNodeObserver;
import com.algorithms.lists.node.Node;

/**
 * @author Mihai Alexandru
 * @date 09.08.2018
 */
public class EmptyPalindromeNodeObserverImpl implements PalindromeNodeObserver<String> {

    @Override
    public void runnerVisited(Node<String> runner) {
        // do nothing
    }

    @Override
    public void nodeVisited(Node<String> n) {
        // do nothing
    }

    @Override
    public void nodeAddedToStack(Node<String> n) {
        // do nothing
    }

    @Override
    public void compareCharactersResult(Node<String> a, Node<String> b, boolean result) {
        // do nothing
    }

    @Override
    public void isPalindrome() {
        // do nothing
    }

    @Override
    public boolean isStopped() {
        return false;
    }
}
