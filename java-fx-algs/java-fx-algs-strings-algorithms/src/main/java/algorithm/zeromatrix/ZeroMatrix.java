package algorithm.zeromatrix;

import static java.util.Objects.requireNonNull;

/**
 * @author Mihai Alexandru
 */
public class ZeroMatrix {

    private int[][] matrix;

    private boolean transformed;

    private int rows;

    private int cols;

    private int[] transformedRows;

    private int[] transformedCols;

    private int[][] transformedValues;

    public ZeroMatrix(int[][] matrix, int rows, int cols) {
        this.matrix = requireNonNull(matrix, "Cannot pass a null matrix");
        this.rows = rows;
        this.cols = cols;
        this.transformedRows = new int[rows];
        this.transformedCols = new int[cols];
        this.transformedValues = new int[rows][cols];
    }

    /**
     * Transforms and returns the matrix, by setting 0 on the row and column of a zero element.
     *
     * @return the transformed matrix.
     */
    public int[][] transform() {

        if (transformed) {
            return matrix;
        }

        transformMatrix();

        return this.matrix;
    }

    /**
     * @return the current matrix.
     */
    public int[][] getMatrix() {
        return matrix;
    }

    // ----------------------------------------------

    private void transformMatrix() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (this.matrix[i][j] == 0 && this.transformedValues[i][j] == 0) {
                    transformRow(i);
                    transformColumn(j);
                }
            }
        }
    }


    private void transformRow(int row) {
        if (this.transformedRows[row] != 0) {
            return;
        }
        for (int i = 0; i < this.cols; i++) {
            int value = this.matrix[row][i];
            if (value != 0) {
                this.transformedValues[row][i]++;
                this.matrix[row][i] = 0;
            }
        }
        this.transformedRows[row]++;
    }

    private void transformColumn(int col) {
        if (this.transformedCols[col] != 0) {
            return;
        }
        for (int i = 0; i < this.rows; i++) {
            int value = this.matrix[i][col];
            if (value != 0) {
                this.transformedValues[i][col]++;
                this.matrix[i][col] = 0;
            }
        }
        this.transformedCols[col]++;
    }


}
