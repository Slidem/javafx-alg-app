package com.algorithms.strings.visualisation.objects;

import javafx.scene.paint.Color;

/**
 * @author Mihai Alexandru
 * @date 10.08.2018
 */
public final class Constants {

    public static final class Matrix {

        public static final int DEFAULT_SQUARE_SIDE = 50;

        public static final Color DEFAULT_NODE_COLOR = Color.GOLD;

        public static final int MAX_MATRIX_SIZE = 10;

        private Matrix() {
        }

    }

    public static final class Canvas {

        public static final int MAX_HEIGHT = 640;

        public static final int MIN_HEIGHT = 610;

        private Canvas() {
        }
    }

    private Constants() {
    }
}
