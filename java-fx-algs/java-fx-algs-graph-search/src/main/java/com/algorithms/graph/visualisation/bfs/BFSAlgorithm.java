package com.algorithms.graph.visualisation.bfs;


import com.algorithms.commmons.AbstractAlgorithm;
import com.algorithms.commmons.AlgorithmType;
import com.algorithms.graph.logic.bfs.BFSSearch;
import com.algorithms.graph.visualisation.GraphSearchAlgorithmTypes;
import com.algorithms.graph.visualisation.canvas.GraphCanvas;
import com.algorithms.graph.visualisation.nodes.GraphAlgorithmVisNode;
import com.algorithms.graph.visualisation.toolbars.GraphToolbar;

/**
 * @author slidem
 */
public class BFSAlgorithm extends AbstractAlgorithm {

    public BFSAlgorithm() {
        super(new GraphAlgorithmVisNode(new GraphToolbar(), new GraphCanvas(), BFSSearch::new));
    }

    @Override
    public AlgorithmType getType() {
        return GraphSearchAlgorithmTypes.BFS;
    }

    @Override
    public void showDecriptionNode() {
        algorithmDisplay.display(null);
    }
}
