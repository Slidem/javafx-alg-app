package com.algorithms.graphics.toolbar;

import javafx.scene.layout.HBox;

/**
 * @author Mihai Alexandru
 * @date 13.10.2018
 */
public interface ToolbarControlCustomizer {

    void customizeToolbar(Toolbar toolbar);

    void customizeControlBar(HBox controlBar);

    void customizeControls(HBox controlBar, Toolbar toolbar);

    void customizeControlsBehaviour(HBox controlBar, Toolbar toolbar);

}
