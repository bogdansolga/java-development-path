package com.java.training.d08.streams;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class FileReaderMain {

    private static final String NEW_LINE = System.lineSeparator();

    public static void main(String[] args) {
        //fileReader();

        //bufferedFileReader();

        //readingFromTheKeyboard();

        //writingToAFile();

        //usingTheTwoOutputStreams();
    }

    private static void fileReader() {
        //usingTryCatch();

        usingTryWithResources();
    }

    private static void usingTryCatch() {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader("README.md");
            char[] buffer = new char[1000];
            fileReader.read(buffer);
            for (char character : buffer) {
                System.out.print(character);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
    }

    private static void usingTryWithResources() {
        try (FileReader fileReader = new FileReader("README.md");
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            char[] buffer = new char[1000];
            fileReader.read(buffer);
            for (char character : buffer) {
                System.out.print(character);
            }

            String newLine;
            while ((newLine = bufferedReader.readLine()) != null) {
                System.out.println(newLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void bufferedFileReader() {
        try (FileReader fileReader = new FileReader("README.md");
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch(IOException ioex) {
            System.err.println(ioex.getMessage());
        }
    }

    private static void readingFromTheKeyboard() {
        usingTheScannerClass();

        //usingTheBufferedReaderClass();
    }

    private static void usingTheScannerClass() {
        String value = readUsingScanner();
        System.out.println(value);
    }

    private static String readUsingScanner() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private static void usingTheBufferedReaderClass() {
        String enteredValue = readFromKeyboard();
        System.out.println("You have entered '" + enteredValue + "'");
    }

    private static String readFromKeyboard() {
        System.out.println("Please enter a value:");
        BufferedReader bufferedReader = null;

        //TODO change the try / catch to try-with-resources
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            return bufferedReader.readLine();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }

        return null;
    }

    private static void writingToAFile() {
        //TODO add closing on the fileWriter
        try (FileWriter fileWriter = new FileWriter("output.txt")) {
            fileWriter.append("Something");
            fileWriter.append(NEW_LINE);
            fileWriter.append("And some other text to write in the file");
            fileWriter.flush();

            System.out.println("The text was written in the file");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private static void usingTheTwoOutputStreams() {
        System.out.println("Using the standard output stream");
        System.err.println("Using the standard error stream");
    }
}
