package com.algorithms.strings.visualisation.state;

import com.algorithms.graphics.toolbar.control.ToolbarControl;
import com.algorithms.strings.visualisation.context.MatrixRotationContext;

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
        context.changeState(new GenerateMatrixState());
    }
}
