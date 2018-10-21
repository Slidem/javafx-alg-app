package com.algorithms.graph.visualisation.dfs;


import ami.lightdi.annotations.Component;
import ami.lightdi.annotations.Inject;
import com.algorithms.commmons.AbstractAlgorithm;
import com.algorithms.commmons.AlgorithmType;
import com.algorithms.graph.logic.dfs.DFSSearch;
import com.algorithms.graph.visualisation.GraphSearchAlgorithmTypes;
import com.algorithms.graph.visualisation.factory.GraphSearchAlgorithmFactory;

/**
 * @author slidem
 */
@Component
public class DFSAlgorithm extends AbstractAlgorithm {

    @Inject
    public DFSAlgorithm(GraphSearchAlgorithmFactory graphSearchAlgorithmFactory) {
        super(graphSearchAlgorithmFactory.create(DFSSearch::new));
    }


    @Override
    public AlgorithmType getType() {
        return GraphSearchAlgorithmTypes.DFS;
    }

    @Override
    public void showDescriptionNode() {
        algorithmDisplay.display(null);
    }

}
