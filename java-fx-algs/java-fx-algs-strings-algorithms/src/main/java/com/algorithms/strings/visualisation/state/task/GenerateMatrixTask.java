package com.algorithms.strings.visualisation.state.task;

import com.algorithms.graphics.canvas.nodes.CanvasNode;
import com.algorithms.strings.problems.rotatematrix.RotateMatrix;
import com.algorithms.strings.problems.rotatematrix.RotateMatrixAlgObserver;
import com.algorithms.strings.visualisation.context.MatrixRotationContext;
import com.algorithms.strings.visualisation.objects.RowColumnHolder;
import com.algorithms.strings.visualisation.utils.MatrixIdComputer;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.util.Pair;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

import static javafx.scene.paint.Color.GOLD;
import static javafx.scene.paint.Color.RED;

/**
 * @author Mihai Alexandru
 * @date 20.10.2018
 */
public class GenerateMatrixTask extends Task<Void> implements RotateMatrixAlgObserver {

    private AtomicBoolean stopped;

    private int matrixSize;

    private MatrixRotationContext context;

    private Runnable taskFinishedAction;

    public GenerateMatrixTask(int matrixSize, MatrixRotationContext context, Runnable taskFinishedAction) {
        this.taskFinishedAction = taskFinishedAction;
        this.stopped = new AtomicBoolean(false);
        this.matrixSize = matrixSize;
        this.context = context;
    }

    @Override
    protected Void call(){
        RotateMatrix.orderedNumericalMatrix(matrixSize).rotate(this);
        Platform.runLater(taskFinishedAction);
        return null;
    }

    @Override
    public void change(Pair<Integer, Integer> n, String txt) {
        if (stopped.get()) {
            return;
        }

        String nodeId = MatrixIdComputer.create(n, matrixSize);

        CanvasNode<RowColumnHolder> canvasNode = context.getCanvas().getNode(nodeId).orElse(null);

        if (Objects.isNull(canvasNode)) {
            return;
        }

        Platform.runLater(()-> canvasNode.visit(RED));

        pauseThread();

        Platform.runLater(()-> canvasNode.changeNodeText(txt));

        pauseThread();

        Platform.runLater(()->canvasNode.visit(GOLD));
    }

    @Override
    public boolean isStopped() {
        return stopped.get();
    }

    public void stop() {
        stopped.set(true);
    }


    private void pauseThread() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
