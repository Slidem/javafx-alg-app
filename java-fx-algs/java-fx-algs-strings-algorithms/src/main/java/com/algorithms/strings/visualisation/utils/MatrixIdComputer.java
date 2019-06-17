package com.algorithms.strings.visualisation.utils;

import com.algorithms.strings.visualisation.objects.RowColumnHolder;
import javafx.util.Pair;

/**
 * @author Mihai Alexandru
 * @date 20.10.2018
 */
public final class MatrixIdComputer {

    private MatrixIdComputer() {
    }

    /**
     * @param rowColumnHolder row and column holder.
     * @return returns the computed id;
     */
    public static String create(RowColumnHolder rowColumnHolder) {
        return String.valueOf(rowColumnHolder.getRow() * rowColumnHolder.getMatrixSize() + rowColumnHolder.getColumn() + 1);
    }

    /**
     * @param rowCol
     * @return the computed id.
     */
    public static String create(Pair<Integer, Integer> rowCol, int matrixSize) {
        return String.valueOf(rowCol.getKey() * matrixSize + rowCol.getValue() + 1);
    }

}
