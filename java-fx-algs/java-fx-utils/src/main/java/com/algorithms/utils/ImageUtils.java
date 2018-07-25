package com.algorithms.utils;

import com.algorithms.utils.exceptions.ImageNotFoundException;
import javafx.scene.image.Image;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Mihai Alexandru
 * @date 24.07.2018
 */
public class ImageUtils {

    private ImageUtils() {
    }

    public static Image getImage(String name) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream is = classLoader.getResourceAsStream(name)) {
            return new Image(is);
        } catch (IOException e) {
            throw new ImageNotFoundException(e);
        }
    }

}
