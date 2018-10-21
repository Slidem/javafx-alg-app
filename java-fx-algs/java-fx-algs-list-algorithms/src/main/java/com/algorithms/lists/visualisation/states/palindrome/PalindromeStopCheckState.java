package com.algorithms.lists.visualisation.states.palindrome;

import com.algorithms.graphics.toolbar.control.ToolbarControl;
import com.algorithms.lists.visualisation.context.ListNodeAlgorithmContext;
import com.algorithms.lists.visualisation.states.ListAbstractState;

/**
 * @author Mihai Alexandru
 * @date 20.10.2018
 */
public class PalindromeStopCheckState extends ListAbstractState {

    private Runnable stopRunnable;

    public PalindromeStopCheckState(Runnable stopRunnable) {
        this.stopRunnable = stopRunnable;
    }

    @Override
    public void controlClicked(ToolbarControl control, ListNodeAlgorithmContext context) {
        stopRunnable.run();
        context.changeState(new PalindromeGenerateListState());
    }
}
