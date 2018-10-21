package com.algorithms.strings.visualisation.state;

import com.algorithms.graphics.toolbar.InformationBar;
import com.algorithms.graphics.toolbar.Toolbar;
import com.algorithms.graphics.toolbar.control.ToolbarControl;
import com.algorithms.strings.visualisation.context.MatrixRotationContext;
import com.algorithms.strings.visualisation.factory.MatrixDrawer;
import com.algorithms.strings.visualisation.utils.MatrixControlUtils;
import javafx.scene.control.ComboBox;

import static com.algorithms.strings.visualisation.objects.MatrixRotationControlTypes.MATRIX_SIZE_COMBO;
import static com.algorithms.strings.visualisation.utils.MatrixControlUtils.getControlAs;

/**
 * @author Mihai Alexandru
 * @date 20.10.2018
 */
public class GenerateMatrixState extends MatrixAbstractState {

    private final MatrixDrawer matrixDrawer;

    public GenerateMatrixState() {
        this.matrixDrawer = new MatrixDrawer();
    }

    @Override
    public void controlClicked(ToolbarControl control, MatrixRotationContext context) {
        Toolbar toolbar = context.getToolbar();

        ComboBox<Integer> matrixSizeCombo = getControlAs(MATRIX_SIZE_COMBO, toolbar, ComboBox.class);
        int matrixSize = matrixSizeCombo.getValue();

        matrixDrawer.drawMatrix(matrixSize, context.getCanvas());

        MatrixControlUtils.disableRotate(toolbar, false);
        MatrixControlUtils.disableClearMatrix(toolbar, false);
        MatrixControlUtils.disableGenerateMatrix(toolbar, true);

        InformationBar informationBar = toolbar.getInformationBar();
        informationBar.changeToInfoIcon();
        informationBar.changeMessage("Click the rotate button to start the rotation process.");
        context.changeState(new MatrixGeneratedState(matrixSize));
    }
}
