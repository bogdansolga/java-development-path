package com.java.training.d09.serializing;

import com.java.training.model.Product;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializingAndDeSerializingMain {

    private static final String FILE_NAME = "products.ser";

    public static void main(String[] args) {
        serializeObjects();

        deSerializeObjects();
    }

    private static void serializeObjects() {
        Product tablet = new Product(1, "Tablet");
        Product fruit = new Product(5, "Watermelon");

        try (FileOutputStream fileOutputStream = new FileOutputStream(new File(FILE_NAME));
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {

            objectOutputStream.writeObject(tablet);
            objectOutputStream.writeObject(fruit);

            System.out.println("The products were successfully serialised");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void deSerializeObjects() {
        try (FileInputStream fileInputStream = new FileInputStream(new File(FILE_NAME));
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

            while (objectInputStream.available() != -1) {
                Object object = objectInputStream.readObject();
                if (object instanceof Product) {
                    System.out.println(object);
                }
            }

            System.out.println("The products were successfully de-serialised");
        } catch (EOFException eofException) {
            // do nothing --> exception swallowing / hiding
            // System.err.println(eofException.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
