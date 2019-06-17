package com.algorithms.strings.problems.rotatematrix;

import javafx.util.Pair;

/**
 * @author Mihai Alexandru
 * @date 12.08.2018
 */
public class EmptyRotateObserver implements RotateMatrixAlgObserver {
    

    @Override
    public void change(Pair<Integer, Integer> n, String txt) {

    }


    @Override
    public boolean isStopped() {
        return false;
    }
}
