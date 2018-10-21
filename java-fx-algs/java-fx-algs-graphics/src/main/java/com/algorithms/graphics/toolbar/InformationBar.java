package com.algorithms.graphics.toolbar;

import com.algorithms.utils.ImageUtils;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.util.concurrent.ConcurrentHashMap;

import static com.algorithms.graphics.constants.GraphicsConstants.InformationBar.*;

/**
 * @author slidem
 */
public class InformationBar extends HBox {

    private ImageView currentIcon;

    private Label currentMessage;

    private String defaultMessage;

    private String defaultIconName;

    private ConcurrentHashMap<String, Image> imageMap = new ConcurrentHashMap<>();

    public InformationBar(String defaultMessage) {
        this(defaultMessage, "info-icon.png");
    }

    public InformationBar(String defaultMessage, String defaultIconName) {
        this.setSpacing(SPACING);
        this.setPadding(PADDING);
        this.setMinHeight(MIN_HEIGHT);
        this.setMinWidth(MIN_WIDTH);
        this.setAlignment(ALIGMENT);
        this.defaultMessage = defaultMessage;
        this.defaultIconName = defaultIconName;
        this.currentMessage = new Label(defaultMessage);
        this.currentIcon = new ImageView(getImageFromMap(defaultIconName));
        this.getChildren().addAll(currentIcon, currentMessage);

    }

    public void changeMessage(String message) {
        this.currentMessage.setText(message);
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

    public void changeIcon(String iconName) {
        currentIcon.setImage(getImageFromMap(iconName));
    }


    // -------------------------------------------------------------------

    private Image getImageFromMap(String iconName) {
        return imageMap.computeIfAbsent(iconName, ImageUtils::getImage);
    }

}
