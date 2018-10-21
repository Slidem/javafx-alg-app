package com.algorithms.graphics.toolbar;

import com.algorithms.graphics.constants.GraphicsConstants;
import com.algorithms.graphics.constants.GraphicsConstants.ControlBar;
import com.algorithms.graphics.toolbar.control.ToolbarControl;
import com.algorithms.graphics.toolbar.observers.ToolbarObserver;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BinaryOperator;

/**
 * @author Mihai Alexandru
 * @date 12.10.2018
 */
public class Toolbar extends VBox {

    private Map<String, ToolbarControl> controls;

    private InformationBar informationBar;

    private HBox controlBar;

    Toolbar(ToolbarBuilder builder) {
        setupInformationBar(builder.initialInfoText);
        setupControlBar(builder.toolbarControlCustomizer);
        setupControlBehaviour(builder.controls, builder.toolbarControlCustomizer);
        setupToolbar(builder.toolbarControlCustomizer);
    }

    public void enableAllControls() {
        controls.values().forEach(b -> b.getControl().setDisable(false));
    }

    public void disableAllExcept(String type) {
        controls.entrySet()
                .stream()
                .filter(e -> !e.getKey().equals(type))
                .map(Map.Entry::getValue)
                .forEach(b -> b.getControl().setDisable(true));
    }

    public void disableControl(String type) {
        setButtonDisable(type, true);
    }

    public void enableControl(String type) {
        setButtonDisable(type, false);
    }

    public ToolbarControl getControl(String type) {
        return controls.get(type);
    }

    public InformationBar getInformationBar() {
        return informationBar;
    }

    public void setToolbarObserver(ToolbarObserver toolbarObserver) {
        controls.values().forEach(tc -> tc.getControl().setOnMouseClicked(e -> {
            toolbarObserver.controlClicked(tc);
        }));
    }

    private void setButtonDisable(String type, boolean value) {
        controls.get(type).getControl().setDisable(value);
    }

    // ---------------------------- SETUP -----------------------------------------

    private void setupInformationBar(String defaultInfo) {
        this.informationBar = new InformationBar(defaultInfo);
    }

    private void setupControlBar(ToolbarControlCustomizer toolbarControlCustomizer) {
        controlBar = new HBox();
        controlBar.setMinHeight(ControlBar.MIN_HEIGHT);
        controlBar.setMaxHeight(ControlBar.MAX_HEIGHT);
        controlBar.setAlignment(ControlBar.ALIGMENT);
        toolbarControlCustomizer.customizeControlBar(controlBar);
    }

    private void setupControlBehaviour(Collection<ToolbarControl> ctrls, ToolbarControlCustomizer toolbarControlCustomizer) {
        controls = new HashMap<>();
        for (ToolbarControl tc : sortedControlsByOrder(ctrls)) {
            controls.put(tc.getType(), tc);
            controlBar.getChildren().add(tc.getControl());
        }
        toolbarControlCustomizer.customizeControls(controlBar, this);
        toolbarControlCustomizer.customizeControlsBehaviour(controlBar, this);
    }


    private void setupToolbar(ToolbarControlCustomizer toolbarControlCustomizer) {
        setMinHeight(GraphicsConstants.Toolbar.MIN_HEIGHT);
        setMaxHeight(GraphicsConstants.Toolbar.MAX_HEIGHT);
        toolbarControlCustomizer.customizeToolbar(this);
        getChildren().addAll(controlBar, informationBar);
    }

    private ToolbarControl[] sortedControlsByOrder(Collection<ToolbarControl> controls) {
        ToolbarControl[] controlsArray = controls.toArray(new ToolbarControl[controls.size()]);
        Arrays.sort(controlsArray, (a, b) -> a.getOrder() - b.getOrder());
        return controlsArray;
    }

    private static <T> BinaryOperator<T> throwingMerger() {
        return (u, v) -> {
            throw new IllegalStateException(String.format("Duplicate key %s", u));
        };
    }

}
