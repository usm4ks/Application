package com.my.exception;

public class ApplicationException extends Exception{

    private final String message;
    private final Throwable exception;

    @Override
    public String getMessage() {
        return message;
    }

    public Throwable getException() {
        return exception;
    }

    public ApplicationException(String message, Throwable exception) {
        this.message = message;
        this.exception = exception;
    }
}
