package algorithm.rotatematrix;

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

	public static RotateMatrix alphabeticalMatrix(int size){
		int character = (int)'a';
		Pixel matrix[][] = new Pixel[size][size];
		for (int i=0; i<size; i++){
			for(int j=0; j<size; j++){
				String pixelValue = String.valueOf(((char)character++));
				matrix[i][j] = new Pixel<>(pixelValue);
			}
		}
		return new RotateMatrix(matrix);
	}

	public Pixel[][] rotate() {
		int start = 0;
		int end = matrix.length - 1;
		while (start < end) {
			rotateLayer(start, end);
			start++;
			end--;
		}
		return matrix;
	}

    public void printMatrix() {
        StringBuilder sb = new StringBuilder();
        sb.append("---- Matrix -----").append("\n");
        for(int i=0; i<matrix.length; i++) {
            for(int j=0; j<matrix.length; j++) {
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

    private void rotateLayer(int start, int end) {
		int length = end - start;
		int plus = start;
		int minus = end;
		for (int i = 0; i < length; i++) {
			Pixel c1 = matrix[start][plus];
			Pixel c2 = matrix[plus][end];
			Pixel c3 = matrix[end][minus];
			Pixel c4 = matrix[minus][start];

			matrix[start][plus] = c4;
			matrix[plus][end] = c1;
			matrix[end][minus] = c2;
			matrix[minus][start] = c3;

			plus++;
			minus--;
		}
	}



	static class Pixel<T> {
		private T value;

		public Pixel(T value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return value.toString();
		}

		public static <T> Pixel<T> of(T value){
			return new Pixel<>(value);
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
