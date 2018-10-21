package com.algorithms.graphics.constants;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * @author Mihai Alexandru
 * @date 10.10.2018
 */
public final class GraphicsConstants {

    private GraphicsConstants() {
    }

    public static final class AlgorithmVisualisation {

        public static final int PREF_WIDTH = 790;

        public static final int PREF_HEIGHT = 700;

    }

    public static final class ControlBar {

        public static final int MIN_HEIGHT = 60;

        public static final int MAX_HEIGHT = 120;

        public static final Pos ALIGMENT = Pos.CENTER;

        private ControlBar() {
        }

    }

    public static final class InformationBar {

        public static final int SPACING = 10;

        public static final Insets PADDING = new Insets(15);

        public static final int MIN_HEIGHT = 40;

        public static final int MIN_WIDTH = 600;

        public static final Pos ALIGMENT = Pos.CENTER;

        private InformationBar() {
        }
    }

    public static final class Toolbar {

        public static final int MIN_HEIGHT = 150;

        public static final int MAX_HEIGHT = 220;

        private Toolbar() {
        }

    }

    public static final class Canvas {

        public static final int MIN_HEIGHT = 610;

        public static final int MAX_HEIGHT = 640;

        public static final Border BORDER = new Border(
                new BorderStroke(
                        Color.BLACK,
                        BorderStrokeStyle.SOLID,
                        CornerRadii.EMPTY,
                        BorderWidths.DEFAULT));

        public static final Background BACKGROUND = new Background(new BackgroundFill(Color.WHITE, null, null));

        private Canvas() {
        }
    }
}
