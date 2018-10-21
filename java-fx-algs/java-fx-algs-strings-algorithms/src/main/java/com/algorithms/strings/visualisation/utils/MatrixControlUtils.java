package com.algorithms.strings.visualisation.utils;

import com.algorithms.graphics.toolbar.Toolbar;
import com.algorithms.strings.visualisation.objects.MatrixRotationControlTypes;
import javafx.scene.Node;

import static com.algorithms.strings.visualisation.objects.MatrixRotationControlTypes.*;

/**
 * @author Mihai Alexandru
 * @date 20.10.2018
 */
public class MatrixControlUtils {

    private MatrixControlUtils() {
    }

    public static void disableGenerateMatrix(Toolbar toolbar, boolean disabled) {
        getControl(GENERATE_MATRIX_BUTTON, toolbar).setDisable(disabled);
        getControl(MATRIX_SIZE_COMBO, toolbar).setDisable(disabled);
    }

    public static void disableRotate(Toolbar toolbar, boolean disabled) {
        getControl(ROTATE_MATRIX_BUTTON, toolbar).setDisable(disabled);
    }

    public static void disableClearMatrix(Toolbar toolbar, boolean disabled) {
        getControl(CLEAR_BUTTON, toolbar).setDisable(disabled);
    }

    public static <T extends Node> T getControlAs(MatrixRotationControlTypes controlTypes, Toolbar toolbar, Class<T> expectedType) {
        return toolbar.getControl(controlTypes.name()).getControlAs(expectedType);
    }

    private static Node getControl(MatrixRotationControlTypes controlType, Toolbar toolbar) {
        return toolbar.getControl(controlType.name()).getControl();
    }
}
