package com.algorithms.strings.problems.rotatematrix;

import javafx.util.Pair;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

/**
 *
 */
public class RotateMatrix {

    public Pixel[][] matrix;

    public RotateMatrix(Pixel[][] matrix) {
        this.matrix = requireNonNull(matrix);
    }

    public static RotateMatrix alphabeticalMatrix(int size) {
        int character = (int) 'a';
        Pixel matrix[][] = new Pixel[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                String pixelValue = String.valueOf(((char) character++));
                matrix[i][j] = new Pixel<>(pixelValue, i, j);
            }
        }
        return new RotateMatrix(matrix);
    }

    public static RotateMatrix orderedNumericalMatrix(int size) {
        int start = 0;
        Pixel matrix[][] = new Pixel[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                String pixelValue = String.valueOf(start++);
                matrix[i][j] = new Pixel<>(pixelValue, i, j);
            }
        }
        return new RotateMatrix(matrix);
    }

    public Pixel[][] rotate(RotateMatrixAlgObserver observer) {
        int start = 0;
        int end = matrix.length - 1;
        while (start < end) {
            if (observer.isStopped()) {
                return matrix;
            }
            rotateLayer(start, end, observer);
            start++;
            end--;
        }
        observer.rotationFinished();
        return matrix;
    }

    public void printMatrix() {
        StringBuilder sb = new StringBuilder();
        sb.append("---- Matrix -----").append("\n");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                sb.append(matrix[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    public Pixel[][] getMatrix() {
        return matrix;
    }

    // -------------------------------------------------

    private void rotateLayer(int start, int end, RotateMatrixAlgObserver observer) {
        int length = end - start;
        int plus = start;
        int minus = end;
        for (int i = 0; i < length; i++) {
            if (observer.isStopped()) {
                return;
            }

            Pixel c1 = matrix[start][plus];
            Pixel c2 = matrix[plus][end];
            Pixel c3 = matrix[end][minus];
            Pixel c4 = matrix[minus][start];

            matrix[start][plus] = c4;
            notifyChange(start, plus, c4.value.toString(), observer);

            matrix[plus][end] = c1;
            notifyChange(plus, end, c1.value.toString(), observer);

            matrix[end][minus] = c2;
            notifyChange(end, minus, c2.value.toString(), observer);

            matrix[minus][start] = c3;
            notifyChange(minus, start, c3.value.toString(), observer);

            plus++;
            minus--;
        }
    }

    private void notifyChange(int row, int col, String txt, RotateMatrixAlgObserver observer) {
        observer.change(new Pair<>(row, col), txt);
    }


    public static class Pixel<T> {
        private T value;

        private int row;

        private int col;

        public Pixel(T value, int row, int column) {
            this.value = value;
            this.row = row;
            col = column;
        }

        @Override
        public String toString() {
            return value.toString();
        }

        public static <T> Pixel<T> of(T value) {
            return new Pixel<>(value, 0, 0);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pixel<?> pixel = (Pixel<?>) o;
            return Objects.equals(value, pixel.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }

}
