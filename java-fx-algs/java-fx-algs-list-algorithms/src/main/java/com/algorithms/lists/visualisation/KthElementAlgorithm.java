package com.algorithms.lists.visualisation;

import ami.lightdi.annotations.Component;
import ami.lightdi.annotations.Inject;
import com.algorithms.commmons.AbstractAlgorithm;
import com.algorithms.commmons.AlgorithmType;
import com.algorithms.lists.visualisation.factory.KthNodeAlgorithmFactory;

/**
 * @author Mihai Alexandru
 * @date 05.08.2018
 */
@Component
public class KthElementAlgorithm extends AbstractAlgorithm {

    @Inject
    public KthElementAlgorithm(KthNodeAlgorithmFactory kthNodeAlgorithmFactory) {
        super(kthNodeAlgorithmFactory.create());
    }

    @Override
    public AlgorithmType getType() {
        return ListAlgorithmTypes.KTH_NODE;
    }

    @Override
    public void showDescriptionNode() {
        algorithmDisplay.display(null);
    }
}
