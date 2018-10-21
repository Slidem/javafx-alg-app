package com.algorithms.graph.visualisation.states;

import com.algorithms.graph.visualisation.context.GraphSearchAlgorithmContext;
import com.algorithms.utils.geometry.Point;

/**
 * @author slidem
 */
public class CreateState extends AbstractGraphState {

    @Override
    public void canvasClicked(Point point, GraphSearchAlgorithmContext context) {
        context.getToolbar().getInformationBar().resetToDefault();
        context.getToolbar().enableAllControls();
        context.getCanvas().drawNode(point, context.getCanvasNodeFactory());
        context.changeState(new DefaultState());
    }

}
