package com.algorithms.strings.visualisation;

import com.algorithms.commmons.AlgorithmType;

/**
 * @author Mihai Alexandru
 * @date 12.08.2018
 */
public enum StringsAlgorithmTypes implements AlgorithmType {

    ROTATE_MATRIX("Matrix rotation", "Matrix rotation");

    private String name;

    private String description;


    private StringsAlgorithmTypes(String name, String description) {
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
