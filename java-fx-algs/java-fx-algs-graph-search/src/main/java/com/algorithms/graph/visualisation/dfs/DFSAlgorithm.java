package com.algorithms.graph.visualisation.dfs;


import com.algorithms.commmons.AbstractAlgorithm;
import com.algorithms.commmons.AlgorithmType;
import com.algorithms.graph.logic.dfs.DFSSearch;
import com.algorithms.graph.visualisation.GraphSearchAlgorithmTypes;
import com.algorithms.graph.visualisation.canvas.GraphCanvas;
import com.algorithms.graph.visualisation.nodes.GraphAlgorithmVisNode;
import com.algorithms.graph.visualisation.toolbars.GraphToolbar;

/**
 * @author slidem
 */
public class DFSAlgorithm extends AbstractAlgorithm {

    public DFSAlgorithm() {
        super(new GraphAlgorithmVisNode(new GraphToolbar(), new GraphCanvas(), DFSSearch::new));
    }


    @Override
    public AlgorithmType getType() {
        return GraphSearchAlgorithmTypes.DFS;
    }

    @Override
    public void showDecriptionNode() {
        algorithmDisplay.display(null);
    }

}
