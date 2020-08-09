package com.java.training.d08.csv;

import com.java.training.d08.csv.service.CSVFileService;
import com.java.training.model.Product;

import java.util.Set;

public class CSVFileExampleUsingAServiceClass {

    public static void main(String[] args) {
        // the 'service' name will be further detailed
        // for now - it contains the business logic from a program

        final CSVFileService csvFileService = new CSVFileService();
        final Set<String> fileContent = csvFileService.readProductsFromFile("products.csv");
        final Set<Product> products = csvFileService.convertLinesIntoProducts(fileContent);
        csvFileService.displayProducts(products);
    }
}
