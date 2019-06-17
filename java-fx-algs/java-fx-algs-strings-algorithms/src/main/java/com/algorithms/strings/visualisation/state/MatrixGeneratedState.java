package com.algorithms.strings.visualisation.state;

import com.algorithms.graphics.toolbar.InformationBar;
import com.algorithms.graphics.toolbar.Toolbar;
import com.algorithms.graphics.toolbar.control.ToolbarControl;
import com.algorithms.strings.visualisation.context.MatrixRotationContext;
import com.algorithms.strings.visualisation.objects.MatrixRotationControlTypes;
import com.algorithms.strings.visualisation.state.task.GenerateMatrixTask;
import javafx.scene.control.Button;
import jdk.jfr.ContentType;

import static com.algorithms.strings.visualisation.objects.MatrixRotationControlTypes.CLEAR_BUTTON;
import static com.algorithms.strings.visualisation.objects.MatrixRotationControlTypes.ROTATE_MATRIX_BUTTON;
import static com.algorithms.strings.visualisation.utils.MatrixControlUtils.*;

/**
 * @author Mihai Alexandru
 * @date 20.10.2018
 */
public class MatrixGeneratedState extends MatrixAbstractState {

    private int matrixSize;

    public MatrixGeneratedState(int matrixSize) {
        this.matrixSize = matrixSize;
    }

    @Override
    public void controlClicked(ToolbarControl control, MatrixRotationContext context) {
        if (clicked(CLEAR_BUTTON, control)) {
            executeClearMatrix(context);
        } else if (clicked(ROTATE_MATRIX_BUTTON, control)) {
            executeRoatateMatrix(context);
        }

    }

    private void executeClearMatrix(MatrixRotationContext context) {
        var canvas = context.getCanvas();
        canvas.deleteAllNodes();
        Toolbar toolbar = context.getToolbar();

        disableGenerateMatrix(toolbar, false);
        disableClearMatrix(toolbar, true);
        disableRotate(toolbar, true);

        InformationBar informationBar = toolbar.getInformationBar();
        informationBar.changeToInfoIcon();
        informationBar.changeMessage("Generate matrix");
        context.changeState(new GenerateMatrixState());
    }

    private void executeRoatateMatrix(MatrixRotationContext context) {
        GenerateMatrixTask task = new GenerateMatrixTask(matrixSize, context, ()-> stopSearch(context));
        context.changeState(new MatrixStopRotationState(() -> {
            stopTask(task);
            stopSearch(context);
        }));
        Toolbar toolbar = context.getToolbar();
        InformationBar informationBar = toolbar.getInformationBar();
        disableRotate(toolbar, true);
        informationBar.changeToInfoIcon();
        informationBar.changeMessage("Rotating matrix...");
        changeClearToStop(toolbar);
        new Thread(task).start();
    }


    private boolean clicked(MatrixRotationControlTypes type, ToolbarControl toolbarControl) {
        return type.name().equals(toolbarControl.getType());
    }

    private void stopSearch(MatrixRotationContext context) {
        InformationBar informationBar = context.getToolbar().getInformationBar();
        informationBar.changeToInfoIcon();
        informationBar.changeMessage("Rotation finished.");
        context.changeState(this);
    }

    private void stopTask(GenerateMatrixTask task) {
        task.stop();
        task.cancel();
    }

    private void pauseThread() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
