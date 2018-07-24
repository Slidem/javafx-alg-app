package com.algorithms.utils;

import javafx.scene.image.Image;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author Mihai Alexandru
 * @date 24.07.2018
 */
public class ImageUtils {

    private ImageUtils() {
    }

    public static Image getImage(String name) {
        File imageFile;
        try {
            imageFile = ClassFinder.getFileFromPackage("com.algorithms.app", name);
            return new Image(new FileInputStream(imageFile));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
