package com.algorithms.graphics.toolbar;

import com.algorithms.graphics.toolbar.control.ToolbarControl;

import java.util.Collection;

/**
 * @author Mihai Alexandru
 * @date 13.10.2018
 */
public class ToolbarBuilder {

    Collection<ToolbarControl> controls;

    ToolbarControlCustomizer toolbarControlCustomizer;

    String initialInfoText;

    public ToolbarBuilder withControls(Collection<ToolbarControl> controls) {
        this.controls = controls;
        return this;
    }

    public ToolbarBuilder withToolbarControlCustomizer(ToolbarControlCustomizer toolbarControlCustomizer) {
        this.toolbarControlCustomizer = toolbarControlCustomizer;
        return this;
    }

    public ToolbarBuilder withInitialInfoText(String initialInfoText) {
        this.initialInfoText = initialInfoText;
        return this;
    }

    public Toolbar build() {
        return new Toolbar(this);
    }
}
