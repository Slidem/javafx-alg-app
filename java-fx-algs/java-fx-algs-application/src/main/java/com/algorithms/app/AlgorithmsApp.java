package com.algorithms.app;

import com.algorithms.app.display.AlgorithmDisplayImpl;
import com.algorithms.app.factory.AlgorithmNodeFactory;
import com.algorithms.app.factory.impl.AlgorithmNodeFactoryImpl;
import com.algorithms.app.store.impl.AlgorithmsStoreImpl;
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

    private static final AlgorithmNodeFactory nodeFactory = AlgorithmNodeFactoryImpl.INSTANCE;

    private static final Logger logger = Logger.getGlobal();

    @Override
    public void init() throws Exception {
        logger.info("----- STARTING ALGS APPLICATION -----");
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

        prepareAlgorithms(vbox, root);

        root.getChildren().add(vbox);

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void prepareAlgorithms(Pane leftSideMenu, Pane displaySide) {
        AlgorithmDisplayImpl.INSTANCE.rootPaneDisplay(node -> {
            displaySide.getChildren().clear();
            displaySide.getChildren().addAll(leftSideMenu, node);
        });
        var algorithms = AlgorithmsStoreImpl.INSTANCE.getAll();
        var algNodes = algorithms.stream().map(nodeFactory::createAlgorithmTitlePane).collect(toList());
        leftSideMenu.getChildren().addAll(algNodes);
    }


    // ----- MAIN METHOD

    public static void main(String[] args) {
        AlgorithmsApp.launch(args);
    }

}
