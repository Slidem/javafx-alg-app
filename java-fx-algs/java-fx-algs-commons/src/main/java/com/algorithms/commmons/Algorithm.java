package com.algorithms.commmons;

import com.algorithms.commmons.display.AlgorithmDisplay;

/**
 * @author Mihai Alexandru
 * @date 25.07.2018
 */
public interface Algorithm {

    AlgorithmType getType();

    void showDescriptionNode();

    void showVisualizationNode();

    void setAlgorithmDisplay(AlgorithmDisplay algorithmDisplay);
}
