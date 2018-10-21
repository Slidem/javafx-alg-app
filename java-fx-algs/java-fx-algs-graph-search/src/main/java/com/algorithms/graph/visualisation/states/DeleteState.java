package com.algorithms.graph.visualisation.states;


import com.algorithms.graph.logic.Node;
import com.algorithms.graph.visualisation.context.GraphSearchAlgorithmContext;
import com.algorithms.graphics.canvas.nodes.CanvasNode;

/**
 * @author slidem
 */
public class DeleteState extends AbstractGraphState {

    @Override
    public void canvasNodeClicked(CanvasNode<Node<String>> canvasNode, GraphSearchAlgorithmContext context) {
        context.getToolbar().getInformationBar().resetToDefault();
        context.getToolbar().enableAllControls();
        context.getCanvas().deleteNode(canvasNode);
        context.changeState(new DefaultState());
    }
}
