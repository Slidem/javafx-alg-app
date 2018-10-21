package com.algorithms.lists.visualisation;

import com.algorithms.commmons.AlgorithmType;

/**
 * @author Mihai Alexandru
 * @date 05.08.2018
 */
public enum ListAlgorithmTypes implements AlgorithmType {

    KTH_NODE("Kth Node", "Kth Node"),

    PALINDROME("Palindrome list", "Palindrome list");

    private String algName;

    private String description;

    ListAlgorithmTypes(String algName, String description) {
        this.algName = algName;
        this.description = description;
    }

    @Override
    public String getName() {
        return algName;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
