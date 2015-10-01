package com.provectus.budgetrush.exceptions;

@SuppressWarnings("serial")
public class ResourceNotFoundException extends RuntimeException {

    private final static String MESSAGE = "%s not found.";

    public ResourceNotFoundException() {
        super(addResorceNameToMessage("Resource"));
    }

    public ResourceNotFoundException(String resourceName) {
        super(addResorceNameToMessage(resourceName));
    }

    private static String addResorceNameToMessage(String resourceName) {
        return String.format(MESSAGE, resourceName);
    }
}
