package com.algorithms.strings.visualisation;

import ami.lightdi.annotations.Component;
import ami.lightdi.annotations.Inject;
import com.algorithms.commmons.AbstractAlgorithm;
import com.algorithms.commmons.AlgorithmType;
import com.algorithms.strings.visualisation.factory.MatrixRotationAlgorithmFactory;

/**
 * @author Mihai Alexandru
 * @date 12.08.2018
 */
@Component
public class MatrixRotationAlgorithm extends AbstractAlgorithm {

    @Inject
    public MatrixRotationAlgorithm(MatrixRotationAlgorithmFactory factory) {
        super(factory.create());
    }

    @Override
    public AlgorithmType getType() {
        return StringsAlgorithmTypes.ROTATE_MATRIX;
    }

    @Override
    public void showDescriptionNode() {
        algorithmDisplay.display(null);
    }
}
