package com.algorithms.graph.visualisation.states;

import com.algorithms.graph.logic.Node;
import com.algorithms.graph.visualisation.context.GraphSearchAlgorithmContext;
import com.algorithms.graphics.canvas.nodes.CanvasNode;
import javafx.scene.paint.Color;

/**
 * @author slidem
 */
public class SearchFromState extends AbstractGraphState {

    @Override
    public void canvasNodeClicked(CanvasNode<Node<String>> canvasNode, GraphSearchAlgorithmContext context) {
        canvasNode.select(Color.GOLDENROD);
        context.changeState(new SearchToState(canvasNode));
        context.getToolbar().getInformationBar().changeMessage("Click on a target node you wish to search to");
    }
}
