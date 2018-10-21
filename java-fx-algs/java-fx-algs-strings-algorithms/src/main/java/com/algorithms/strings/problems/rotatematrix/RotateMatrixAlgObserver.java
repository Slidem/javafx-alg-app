package com.algorithms.strings.problems.rotatematrix;

import javafx.util.Pair;

/**
 * @author Mihai Alexandru
 * @date 11.08.2018
 */
public interface RotateMatrixAlgObserver {

    void rotationFinished();

    void change(Pair<Integer, Integer> n, String txt);

    boolean isStopped();
}
