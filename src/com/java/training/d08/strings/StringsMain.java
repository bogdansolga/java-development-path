package com.java.training.d08.strings;

import com.java.training.model.Product;

import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * A class used to display some simple examples for working with Strings
 *
 * @author bogdan.solga
 */
public class StringsMain {

    public static void main(String[] args) {
        //simpleStringOperations();

        //productOperations();

        //stringBuilderExample();

        stringTokenizerExample();
    }

    private static void simpleStringOperations() {
        String helloSummer = "Hello, summer! Where are you?";
        System.out.println(helloSummer.startsWith("Hello"));

        //!!! remember - the String class is immutable, MUST create a new object each time we apply
        // an operation on a String object
        final String substring = helloSummer.substring(0, 13);
        System.out.println(substring);

        // creating a new object in order to obtain the value of the replaced string
        final String replaced = helloSummer.replaceFirst("\\?", "?!");
        System.out.println(replaced);

        final String[] parts = helloSummer.split(" ");
        System.out.println(Arrays.asList(parts));
        for (String part : parts) {
            System.out.println(part);
        }

        String hello = new String(new char[]{'h', 'e', 'l', 'l', 'o'});
        System.out.println(hello);

        // recommended only for Strings composed from 2-3-4 part, not from 20, 30 or 40 parts
        // for those cases - we have StringBuilder and StringBuffer
        String composed = "some" + "thing"; // --> something
        System.out.println(composed);

        System.out.println(composed.equals("something")); // true
        System.out.println(composed.equalsIgnoreCase("Something")); // true
    }

    private static void productOperations() {
        Product product = new Product(10, "Sunglasses");
        System.out.println(product.toString());
    }

    private static void stringBuilderExample() {
        StringBuilder stringBuilder = new StringBuilder("The ");
        stringBuilder.append("weekend ")
                     .append("is ")
                     .append("coming");
        System.out.println(stringBuilder.toString());
    }

    private static void stringTokenizerExample() {
        String constantWish = "I want a holiday, not just a weekend!";
        StringTokenizer stringTokenizer = new StringTokenizer(constantWish, " ,;!");
        System.out.println("The string has " + stringTokenizer.countTokens() + " parts");
        while (stringTokenizer.hasMoreTokens()) {
            System.out.println(stringTokenizer.nextToken());
        }
    }
}
