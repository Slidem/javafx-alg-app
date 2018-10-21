package com.algorithms.app.display;

import ami.lightdi.annotations.Component;
import com.algorithms.commmons.display.AlgorithmDisplay;
import com.algorithms.commmons.display.PaneDisplay;
import javafx.scene.Node;

import java.util.logging.Logger;

import static java.util.Objects.isNull;

/**
 * @author slidem
 */
@Component
public class AlgorithmDisplayImpl implements AlgorithmDisplay {

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
