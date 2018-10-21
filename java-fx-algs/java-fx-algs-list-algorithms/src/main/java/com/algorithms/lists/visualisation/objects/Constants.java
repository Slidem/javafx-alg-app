package com.algorithms.lists.visualisation.objects;

import javafx.scene.paint.Color;

/**
 * @author Mihai Alexandru
 * @date 02.08.2018
 */
public final class Constants {

    private Constants() {
    }

    public static class Node {

        public static final double DEFAULT_SQUARE_SIDE = 40.0;

        public static final Color DEFAULT_COLOR = Color.GOLD;

        public static final int NODE_SPACING = 15;

        private Node() {
        }

    }

    public static class Canvas {

        public static final double MIN_HEIGHT = 610;

        public static final double MAX_HEIGHT = 640;

        public static final int MAX_LIST_SIZE = 15;

        public static final int MIN_LIST_SIZE = 1;


    }


}
