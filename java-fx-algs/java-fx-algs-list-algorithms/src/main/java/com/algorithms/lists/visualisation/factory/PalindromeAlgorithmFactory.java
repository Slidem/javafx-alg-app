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
import com.algorithms.lists.visualisation.objects.TextFieldLimited;
import com.algorithms.lists.visualisation.states.palindrome.PalindromeGenerateListState;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.util.Collection;
import java.util.List;

import static com.algorithms.lists.visualisation.objects.PalindromeControlTypes.*;
import static com.algorithms.lists.visualisation.objects.PalindromeNodeControlUtils.disableClearList;
import static com.algorithms.lists.visualisation.objects.PalindromeNodeControlUtils.disableSearch;

/**
 * @author Mihai Alexandru
 * @date 20.10.2018
 */
@Component
public class PalindromeAlgorithmFactory {

    public AlgorithmVisualisationNode<Node<String>, ListNodeAlgorithmContext> create() {
        Toolbar toolbar = new ToolbarBuilder()
                .withControls(getControls())
                .withToolbarControlCustomizer(getCustomizer())
                .withInitialInfoText("Generate a list to check if it's a palindrome.")
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
                .setInitialState(new PalindromeGenerateListState())
                .build();
    }

    private Collection<ToolbarControl> getControls() {
        return List.of(
                ToolbarControl.of(GENERATE_LIST.name(), new Button("Generate list"), 1),
                ToolbarControl.of(LIST_TEXT_FIELD.name(), new TextFieldLimited(), 2),
                ToolbarControl.of(CHECK_PALINDROME.name(), new Button("Check if palindrome"), 3),
                ToolbarControl.of(CLEAR.name(), new Button("Clear"), 4)
        );
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
                controlBar.setAlignment(Pos.CENTER);
            }

            @Override
            public void customizeControls(HBox controlBar, Toolbar toolbar) {
                Insets insets = new Insets(0, 50, 0, 0);
                HBox.setMargin(toolbar.getControl(LIST_TEXT_FIELD.name()).getControl(), insets);
                HBox.setMargin(toolbar.getControl(CHECK_PALINDROME.name()).getControl(), insets);
                disableSearch(toolbar, true);
                disableClearList(toolbar, true);
            }

            @Override
            public void customizeControlsBehaviour(HBox controlBar, Toolbar toolbar) {
                // no additional buttons behaviour to be done. main behaviours are done in the palindrome algorithm states
            }
        };
    }

}
