package com.algorithms.graph.visualisation;

import com.algorithms.commmons.AlgorithmType;

/**
 * @author Mihai Alexandru
 * @date 25.07.2018
 */
public enum GraphSearchAlgorithmTypes implements AlgorithmType {

    BFS("BFS", "BFS search"), DFS("DFS", "DFS search");

    private final String name;

    private final String description;

    GraphSearchAlgorithmTypes(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
