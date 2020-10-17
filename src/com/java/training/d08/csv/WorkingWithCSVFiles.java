package com.java.training.d08.csv;

import com.java.training.model.Product;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class WorkingWithCSVFiles {

    private static final String PATH_SEPARATOR = File.separator;

    public static void main(String[] args) {
        final Set<Product> products = readProductsFromFile("products.csv");

        System.out.println("There are " + products.size() + " products in the file:");
        for (Product product : products) {
            System.out.println(product);
        }
    }

    private static Set<Product> readProductsFromFile(String fileName) {
        // 1 - read file content
        final Set<String> fileContent = readFileContent(fileName);

        // 2 - convert lines into Products
        return convertStringLinesIntoProducts(fileContent);
    }

    private static Set<String> readFileContent(String fileName) {
        if (fileDoesNotExist("csv" + PATH_SEPARATOR + fileName)) {
            System.err.println("The file '" + fileName + "' does not exist");
            return new HashSet<>();
        }

        Set<String> lines = new HashSet<>();

        try (FileReader fileReader = new FileReader("csv" + PATH_SEPARATOR + fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            String fileLine;
            while ((fileLine = bufferedReader.readLine()) != null) {
                lines.add(fileLine);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return lines;
    }

    private static boolean fileDoesNotExist(String fileName) {
        return !(new File(fileName).exists());
    }

    private static Set<Product> convertStringLinesIntoProducts(Set<String> fileContent) {
        final Set<Product> products = new TreeSet<>();
        for (String line : fileContent) {
            StringTokenizer stringTokenizer = new StringTokenizer(line, ",");
            products.add(new Product(
                    Integer.parseInt(stringTokenizer.nextToken()),
                    stringTokenizer.nextToken())
            );
        }
        return products;
    }
}
