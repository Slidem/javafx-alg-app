package com.algorithms.lists.visualisation;

import ami.lightdi.annotations.Component;
import ami.lightdi.annotations.Inject;
import com.algorithms.commmons.AbstractAlgorithm;
import com.algorithms.commmons.AlgorithmType;
import com.algorithms.lists.visualisation.factory.PalindromeAlgorithmFactory;

/**
 * @author Mihai Alexandru
 * @date 08.08.2018
 */
@Component
public class PalindromeAlgorithm extends AbstractAlgorithm {

    @Inject
    public PalindromeAlgorithm(PalindromeAlgorithmFactory palindromeAlgorithmFactory) {
        super(palindromeAlgorithmFactory.create());
    }

    @Override
    public AlgorithmType getType() {
        return ListAlgorithmTypes.PALINDROME;
    }

    @Override
    public void showDescriptionNode() {
        algorithmDisplay.display(null);
    }
}
