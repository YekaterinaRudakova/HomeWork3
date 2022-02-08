package com.it_academy.bank;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileReader {
    public static final String PATH_TO_PROPERTIES = "/Users/kateradkevich/Desktop/homework3/src/main/resources/config.properties";
    private Properties properties;
    private FileInputStream fileInputStream;

    public PropertiesFileReader() {
    }

    public void initialize() {
        try {
            fileInputStream = new FileInputStream(PATH_TO_PROPERTIES);
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (IOException ex) {
            System.out.println("IOException" + ex.getMessage());
        }

    }

    public void finish() {
        try {
            fileInputStream.close();
        } catch (IOException ex) {
            System.out.println("IOException" + ex.getMessage());
        }
    }

    public String readProperties(String key) {
        return properties.getProperty(key);
    }
}
