package com.algorithms.strings.visualisation.state;

import com.algorithms.graphics.toolbar.InformationBar;
import com.algorithms.graphics.toolbar.Toolbar;
import com.algorithms.graphics.toolbar.control.ToolbarControl;
import com.algorithms.strings.visualisation.context.MatrixRotationContext;
import com.algorithms.strings.visualisation.objects.MatrixRotationControlTypes;
import com.algorithms.strings.visualisation.state.task.GenerateMatrixTask;
import com.algorithms.strings.visualisation.state.task.GenerateMatrixTask.MatrixTaskEvent;
import com.algorithms.strings.visualisation.utils.MatrixIdComputer;

import static com.algorithms.strings.visualisation.objects.MatrixRotationControlTypes.CLEAR_BUTTON;
import static com.algorithms.strings.visualisation.objects.MatrixRotationControlTypes.ROTATE_MATRIX_BUTTON;
import static com.algorithms.strings.visualisation.state.task.GenerateMatrixTask.MatrixTaskEventType.CHANGE;
import static com.algorithms.strings.visualisation.state.task.GenerateMatrixTask.MatrixTaskEventType.ROTATION_FINISHED;
import static com.algorithms.strings.visualisation.utils.MatrixControlUtils.*;
import static java.util.Objects.isNull;
import static javafx.scene.paint.Color.GOLD;
import static javafx.scene.paint.Color.RED;

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
        GenerateMatrixTask task = new GenerateMatrixTask(matrixSize);
        task.valueProperty().addListener((observableValue, matrixTaskEvent, newValue) -> processMatrixEvent(context, newValue, task));
        context.changeState(new MatrixStopRotationState(() -> stopTask(context, task)));
    }

    private void processMatrixEvent(MatrixRotationContext context, MatrixTaskEvent event, GenerateMatrixTask task) {
        if (isNull(event)) {
            return;
        }

        if (CHANGE == event.getType()) {
            processMatrixChangeEvent(event, context);
        } else if (ROTATION_FINISHED == event.getType()) {
            processMatrixFinishedEvent(context, task);
        }
    }

    private void processMatrixFinishedEvent(MatrixRotationContext context, GenerateMatrixTask task) {
        context.getToolbar().getInformationBar().changeMessage("Rotation finished");

        stopTask(context, task);

        context.changeState(new GenerateMatrixState());
    }

    private void processMatrixChangeEvent(MatrixTaskEvent event, MatrixRotationContext context) {
        String nodeId = MatrixIdComputer.create(event.getNodeRowCol());

        var canvasNode = context.getCanvas().getNode(nodeId);

        canvasNode.ifPresent(n -> n.visit(RED));

        pauseThread();

        canvasNode.ifPresent(n -> n.changeNodeText(event.getTxt()));

        pauseThread();

        canvasNode.ifPresent(n -> n.visit(GOLD));
    }


    private boolean clicked(MatrixRotationControlTypes type, ToolbarControl toolbarControl) {
        return type.name().equals(toolbarControl.getType());
    }

    private void stopTask(MatrixRotationContext context, GenerateMatrixTask task) {
        InformationBar informationBar = context.getToolbar().getInformationBar();
        informationBar.changeToInfoIcon();
        informationBar.changeMessage("Rotation finished.");
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
