package ru.inno.ext;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyProvider {
    private static PropertyProvider instance;

    private Properties properties;

    private PropertyProvider() {
    }

    public static PropertyProvider getInstance() {
        if (instance == null) {
            instance = new PropertyProvider();
            instance.loadProperties();
        }

        return instance;
    }

    public Properties getProps() {
        return properties;
    }

    private void loadProperties() {
        properties = new Properties();
        try {
            properties.load(new FileReader("src/test/resources/config.properties"));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}