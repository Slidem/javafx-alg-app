package com.algorithms.lists.visualisation.states.kthnode;

import com.algorithms.graphics.toolbar.control.ToolbarControl;
import com.algorithms.lists.node.Node;
import com.algorithms.lists.visualisation.context.ListNodeAlgorithmContext;
import com.algorithms.lists.visualisation.states.ListAbstractState;

/**
 * @author Mihai Alexandru
 * @date 19.10.2018
 */
public class KthNodeStopSearchState extends ListAbstractState {

    private Runnable stopSearchRunnable;

    private Node<String> headNode;

    public KthNodeStopSearchState(Runnable stopSearchRunnable, Node<String> headNode) {
        this.stopSearchRunnable = stopSearchRunnable;
        this.headNode = headNode;
    }

    @Override
    public void controlClicked(ToolbarControl control, ListNodeAlgorithmContext context) {
        stopSearchRunnable.run();
        context.changeState(new KthNodeListGeneratedState(headNode));
    }
}
