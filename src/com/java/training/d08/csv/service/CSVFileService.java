package com.java.training.d08.csv.service;

import com.java.training.model.Product;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 * The class encapsulates the logic for reading a CSV file and converting the lines into {@link Product} objects
 */
public class CSVFileService {

    private static final String PATH_SEPARATOR = File.separator;

    public Set<String> readProductsFromFile(String fileName) {
        // 1 - read file content
        return readFileContent(fileName);
    }

    private Set<String> readFileContent(String fileName) {
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

    private boolean fileDoesNotExist(String fileName) {
        return !(new File(fileName).exists());
    }

    public Set<Product> convertLinesIntoProducts(Set<String> fileContent) {
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

    public void displayProducts(Set<Product> products) {
        for (Product product : products) {
            System.out.println(product);
        }
    }
}
