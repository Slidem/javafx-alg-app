package com.algorithms.toolbar.information;

import com.algorithms.utils.ImageUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author slidem
 */
public class InformationBar extends HBox {

    private ImageView currentIcon;

    private Label currentMessage;

    private String defaultMessage;

    private String defaultIconName;

    private ConcurrentHashMap<String, Image> imageMap = new ConcurrentHashMap<>();

    public InformationBar(String defaultMessage, String defaultIconName) {
        this.setSpacing(10);
        this.setPadding(new Insets(15));
        this.setMinHeight(40);
        this.setMinWidth(600);
        this.setAlignment(Pos.CENTER);
        this.defaultMessage = defaultMessage;
        this.defaultIconName = defaultIconName;
        this.currentMessage = new Label(defaultMessage);
        this.currentIcon = new ImageView(getImageFromMap(defaultIconName));
        this.getChildren().addAll(currentIcon, currentMessage);
    }

    public void changeMessage(String message) {
        this.currentMessage.setText(message);
    }

    public void changeIcon(String iconName) {
        currentIcon.setImage(getImageFromMap(iconName));
    }

    public void changeToInfoIcon() {
        changeIcon("info-icon.png");
    }

    public void changeToAttentionIcon() {
        changeIcon("attention-icon.png");
    }

    public void changeToErrorIcon() {
        changeIcon("error-icon.png");
    }

    public void resetToDefault() {
        changeMessage(defaultMessage);
        changeIcon(defaultIconName);
    }

    // -------------------------------------------------------------------

    private Image getImageFromMap(String iconName) {
        return imageMap.computeIfAbsent(iconName, ImageUtils::getImage);
    }

}
