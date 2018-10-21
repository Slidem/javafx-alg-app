package com.algorithms.graph.visualisation.bfs;


import ami.lightdi.annotations.Component;
import ami.lightdi.annotations.Inject;
import com.algorithms.commmons.AbstractAlgorithm;
import com.algorithms.commmons.AlgorithmType;
import com.algorithms.graph.logic.bfs.BFSSearch;
import com.algorithms.graph.visualisation.GraphSearchAlgorithmTypes;
import com.algorithms.graph.visualisation.factory.GraphSearchAlgorithmFactory;

/**
 * @author slidem
 */
@Component
public class BFSAlgorithm extends AbstractAlgorithm {

    @Inject
    public BFSAlgorithm(GraphSearchAlgorithmFactory graphSearchAlgorithmFactory) {
        super(graphSearchAlgorithmFactory.create(BFSSearch::new));
    }

    @Override
    public AlgorithmType getType() {
        return GraphSearchAlgorithmTypes.BFS;
    }

    @Override
    public void showDescriptionNode() {
        algorithmDisplay.display(null);
    }
}
