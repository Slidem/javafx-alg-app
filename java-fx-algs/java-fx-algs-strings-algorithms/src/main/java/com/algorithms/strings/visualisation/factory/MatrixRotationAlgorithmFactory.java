package com.algorithms.strings.visualisation.factory;

import ami.lightdi.annotations.Component;
import com.algorithms.graphics.AlgorithmNodeBuilder;
import com.algorithms.graphics.AlgorithmVisualisationNode;
import com.algorithms.graphics.canvas.Canvas;
import com.algorithms.graphics.toolbar.Toolbar;
import com.algorithms.graphics.toolbar.ToolbarBuilder;
import com.algorithms.graphics.toolbar.ToolbarControlCustomizer;
import com.algorithms.graphics.toolbar.control.ToolbarControl;
import com.algorithms.strings.visualisation.context.MatrixRotationContext;
import com.algorithms.strings.visualisation.objects.RowColumnHolder;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.algorithms.strings.visualisation.objects.Constants.Matrix.MAX_MATRIX_SIZE;
import static com.algorithms.strings.visualisation.objects.MatrixRotationControlTypes.*;

/**
 * @author Mihai Alexandru
 * @date 20.10.2018
 */
@Component
public class MatrixRotationAlgorithmFactory {


    public AlgorithmVisualisationNode<RowColumnHolder, MatrixRotationContext> create() {

        Toolbar toolbar = new ToolbarBuilder()
                .withControls(getControls())
                .withToolbarControlCustomizer(getControlsCustomizer())
                .withInitialInfoText("Generate matrix")
                .build();
        Canvas<RowColumnHolder> canvas = new Canvas<>();
        MatrixRotationContext context = new MatrixRotationContext(canvas, toolbar);
        return new AlgorithmNodeBuilder<RowColumnHolder, MatrixRotationContext>()
                .setToolbar(toolbar)
                .setCanvas(canvas)
                .setContext(context)
                .setInitialState(null)
                .build();
    }

    private Collection<ToolbarControl> getControls() {
        return List.of(
                ToolbarControl.of(GENERATE_MATRIX_BUTTON.name(), new Button("Generate matrix"), 1),
                ToolbarControl.of(MATRIX_SIZE_LABEL.name(), new Label("Matrix size: "), 2),
                ToolbarControl.of(MATRIX_SIZE_COMBO.name(), getMatrixComboBox(), 3),
                ToolbarControl.of(ROTATE_MATRIX_BUTTON.name(), new Button("Rotate"), 4),
                ToolbarControl.of(CLEAR_BUTTON.name(), new Button("Clear"), 5)
        );
    }

    private ToolbarControlCustomizer getControlsCustomizer() {
        return new ToolbarControlCustomizer() {
            @Override
            public void customizeToolbar(Toolbar toolbar) {
                toolbar.setSpacing(5);
                toolbar.setPadding(new Insets(10));
            }

            @Override
            public void customizeControlBar(HBox controlBar) {
                controlBar.setSpacing(5);
                controlBar.setPadding(new Insets(15));
            }

            @Override
            public void customizeControls(HBox controlBar, Toolbar toolbar) {
                Insets insets = new Insets(0, 50, 0, 0);
                HBox.setMargin(toolbar.getControl(MATRIX_SIZE_COMBO.name()).getControl(), insets);
                HBox.setMargin(toolbar.getControl(ROTATE_MATRIX_BUTTON.name()).getControl(), insets);
            }

            @Override
            public void customizeControlsBehaviour(HBox controlBar, Toolbar toolbar) {
                //nothing to do here
            }
        };
    }

    private ComboBox<Integer> getMatrixComboBox() {
        ComboBox<Integer> combo = new ComboBox<>();
        combo.getItems().addAll(getMatrixComboItems());
        combo.getSelectionModel().selectFirst();
        return combo;
    }

    private List<Integer> getMatrixComboItems() {
        var listSizeItems = new ArrayList<Integer>();
        for (int i = 0; i < MAX_MATRIX_SIZE; i++) {
            listSizeItems.add(i);
        }
        return listSizeItems;
    }


}
