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
        return String.valueOf(rowColumnHolder.getRow() + rowColumnHolder.getColumn() + 1);
    }

    /**
     * @param row
     * @param column
     * @return the computed id.
     */
    public static String create(int row, int column) {
        return String.valueOf(row + column + 1);
    }

    /**
     * @param rowCol
     * @return the computed id.
     */
    public static String create(Pair<Integer, Integer> rowCol) {
        return String.valueOf(rowCol.getKey() + rowCol.getKey() + 1);
    }

}
