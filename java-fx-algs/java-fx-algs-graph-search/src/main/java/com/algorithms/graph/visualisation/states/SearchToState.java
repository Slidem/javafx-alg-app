package com.algorithms.graph.visualisation.states;


import com.algorithms.graph.logic.Node;
import com.algorithms.graph.visualisation.context.GraphSearchAlgorithmContext;
import com.algorithms.graph.visualisation.controls.ButtonType;
import com.algorithms.graphics.canvas.nodes.CanvasNode;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class SearchToState extends AbstractGraphState {

    private final CanvasNode<Node<String>> from;

    public SearchToState(CanvasNode<Node<String>> from) {
        this.from = from;
    }

    @Override
    public void canvasNodeClicked(CanvasNode<Node<String>> canvasNode, GraphSearchAlgorithmContext context) {
        canvasNode.select(Color.GOLDENROD);
        context.changeState(new StartSearchState(from, canvasNode));
        context.getToolbar().getInformationBar().changeMessage("Click on \"Start Search\" to start the graph search.");
        context.getToolbar().getControl(ButtonType.SEARCH.name()).getControlAs(Button.class).setText("Start Search");
    }

}
