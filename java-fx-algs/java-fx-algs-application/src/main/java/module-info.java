/**
 * @author Mihai Alexandru
 * @date 25.07.2018
 */module java.fx.algs.application {
    requires javafx.graphics;
    requires java.logging;
    requires javafx.controls;
    requires java.fx.algs.commons;
    requires java.fx.utils;
    requires java.fx.algs.graph.search;
    uses com.algorithms.commmons.Algorithm;
    exports com.algorithms.app;
}