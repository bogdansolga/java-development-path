package com.java.training.d09.serializing;

import com.java.training.model.Product;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class XMLSerializingAndDeSerializingMain {

    private static final String FILE_NAME = "products.xml";

    public static void main(String[] args) {
        serializeObjects();

        deSerializeObjects();
    }

    private static void serializeObjects() {
        Product tablet = new Product(1, "Tablet");
        Product fruit = new Product(5, "Watermelon");

        try (FileOutputStream fileOutputStream = new FileOutputStream(new File(FILE_NAME));
             XMLEncoder xmlEncoder = new XMLEncoder(fileOutputStream)) {

            xmlEncoder.writeObject(tablet);
            xmlEncoder.writeObject(fruit);

            System.out.println("The products were successfully serialised");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void deSerializeObjects() {
        try (FileInputStream fileInputStream = new FileInputStream(new File(FILE_NAME));
             XMLDecoder xmlDecoder = new XMLDecoder(fileInputStream)) {

            Object object;
            do {
                object = xmlDecoder.readObject();
                if (object instanceof Product) {
                    System.out.println(object);
                }
            } while (object != null);

            System.out.println("The products were successfully de-serialised");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
