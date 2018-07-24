package com.algorithms.utils.exceptions;

/**
 * @author slidem
 */
public class ClassFinderException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ClassFinderException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClassFinderException(String message) {
        super(message);
    }

    public ClassFinderException(Throwable cause) {
        super(cause);
    }


}
