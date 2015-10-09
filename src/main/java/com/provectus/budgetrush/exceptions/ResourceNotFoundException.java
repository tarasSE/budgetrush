package com.provectus.budgetrush.exceptions;

@SuppressWarnings("serial")
public class ResourceNotFoundException extends RuntimeException {

    private final static String MESSAGE = "%s not found.";

    public ResourceNotFoundException() {
        super(addResourceNameToMessage("Resource"));
    }

    public ResourceNotFoundException(String resourceName) {
        super(addResourceNameToMessage(resourceName));
    }

    private static String addResourceNameToMessage(String resourceName) {
        return String.format(MESSAGE, resourceName);
    }
}
