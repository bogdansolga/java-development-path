package com.java.training.StoresManagementProject.exceptions;

public class InvalidNumericInputException  extends Exception{

    public InvalidNumericInputException() {
        super(String.format("Invalid input. Please select an option."));
    }
}
