package com.provectus.budgetrush.exceptions;

public class ErrorInfo {
    public final String error;
    public final String description;

    public ErrorInfo(Exception exception) {
        this.error = "server error";
        this.description = exception.getLocalizedMessage();
    }

    public ErrorInfo(String error, Exception exception) {
        this.error = error;
        this.description = exception.getLocalizedMessage();
    }
}
