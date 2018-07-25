package com.algorithms.graph.visualisation.states.impl;

import com.algorithms.graph.visualisation.objects.Point;
import com.algorithms.graph.visualisation.states.GraphStateContext;

/**
 * @author slidem
 */
public class CreateState extends AbstractGraphState {

    @Override
    public void canvasClicked(Point point, GraphStateContext context) {
        context.getToolbar().getInformationBar().resetToDefault();
        context.getToolbar().enableAll();
        context.getGraphCanvas().drawNode(point);
        context.changeState(new DefaultState());

    }

}
