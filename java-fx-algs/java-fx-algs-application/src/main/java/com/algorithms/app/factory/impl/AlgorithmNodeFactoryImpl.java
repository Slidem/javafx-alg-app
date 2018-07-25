package com.algorithms.app.factory.impl;

import com.algorithms.app.factory.AlgorithmNodeFactory;
import com.algorithms.commmons.Algorithm;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import static com.algorithms.utils.ImageUtils.getImage;

/**
 * @author slidem
 */
public enum AlgorithmNodeFactoryImpl implements AlgorithmNodeFactory {

    INSTANCE;

    private static final int PANE_HEIGHT = 70;

    private static final String DESCRIPTION_ICON = "description.png";

    private static final String VISUALISATION_ICON = "visualisation.png";

    private static final String DESCRIPTION = "Description";

    private static final String VISUALISATION = "Visualisation";

    @Override
    public TitledPane createAlgorithmTitlePane(Algorithm algorithm) {
        String algName = algorithm.getType().getDescription();
        TitledPane algPane = new TitledPane();
        algPane.setText(algName);
        algPane.setContent(createAlgorithmPaneContent(algorithm));
        return algPane;
    }

    // ----------------------------------------------------------------

    private Node createAlgorithmPaneContent(Algorithm algorithm) {
        VBox algContent = new VBox();
        var data = FXCollections.observableArrayList(DESCRIPTION, VISUALISATION);
        var listView = new ListView<String>();
        listView.setItems(data);
        listView.setPrefHeight(PANE_HEIGHT);
        listView.setCellFactory(getCellFactory());
        listView.setOnMouseClicked(getListClickedEventHandler(listView, algorithm));
        algContent.getChildren().add(listView);
        return algContent;
    }

    private Callback<ListView<String>, ListCell<String>> getCellFactory() {
        Image descIcon = getImage(DESCRIPTION_ICON);
        Image visIcon = getImage(VISUALISATION_ICON);
        return param -> new ListCell<>() {
            private ImageView imageView = new ImageView();

            @Override
            public void updateItem(String name, boolean empty) {
                super.updateItem(name, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    if (name.equals(DESCRIPTION)) {
                        imageView.setImage(descIcon);
                    }
                    if (name.equals(VISUALISATION)) {
                        imageView.setImage(visIcon);
                    }
                    setText(name);
                    setGraphic(imageView);
                }
            }
        };
    }

    private EventHandler<? super MouseEvent> getListClickedEventHandler(ListView<String> listView,
                                                                        Algorithm algorithm) {

        return mouseEvent -> {
            String selectedItem = listView.getSelectionModel().getSelectedItem();
            if (DESCRIPTION.equals(selectedItem)) {
                algorithm.showDecriptionNode();
            }
            if (VISUALISATION.equals(selectedItem)) {
                algorithm.showVisualizationNode();
            }

        };
    }


}
