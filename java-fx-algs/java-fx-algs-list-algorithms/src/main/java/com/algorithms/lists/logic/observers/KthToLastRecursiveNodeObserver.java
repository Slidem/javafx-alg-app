package com.algorithms.lists.logic.observers;

import com.algorithms.lists.logic.ReturnKthToLast;
import com.algorithms.lists.node.Node;

/**
 * @author Mihai Alexandru
 * @date 02.08.2018
 */
public interface KthToLastRecursiveNodeObserver<T extends Object> {

    /**
     * Triggered when the node is visited in the recursive function of the kth to last algorithm.
     * Check also {@link ReturnKthToLast}
     *
     * @param result
     */
    void nodeVisited(ReturnKthToLast.Result<T> result);

    /**
     * Triggered when the result of the recursive function of the kth to last algorithm is returned.
     * Check also {@link ReturnKthToLast}
     *
     * @param result
     */
    void resultObtained(ReturnKthToLast.Result<T> result);


    /**
     * Triggered when final result is obtained. If the {@link com.algorithms.lists.logic.ReturnKthToLast.Result} has
     * a null {@link Node}, than the kth node was not found.
     *
     * @param finalResult
     */
    void finalResultObtained(ReturnKthToLast.Result<T> finalResult);

    /**
     * @return true if the observer stopped observing the algorithm. false otherwise
     */
    boolean isStopped();

}
