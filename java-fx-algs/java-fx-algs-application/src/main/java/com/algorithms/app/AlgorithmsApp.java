package com.algorithms.app;

import ami.lightdi.LightDI;
import com.algorithms.app.display.AlgorithmDisplayImpl;
import com.algorithms.app.factory.AlgorithmNodeFactory;
import com.algorithms.app.factory.impl.AlgorithmNodeFactoryImpl;
import com.algorithms.app.store.AlgorithmsStore;
import com.algorithms.app.store.impl.AlgorithmsStoreImpl;
import com.algorithms.commmons.display.AlgorithmDisplay;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.logging.Logger;

import static java.util.stream.Collectors.toList;

/**
 * @author Mihai Alexandru
 */
public class AlgorithmsApp extends Application {

    private static final Logger logger = Logger.getGlobal();

    private AlgorithmNodeFactory algorithmNodeFactory;

    private AlgorithmsStore algorithmsStore;

    private AlgorithmDisplay algorithmDisplay;

    @Override
    public void init() throws Exception {
        logger.info("----- STARTING ALGS APPLICATION -----");

        LightDI.init("com.algorithms");

        algorithmNodeFactory = LightDI.getBean(AlgorithmNodeFactoryImpl.class);
        algorithmsStore = LightDI.getBean(AlgorithmsStoreImpl.class);
        algorithmDisplay = LightDI.getBean(AlgorithmDisplayImpl.class);
    }

    @Override
    public void stop() throws Exception {
        logger.info("----- STOPING ALGS APPLICATION -----");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setWidth(1000);
        primaryStage.setHeight(800);
        primaryStage.setTitle("Algorithms applicaton");

        HBox root = new HBox();

        VBox vbox = new VBox(5);
        vbox.setPadding(new Insets(10));
        vbox.setMaxHeight(780);
        vbox.setMaxWidth(200);

        prepareComponentScan();
        prepareAlgorithms(vbox, root);

        root.getChildren().add(vbox);

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void prepareComponentScan() {
        LightDI.init("com.algorithms");
    }

    private void prepareAlgorithms(Pane leftSideMenu, Pane displaySide) {
        algorithmDisplay.rootPaneDisplay(node -> {
            displaySide.getChildren().clear();
            displaySide.getChildren().addAll(leftSideMenu, node);
        });
        var algorithms = algorithmsStore.getAll();
        var algNodes = algorithms.stream().map(algorithmNodeFactory::createAlgorithmTitlePane).collect(toList());
        leftSideMenu.getChildren().addAll(algNodes);
    }

    // ----- MAIN METHOD

    public static void main(String[] args) {
        AlgorithmsApp.launch(args);
    }

}
