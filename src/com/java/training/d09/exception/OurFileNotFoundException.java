package com.java.training.d09.exception;

public class OurFileNotFoundException extends RuntimeException {

    public OurFileNotFoundException(String message) {
        super(message);
    }
}
