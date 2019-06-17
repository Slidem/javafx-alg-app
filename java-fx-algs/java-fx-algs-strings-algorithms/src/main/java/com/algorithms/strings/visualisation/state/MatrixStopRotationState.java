package com.algorithms.strings.visualisation.state;

import com.algorithms.graphics.toolbar.Toolbar;
import com.algorithms.graphics.toolbar.control.ToolbarControl;
import com.algorithms.strings.visualisation.context.MatrixRotationContext;
import com.algorithms.strings.visualisation.objects.MatrixRotationControlTypes;
import javafx.scene.control.Button;

import java.awt.*;

import static com.algorithms.strings.visualisation.utils.MatrixControlUtils.*;

/**
 * @author Mihai Alexandru
 * @date 20.10.2018
 */
public class MatrixStopRotationState extends MatrixAbstractState {

    private Runnable runnable;

    public MatrixStopRotationState(Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    public void controlClicked(ToolbarControl control, MatrixRotationContext context) {
        runnable.run();
        Toolbar toolbar = context.getToolbar();
        disableGenerateMatrix(toolbar, false);
        disableClearMatrix(toolbar, false);
        disableRotate(toolbar, false);
        changeStopToClear(toolbar);
        context.changeState(new GenerateMatrixState());
    }
}
