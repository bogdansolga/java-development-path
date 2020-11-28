package com.java.training.stores.project.exceptions;

public class InvalidNumericInputException extends RuntimeException {

    public InvalidNumericInputException() {
        super("Invalid input. Please select an option.");
    }
}
