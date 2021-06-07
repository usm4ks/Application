package com.my.exception;

public class CommandException extends Exception {

    private final String message;
    private final Throwable exception;

    @Override
    public String getMessage() {
        return message;
    }

    public Throwable getException() {
        return exception;
    }

    public CommandException(String message, Throwable exception) {
        this.message = message;
        this.exception = exception;
    }
}
