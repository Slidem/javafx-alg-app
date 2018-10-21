package com.algorithms.lists.logic.observers.impl;

import com.algorithms.lists.logic.ReturnKthToLast;
import com.algorithms.lists.logic.observers.KthToLastRecursiveNodeObserver;

/**
 * @author Mihai Alexandru
 * @date 02.08.2018
 */
public class EmptyKthToLastObserver<T> implements KthToLastRecursiveNodeObserver<T> {

    public EmptyKthToLastObserver() {
    }

    @Override
    public void nodeVisited(ReturnKthToLast.Result<T> n) {
        // do nothing
    }

    @Override
    public void resultObtained(ReturnKthToLast.Result<T> result) {
        // do nothing
    }

    @Override
    public void finalResultObtained(ReturnKthToLast.Result<T> finalResult) {
        // do nothing
    }

    @Override
    public boolean isStopped() {
        return false;
    }
}
