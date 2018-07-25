package com.algorithms.graph.visualisation.toolbars;

import com.algorithms.graph.visualisation.observers.GraphAlgVisualisationObserver;
import com.algorithms.toolbar.information.InformationBar;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.EnumMap;
import java.util.Map;

/**
 * @author slidem
 */
public class GraphToolbar extends VBox {

    private GraphAlgVisualisationObserver graphObserver;

    private Map<ButtonType, Button> buttonMap;

    private InformationBar informationBar;

    public GraphToolbar() {
        super();
        setup();
    }

    public static GraphToolbar newInstance() {
        return new GraphToolbar();
    }

    public static GraphToolbar of(GraphAlgVisualisationObserver observer) {
        GraphToolbar gtb = new GraphToolbar();
        gtb.setGraphObserver(observer);
        return gtb;
    }

    private void setup() {
        informationBar = new InformationBar("Create nodes to form a graph.", "info-icon.png");
        HBox buttonsBar = new HBox();
        buttonsBar.setMinHeight(60);
        buttonsBar.setMaxHeight(120);
        buttonsBar.setPadding(new Insets(15));
        buttonsBar.setSpacing(30);
        buttonsBar.setAlignment(Pos.CENTER);
        buttonMap = new EnumMap<>(ButtonType.class);
        buttonMap.put(ButtonType.NEW, new Button("Create"));
        buttonMap.put(ButtonType.CONNECT, new Button("Connect"));
        buttonMap.put(ButtonType.SEARCH, new Button("Search"));
        buttonMap.put(ButtonType.DELETE, new Button("Delete"));
        buttonMap.values().forEach(b -> buttonsBar.getChildren().add(b));
        setMinHeight(150);
        setMaxHeight(220);
        setSpacing(5);
        setPadding(new Insets(10));
        getChildren().addAll(buttonsBar, informationBar);
    }

    public void enableAll() {
        buttonMap.values().forEach(b -> b.setDisable(false));
    }

    public void disableAllExcept(ButtonType type) {
        buttonMap.entrySet().stream().filter(e -> e.getKey() != type).map(Map.Entry::getValue).forEach(b -> b.setDisable(true));
    }

    public void disableButton(ButtonType type) {
        buttonMap.get(type).setDisable(true);
    }

    public void enableButton(ButtonType type) {
        buttonMap.get(type).setDisable(false);
    }

    public Button getButton(ButtonType type) {
        return buttonMap.get(type);
    }

    public InformationBar getInformationBar() {
        return informationBar;
    }

    public void setGraphObserver(GraphAlgVisualisationObserver observer) {
        graphObserver = observer;
        setupButtonBehaviours();
    }

    public void changeButtonLabel(ButtonType type, String text) {
        buttonMap.get(type).setText(text);
    }


    private void setupButtonBehaviours() {
        for (Map.Entry<ButtonType, Button> entry : buttonMap.entrySet()) {
            entry.getValue().setOnMouseClicked(mouseEvent -> graphObserver.buttonClicked(entry.getKey()));
        }
    }

    public enum ButtonType {
        NEW, DELETE, CONNECT, SEARCH;
    }

}
