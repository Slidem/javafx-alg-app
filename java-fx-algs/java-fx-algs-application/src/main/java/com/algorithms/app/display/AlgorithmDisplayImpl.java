package com.algorithms.app.display;

import com.algorithms.commmons.display.AlgorithmDisplay;
import com.algorithms.commmons.display.PaneDisplay;
import javafx.scene.Node;

import java.util.logging.Logger;

import static java.util.Objects.isNull;

/**
 * @author slidem
 */
public enum AlgorithmDisplayImpl implements AlgorithmDisplay {

    INSTANCE;

    private static final Logger logger = Logger.getGlobal();

    private PaneDisplay rootPaneDisplay;

    @Override
    public void display(Node node) {
        if (isNull(node)) {
            logger.info("No node to display");
            return;
        }
        rootPaneDisplay.display(node);
    }

    @Override
    public void rootPaneDisplay(PaneDisplay rootPaneDisplay) {
        this.rootPaneDisplay = rootPaneDisplay;
    }

}
