package com.java.training.stores.project.services;

import com.java.training.stores.project.models.Product;
import com.java.training.stores.project.models.Section;
import com.java.training.stores.project.models.Store;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class UtilService {
    private static Scanner scanner = new Scanner(System.in);

    public static String readValue() {
        // TODO use try with resources --> extract the stream objects

        //Enter data using BufferReader
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // Reading data using readLine
        try {
            return reader.readLine();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static boolean isNumeric(final String value) {
        if (value == null)
            return false;

        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void createNewStore() {
        System.out.println("Enter the name of the new store: ");
        final String name = readValue();
        Store store = new Store(1, name, null);
        System.out.println("The store " + store.getName() + " was successfully created.");
    }

    public static void writeInXML(final String fileName, List<Store> storeList) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(new File(fileName));
             XMLEncoder xmlEncoder = new XMLEncoder(fileOutputStream)) {

            xmlEncoder.writeObject(storeList);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static void writeInCSV(final String fileName, List<Store> storeList) {
        try (FileWriter fw = new FileWriter(fileName);
             BufferedWriter bw = new BufferedWriter(fw)) {

            bw.write("Store, Section, Product");
            bw.newLine();
            for (int i = 0; i < storeList.size(); i++) {
                if (storeList.get(i).getSections() != null) {
                    for (Section section : storeList.get(i).getSections()) {
                        if (section.getProducts() != null) {
                            for (Product product : section.getProducts()) {
                                bw.write(storeList.get(i).getName().concat(", ").concat(section.getName()).concat(", ").concat(product.getName()));
                                bw.newLine();
                            }
                        } else {
                            bw.write(storeList.get(i).getName().concat(", ").concat(section.getName()));
                            bw.newLine();
                        }
                    }
                } else {
                    bw.write(storeList.get(i).getName());
                    bw.newLine();
                }
            }
            bw.write("\n");
            bw.newLine();
            bw.close();
            fw.close();
            System.out.println("Data was written in the CSV file");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static List<Store> getAllData(final String fileName) {
        try (FileInputStream fileInputStream = new FileInputStream(new File(fileName));
             XMLDecoder xmlDecoder = new XMLDecoder(fileInputStream)) {
            Object object;
            do {
                object = xmlDecoder.readObject();
                if (object instanceof List && !((List) object).isEmpty() && ((List) object).get(0) instanceof Store) {
                    return (List<Store>) object;
                }
            } while (object != null);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    public static Scanner getScanner() {
        return scanner;
    }

    public static void archiveCsvAsZip() {
        // TODO use try with resources --> extract the stream objects
        try {
            String sourceFile = "Store.csv";
            File file = new File(sourceFile);
            FileOutputStream fileoutput = new FileOutputStream("StoreCsv.zip");
            ZipOutputStream zipoutput = new ZipOutputStream(fileoutput);
            zipoutput.putNextEntry(new ZipEntry(file.getName()));
            byte[] bytes = Files.readAllBytes(Paths.get("Store.csv"));
            zipoutput.write(bytes, 0, bytes.length);
            zipoutput.closeEntry();
            zipoutput.close();
            System.out.println("Zip file was successfully created");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static void archiveXmlAsZip() {
        // TODO use try with resources --> extract the stream objects
        try {
            String sourceFile = "Store.xml";
            File file = new File(sourceFile);
            FileOutputStream fileoutput = new FileOutputStream("StoreXml.zip");
            ZipOutputStream zipoutput = new ZipOutputStream(fileoutput);
            zipoutput.putNextEntry(new ZipEntry(file.getName()));
            byte[] bytes = Files.readAllBytes(Paths.get("Store.csv"));
            zipoutput.write(bytes, 0, bytes.length);
            zipoutput.closeEntry();
            zipoutput.close();
            System.out.println("Zip file was successfully created");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void displayStock(List<Store> stores) {
        printStores(stores);
    }

    public static void printStores(List<Store> stores) {
        if (stores != null)
            stores.forEach(store -> {
                System.out.println("[Store]: " + store.getName());
                printSections(store.getSections());
            });
    }

    public static void printSections(Set<Section> sections) {
        if (sections != null)
            sections.forEach(section -> {
                System.out.println("\t[Section]: " + section.getName());
                printProducts(section.getProducts());
            });
    }

    public static void printProducts(Set<Product> products) {
        if (products != null)
            products.forEach(product -> {
                System.out.println("\t\t[Product]:" + product.getName());
            });
    }

}
