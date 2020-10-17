package com.java.training.d09.serializing;

import com.java.training.model.Product;
import com.java.training.model.Section;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashSet;
import java.util.Set;

public class XMLSerializingAndDeSerializingAnObjectMain {

    private static final String FILE_NAME = "section.xml";

    public static void main(String[] args) {
        serializeObject();

        deSerializeObject();
    }

    private static void serializeObject() {
        Product tablet = new Product(1, "Tablet");
        Product keyboard = new Product(5, "Keyboard");

        Section section = new Section();
        section.setId(1);
        section.setName("Electronics");

        Set<Product> products = new HashSet<>();
        products.add(tablet);
        products.add(keyboard);

        section.setProducts(products);

        try (FileOutputStream fileOutputStream = new FileOutputStream(new File(FILE_NAME));
             XMLEncoder xmlEncoder = new XMLEncoder(fileOutputStream)) {

            xmlEncoder.writeObject(section);

            System.out.println("The section was successfully serialised");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void deSerializeObject() {
        try (FileInputStream fileInputStream = new FileInputStream(new File(FILE_NAME));
             XMLDecoder xmlDecoder = new XMLDecoder(fileInputStream)) {

            Section section = (Section) xmlDecoder.readObject();
            System.out.println("The section was successfully de-serialised");

            final Set<Product> products = section.getProducts();
            System.out.println("The section has " + products.size() + " products");
            for (Product product : products) {
                System.out.println("\t" + product.toString());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
