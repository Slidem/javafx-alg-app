package com.algorithms.strings.visualisation.state.task;

import com.algorithms.strings.problems.rotatematrix.RotateMatrix;
import com.algorithms.strings.problems.rotatematrix.RotateMatrixAlgObserver;
import javafx.concurrent.Task;
import javafx.util.Pair;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Mihai Alexandru
 * @date 20.10.2018
 */
public class GenerateMatrixTask extends Task<GenerateMatrixTask.MatrixTaskEvent> implements RotateMatrixAlgObserver {

    private AtomicBoolean stopped;

    private int matrixSize;

    public GenerateMatrixTask(int matrixSize) {
        this.stopped = new AtomicBoolean(false);
        this.matrixSize = matrixSize;
    }

    @Override
    protected MatrixTaskEvent call() throws Exception {
        RotateMatrix.orderedNumericalMatrix(matrixSize).rotate(this);
        return null;
    }

    @Override
    public void rotationFinished() {
        if (stopped.get()) {
            return;
        }
        updateValue(new MatrixTaskEvent(MatrixTaskEventType.ROTATION_FINISHED, null, null));
    }

    @Override
    public void change(Pair<Integer, Integer> n, String txt) {
        if (stopped.get()) {
            return;
        }
        updateValue(new MatrixTaskEvent(MatrixTaskEventType.CHANGE, n, txt));
    }

    @Override
    public boolean isStopped() {
        return stopped.get();
    }

    public void stop() {
        stopped.set(true);
    }


    public static class MatrixTaskEvent {

        private Pair<Integer, Integer> nodeRowCol;

        private String txt;

        private MatrixTaskEventType type;

        public MatrixTaskEvent(MatrixTaskEventType type, Pair<Integer, Integer> nodeRowCol, String txt) {
            this.nodeRowCol = nodeRowCol;
            this.txt = txt;
            this.type = type;
        }

        public Pair<Integer, Integer> getNodeRowCol() {
            return nodeRowCol;
        }

        public String getTxt() {
            return txt;
        }

        public MatrixTaskEventType getType() {
            return type;
        }
    }

    public enum MatrixTaskEventType {

        CHANGE, ROTATION_FINISHED;

    }


}
