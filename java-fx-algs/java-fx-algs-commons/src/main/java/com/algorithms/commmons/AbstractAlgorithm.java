package com.algorithms.commmons;

import com.algorithms.commmons.display.AlgorithmDisplay;
import javafx.scene.Node;

import static java.util.Objects.requireNonNull;

/**
 * @author Mihai Alexandru
 * @date 25.07.2018
 */
public abstract class AbstractAlgorithm implements Algorithm {

    protected AlgorithmDisplay algorithmDisplay;

    protected Node visualisationNode;

    public AbstractAlgorithm(Node visualisationNode) {
        this.visualisationNode = visualisationNode;
    }

    @Override
    public void showVisualizationNode() {
        algorithmDisplay.display(visualisationNode);
    }

    @Override
    public void setAlgorithmDisplay(AlgorithmDisplay algorithmDisplay) {
        this.algorithmDisplay = requireNonNull(algorithmDisplay);
    }
}
