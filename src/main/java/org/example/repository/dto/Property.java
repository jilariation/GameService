package org.example.repository.dto;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Reads variable values from {@see <a href="src/main/resources/application.properties">application.properties</a>}
 */
public class Property {
    public static Properties getProperty() {
        Properties properties = new Properties();
        InputStream inputStream = Property.class.getClassLoader().getResourceAsStream("application.properties");
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }
}
