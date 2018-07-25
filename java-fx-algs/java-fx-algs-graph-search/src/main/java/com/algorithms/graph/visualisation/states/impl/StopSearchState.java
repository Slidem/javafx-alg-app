package com.algorithms.graph.visualisation.states.impl;


import com.algorithms.graph.visualisation.states.GraphStateContext;
import com.algorithms.graph.visualisation.toolbars.GraphToolbar;

/**
 * @author slidem
 */
public class StopSearchState extends AbstractGraphState {

    private Runnable stopSearchRunnable;

    public StopSearchState(Runnable stopSearchRunnable) {
        this.stopSearchRunnable = stopSearchRunnable;
    }

    @Override
    public void buttonClicked(GraphToolbar.ButtonType buttonType, GraphStateContext context) {
        stopSearchRunnable.run();
        context.getToolbar().enableAll();
        context.getToolbar().changeButtonLabel(GraphToolbar.ButtonType.SEARCH, "Search");
        context.getInformationBar().resetToDefault();
        context.changeState(new DefaultState());
        context.getGraphCanvas().unvisitAllNodes();
        context.getGraphCanvas().unselectAllNodes();
    }

}
