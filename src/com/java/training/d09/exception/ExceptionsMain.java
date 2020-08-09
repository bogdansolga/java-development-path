package com.java.training.d09.exception;

public class ExceptionsMain {

    public static void main(String[] args) {
        //handlingNotInitializedObjects();

        //numberFormatException();

        boolean isANumber = isNumber("2359");
        System.out.println(isANumber);
    }

    private static void handlingNotInitializedObjects() {
        nullPointerException();

        nullPointerExceptionAvoided();

        cleanerNullPointerExceptionAvoided();

        nullPointerExceptionWithTryCatch();
    }


    // no safety (/ non-null) checks
    // --> the program execution is interrupted --> not what we want
    private static void nullPointerException() {
        String name = null;
        System.out.println(name.length());
    }

    // a non-null check; OK for a variable, not OK for many --> would pollute the code with too many ifs
    // the exception is avoided, the program execution continues
    private static void nullPointerExceptionAvoided() {
        String name = null;
        if (name != null) {
            System.out.println(name.length());
        } else {
            System.err.println("The name is null");
        }
    }

    private static void cleanerNullPointerExceptionAvoided() {
        String name = null;
        if (name == null) {
            System.err.println("The name is null");
            return;
        }

        System.out.println(name.length());
    }

    // a try/catch block with an action made on catch
    // the exception is handled, the program execution continues
    private static void nullPointerExceptionWithTryCatch() {
        String name = null;
        try {
            System.out.println(name.length());
        } catch (NullPointerException npe) {
            System.err.println("The name is null; exception message: " + npe.getMessage());
        }
    }

    private static void numberFormatException() {
        String number = "8435";

        int parsedNumber;
        try {
            parsedNumber = Integer.parseInt(number);
            System.out.println("The parsed value is: " + parsedNumber);
        } catch (NumberFormatException nfe) {
            System.err.println("The string '" + number + "' is not a number");
        }
    }

    private static boolean isNumber(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}
