package com.provectus.budgetrush.exceptions;

@SuppressWarnings("serial")
public class CustomException extends RuntimeException {

    public CustomException() {
        super();
    }

    public CustomException(String message) {
        super(message);
    }

}
