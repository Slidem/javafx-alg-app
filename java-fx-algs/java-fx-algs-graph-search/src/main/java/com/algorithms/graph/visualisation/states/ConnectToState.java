package com.algorithms.graph.visualisation.states;

import com.algorithms.graph.logic.Node;
import com.algorithms.graph.visualisation.context.GraphSearchAlgorithmContext;
import com.algorithms.graphics.canvas.nodes.CanvasNode;

/**
 * @author slidem
 */
public class ConnectToState extends AbstractGraphState {

    private CanvasNode<Node<String>> from;

    public ConnectToState(CanvasNode<Node<String>> from) {
        this.from = from;
    }

    @Override
    public void canvasNodeClicked(CanvasNode<Node<String>> canvasNode, GraphSearchAlgorithmContext context) {
        context.getToolbar().enableAllControls();
        context.getToolbar().getInformationBar().resetToDefault();
        from.addConnection(canvasNode);
        from.getValue().addNeighbour(canvasNode.getValue());
        from.reset();
        canvasNode.reset();
        context.changeState(new DefaultState());
    }

}
