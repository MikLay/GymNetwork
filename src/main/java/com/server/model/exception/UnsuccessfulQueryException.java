package com.server.model.exception;


public class UnsuccessfulQueryException extends Exception {

    public UnsuccessfulQueryException() {
        super("Unsuccessful SQL queries to DB");
    }

    public UnsuccessfulQueryException(String message) {
        super(message);
    }

    public UnsuccessfulQueryException(String message, Throwable cause) {
        super(message, cause);
    }
}

