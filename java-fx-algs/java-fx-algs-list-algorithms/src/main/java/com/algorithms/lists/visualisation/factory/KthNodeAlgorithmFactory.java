package com.algorithms.lists.visualisation.factory;

import ami.lightdi.annotations.Component;
import com.algorithms.graphics.AlgorithmNodeBuilder;
import com.algorithms.graphics.AlgorithmVisualisationNode;
import com.algorithms.graphics.canvas.Canvas;
import com.algorithms.graphics.toolbar.Toolbar;
import com.algorithms.graphics.toolbar.ToolbarBuilder;
import com.algorithms.graphics.toolbar.ToolbarControlCustomizer;
import com.algorithms.graphics.toolbar.control.ToolbarControl;
import com.algorithms.lists.node.Node;
import com.algorithms.lists.visualisation.context.ListNodeAlgorithmContext;
import com.algorithms.lists.visualisation.states.kthnode.KthNodeGenerateListState;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.Collection;
import java.util.List;

import static com.algorithms.lists.visualisation.objects.Constants.Canvas.MAX_LIST_SIZE;
import static com.algorithms.lists.visualisation.objects.KthNodeControlTypes.*;
import static com.algorithms.lists.visualisation.objects.ListUtils.getMinMaxList;

/**
 * @author Mihai Alexandru
 * @date 17.10.2018
 */
@Component
public class KthNodeAlgorithmFactory {

    /**
     * @return Kth node algorithm visualisation node instantce.
     */
    public AlgorithmVisualisationNode<Node<String>, ListNodeAlgorithmContext> create() {
        Toolbar toolbar = new ToolbarBuilder()
                .withControls(getControls())
                .withToolbarControlCustomizer(getCustomizer())
                .withInitialInfoText("Generate a list to search the kth end node.")
                .build();
        Canvas<Node<String>> canvas = new Canvas<>();
        ListFactory listFactory = ListFactory.of(canvas);
        ListNodeAlgorithmContext context = new ListNodeAlgorithmContext.Builder()
                .withToolbar(toolbar)
                .withCanvas(canvas)
                .withListFactory(listFactory)
                .build();
        return new AlgorithmNodeBuilder<Node<String>, ListNodeAlgorithmContext>()
                .setToolbar(toolbar)
                .setCanvas(canvas)
                .setContext(context)
                .setInitialState(new KthNodeGenerateListState())
                .build();
    }

    // --------------------------------------------------------------------------------------

    private Collection<ToolbarControl> getControls() {
        return List.of(
                ToolbarControl.of(GENERATE_LIST_BUTTON.name(), new Button("Generate list"), 1),
                ToolbarControl.of(LIST_SIZE_LABEL.name(), new Label("List size: "), 2),
                ToolbarControl.of(LIST_SIZE_COMBO.name(), getComboBox(getMinMaxList(1, MAX_LIST_SIZE)), 3),
                ToolbarControl.of(SEARCH_BUTTON.name(), new Button("Search kth node: "), 4),
                ToolbarControl.of(KTH_ELEMENT_SEARCH.name(), getComboBox(List.of(1)), 5),
                ToolbarControl.of(CLEAR_BUTTON.name(), new Button("Clear"), 6)
        );
    }

    private ComboBox<Integer> getComboBox(List<Integer> items) {
        ComboBox<Integer> combo = new ComboBox<>();
        combo.getItems().addAll(items);
        combo.getSelectionModel().selectFirst();
        return combo;
    }


    private ToolbarControlCustomizer getCustomizer() {
        return new ToolbarControlCustomizer() {
            @Override
            public void customizeToolbar(Toolbar toolbar) {
                toolbar.setSpacing(5);
                toolbar.setPadding(new Insets(10));
            }

            @Override
            public void customizeControlBar(HBox controlBar) {
                controlBar.setPadding(new Insets(15));
                controlBar.setSpacing(5);
            }

            @Override
            public void customizeControls(HBox controlBar, Toolbar toolbar) {
                Insets insets = new Insets(0, 50, 0, 0);
                HBox.setMargin(toolbar.getControl(LIST_SIZE_COMBO.name()).getControl(), insets);
                HBox.setMargin(toolbar.getControl(KTH_ELEMENT_SEARCH.name()).getControl(), insets);

                toolbar.getControl(SEARCH_BUTTON.name()).getControl().setDisable(true);
                toolbar.getControl(KTH_ELEMENT_SEARCH.name()).getControl().setDisable(true);
                toolbar.getControl(CLEAR_BUTTON.name()).getControl().setDisable(true);
            }

            @Override
            public void customizeControlsBehaviour(HBox controlBar, Toolbar toolbar) {
                ComboBox<Integer> listSizeCombo = toolbar.getControl(LIST_SIZE_COMBO.name()).getControlAs(ComboBox.class);
                ComboBox<Integer> kthElementSearch = toolbar.getControl(KTH_ELEMENT_SEARCH.name()).getControlAs(ComboBox.class);
                // change kth node search based on selected list size;
                listSizeCombo.valueProperty().addListener((observable, oldValue, newValue) -> {
                    int maxKthElement = newValue;
                    List<Integer> kthElementList = getMinMaxList(1, maxKthElement);
                    kthElementSearch.getItems().clear();
                    kthElementSearch.getItems().setAll(kthElementList);
                    kthElementSearch.getSelectionModel().selectFirst();
                });
            }
        };
    }

}
