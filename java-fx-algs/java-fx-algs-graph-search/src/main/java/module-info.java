/**
 * @author Mihai Alexandru
 * @date 25.07.2018
 */module java.fx.algs.graph.search {
    requires java.fx.algs.commons;
    requires javafx.graphics;
    requires java.fx.algorithms.toolbar;
    requires javafx.controls;

    provides com.algorithms.commmons.Algorithm
            with
                    com.algorithms.graph.visualisation.dfs.DFSAlgorithm,
                    com.algorithms.graph.visualisation.bfs.BFSAlgorithm;

}