package com.algorithms.strings.visualisation.factory;

import com.algorithms.graphics.canvas.Canvas;
import com.algorithms.strings.visualisation.objects.RowColumnHolder;
import com.algorithms.utils.geometry.Point;

import static com.algorithms.strings.visualisation.objects.Constants.Matrix.DEFAULT_SQUARE_SIDE;

/**
 * @author Mihai Alexandru
 * @date 20.10.2018
 */
public class MatrixDrawer {

    public void drawMatrix(int size, Canvas<RowColumnHolder> canvas) {
        Point p = getStartingPoint(size, canvas);
        int c = 0;
        for (int i = 0; i < size; i++) {
            double originalX = p.getX();
            for (int j = 0; j < size; j++) {
                RowColumnHolder rowColumnHolder = new RowColumnHolder(i, j);
                canvas.drawNode(p, new MatrixNodeFactory(rowColumnHolder));
                double x = p.getX() + DEFAULT_SQUARE_SIDE;
                p = new Point(x, p.getY());
            }
            double y = p.getY() + DEFAULT_SQUARE_SIDE;
            p = new Point(originalX, y);
        }
    }

    private Point getStartingPoint(int matrixSize, Canvas<RowColumnHolder> canvas) {
        double canvasWidth = canvas.getWidth();
        double canvasHight = canvas.getHeight();
        double substract = 0;
        if (matrixSize != 1) {
            substract = DEFAULT_SQUARE_SIDE * (matrixSize / 2) + (matrixSize % 2 == 1 ? DEFAULT_SQUARE_SIDE / 2 : 0);
        }
        double startX = canvasWidth / 2 - substract;
        double startY = canvasHight / 2 - substract;
        return new Point(startX, startY);
    }


}
