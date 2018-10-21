package com.algorithms.graph.visualisation.states;


import com.algorithms.graph.visualisation.context.GraphSearchAlgorithmContext;
import com.algorithms.graph.visualisation.controls.ButtonType;
import com.algorithms.graphics.canvas.nodes.CanvasNode;
import com.algorithms.graphics.toolbar.control.ToolbarControl;
import javafx.scene.control.Button;

/**
 * @author slidem
 */
public class StopSearchState extends AbstractGraphState {

    private Runnable stopSearchRunnable;

    public StopSearchState(Runnable stopSearchRunnable) {
        this.stopSearchRunnable = stopSearchRunnable;
    }

    @Override
    public void controlClicked(ToolbarControl control, GraphSearchAlgorithmContext context) {
        stopSearchRunnable.run();
        context.getToolbar().enableAllControls();
        changeSearchButtonLabel(context, "Search");
        context.getToolbar().getInformationBar().resetToDefault();
        context.changeState(new DefaultState());
        context.getCanvas().getAllNodes().forEach(CanvasNode::reset);
    }

    private void changeSearchButtonLabel(GraphSearchAlgorithmContext context, String text) {
        context.getToolbar().getControl(ButtonType.SEARCH.name()).getControlAs(Button.class).setText(text);
    }
}
