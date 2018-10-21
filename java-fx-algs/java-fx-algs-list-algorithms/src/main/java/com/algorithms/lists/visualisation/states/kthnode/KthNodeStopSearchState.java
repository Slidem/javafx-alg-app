package com.algorithms.lists.visualisation.states.kthnode;

import com.algorithms.graphics.toolbar.control.ToolbarControl;
import com.algorithms.lists.visualisation.context.ListNodeAlgorithmContext;
import com.algorithms.lists.visualisation.states.ListAbstractState;

/**
 * @author Mihai Alexandru
 * @date 19.10.2018
 */
public class KthNodeStopSearchState extends ListAbstractState {

    private Runnable stopSearchRunnable;

    public KthNodeStopSearchState(Runnable stopSearchRunnable) {
        this.stopSearchRunnable = stopSearchRunnable;
    }

    @Override
    public void controlClicked(ToolbarControl control, ListNodeAlgorithmContext context) {
        stopSearchRunnable.run();
        context.changeState(new KthNodeGenerateListState());
    }
}
