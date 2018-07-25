package com.algorithms.utils.exceptions;

/**
 * @author Mihai Alexandru
 * @date 25.07.2018
 */
public class ImageNotFoundException extends RuntimeException {

    public ImageNotFoundException(String message) {
        super(message);
    }

    public ImageNotFoundException(Throwable cause) {
        super(cause);
    }
}
