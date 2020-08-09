package com.java.training.d09.exception;

import java.beans.XMLDecoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ExceptionTypes {

    public static void main(String[] args) {
        // fail fast, fail quickly

        try {
            checkedExceptions();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        uncheckedExceptions();
    }

    private static void checkedExceptions() throws FileNotFoundException {
        handlingTheException();

        reThrowingTheException();

        convertingAnException();
    }

    private static void handlingTheException() {
        String fileName = "section.xml";
        FileInputStream fileInputStream = null;
        try {
            System.out.println("Opening the file '" + fileName + "'...");
            fileInputStream = new FileInputStream(new File(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new OurFileNotFoundException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        System.out.println("The file '" + fileName + "' was successfully opened");
        XMLDecoder xmlDecoder = new XMLDecoder(fileInputStream);
    }

    private static void reThrowingTheException() throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(new File("section.xml"));
        XMLDecoder xmlDecoder = new XMLDecoder(fileInputStream);
    }

    // exception type changing (from checked to unchecked)
    private static void convertingAnException() {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(new File("section.xml"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("The file was not found");
        }

        XMLDecoder xmlDecoder = new XMLDecoder(fileInputStream);
    }

    private static void uncheckedExceptions() {
        String name = null;
        // further processing

        if (name == null) {
            throw new RuntimeException("The name cannot be null");
        }
    }
}
