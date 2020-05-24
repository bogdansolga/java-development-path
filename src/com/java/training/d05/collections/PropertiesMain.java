package com.java.training.d05.collections;

import java.io.IOException;
import java.util.Properties;
import java.util.Set;
// for pull request
public class PropertiesMain {

    public static void main(String[] args) throws IOException {
        //systemProperties();

        loadingAPropertiesFile();
    }

    private static void loadingAPropertiesFile() throws IOException {
        Properties properties = new Properties();

        // using a static context / object
        //properties.load(PropertiesMain.class.getResourceAsStream("product.properties"));

        // using a non-static context / object
        PropertiesMain propertiesMain = new PropertiesMain();
        properties.load(propertiesMain.getClass()
                                      .getResourceAsStream("product.properties"));

        final Set<String> strings = properties.stringPropertyNames();
        System.out.println(strings.size());
        for (String key : strings) {
            System.out.println(key + " - " + properties.get(key));
        }
    }

    private static void systemProperties() {
        Properties systemProperties = System.getProperties();
        final Set<String> propertyNames = systemProperties.stringPropertyNames();
        for (String propertyName : propertyNames) {
            System.out.println(propertyName + " - " + systemProperties.getProperty(propertyName));
        }
    }
}
