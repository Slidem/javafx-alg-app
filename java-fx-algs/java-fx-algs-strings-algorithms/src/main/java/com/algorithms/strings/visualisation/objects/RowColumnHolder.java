package com.algorithms.strings.visualisation.objects;

/**
 * @author Mihai Alexandru
 * @date 20.10.2018
 */
public class RowColumnHolder {

    private int row;

    private int column;

    private int matrixSize;

    public RowColumnHolder(int row, int column, int matrixSize) {
        this.row = row;
        this.column = column;
        this.matrixSize = matrixSize;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getMatrixSize() {
        return matrixSize;
    }
}
