package com.server.model.exception;

public class InvalidIdException extends Exception {
    private static final long serialVersionUID = 65180913621318L;

    public InvalidIdException() {
        super();
    }

    public InvalidIdException(String message) {
        super(message);
    }

    public InvalidIdException(String message, Throwable cause) {
        super(message, cause);
    }
}
