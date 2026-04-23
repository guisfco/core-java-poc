package com.guisfco.api.properties;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

// The Properties class represents a persistent set of properties.
// The Properties can be saved to a stream or loaded from a stream.
// Each key and its corresponding value in the property list is a string.
public class Main {

    static void main() throws IOException {
        IO.println("==== CREATE ====");
        var properties = create();

        IO.println("\n==== READ ====");
        read(properties);

        IO.println("\n==== LOAD ====");
        loadProperties(properties);
        loadPropertiesFromXML(properties);

        IO.println("\n==== STORE ====");
        store(properties);
        storeToXML(properties);

        IO.println("\n==== LIST ====");
        list(properties);

        IO.println("\n==== REMOVE ====");
        remove(properties);

        IO.println("\n==== SYSTEM ====");
        getSystemProperties();
    }

    static Properties create() {
        // Creates an empty property list with no default values
        var defaultProperties = new Properties();
        defaultProperties.setProperty("env", "prd");

        // Creates an empty property list with the specified defaults
        var properties = new Properties(defaultProperties);

        properties.setProperty("app.name", "PropertiesApi");
        properties.setProperty("version", "1.0");

        // Updating property
        properties.setProperty("version", "2.0");

        IO.println(properties);
        IO.println(properties.getProperty("env")); // Inherit default property

        return properties;
    }

    static void read(Properties properties) {
        var version = properties.getProperty("version");
        var missingProperty = properties.getProperty("missing.key", "default");

        IO.println(version);
        IO.println(missingProperty);
    }

    static void loadProperties(Properties properties) throws IOException {
        try (var inputStream = ClassLoader.getSystemResourceAsStream("database.properties")) {
            properties.load(inputStream);
        }

        IO.println(properties);
    }

    static void loadPropertiesFromXML(Properties properties) throws IOException {
        try (var inputStream = ClassLoader.getSystemResourceAsStream("messages.xml")) {
            properties.loadFromXML(inputStream);
        }

        IO.println(properties.getProperty("signup"));
        IO.println(properties.getProperty("signin"));
    }

    static void store(Properties properties) throws IOException {
        try (var fileWriter = new FileWriter("./src/main/resources/output.properties")) {
            properties.store(fileWriter, "Test v1.0");
        }

        IO.println("File stored in /src/main/resources/output.properties");
    }

    static void storeToXML(Properties properties) throws IOException {
        try (var fileWriter = new FileOutputStream("./src/main/resources/output.xml")) {
            properties.storeToXML(fileWriter, "Test v1.0");
        }

        IO.println("File stored in /src/main/resources/output.xml");
    }

    static void list(Properties properties) throws IOException {
        properties.list(System.out);
    }

    static void remove(Properties properties) {
        properties.remove("database.url");
        IO.println(properties);
    }

    static void getSystemProperties() {
        var systemProperties = System.getProperties();
        IO.println(systemProperties.getProperty("java.version"));
    }
}
