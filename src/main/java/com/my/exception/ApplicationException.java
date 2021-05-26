package com.my.exception;

public class ApplicationException extends Exception{

    private String message;
    private Exception exception;

    @Override
    public String getMessage() {
        return message;
    }

    public Exception getException() {
        return exception;
    }

    public ApplicationException(String message, Exception exception) {
        this.message = message;
        this.exception = exception;
    }
}
