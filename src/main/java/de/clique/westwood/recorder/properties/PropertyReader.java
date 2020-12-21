package de.clique.westwood.recorder.properties;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Interface to read System Properties
 */
public abstract class PropertyReader {

    private static final Logger LOGGER = Logger.getLogger(PropertyReader.class.getName());

    private static Properties properties;
    static {
        try {
            properties = new Properties();
            properties.load(PropertyReader.class.getClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException e) {
            LOGGER.severe("Can't read application.properties file: " + e.toString());
        }
    }

    /**
     * Private Constructor
     */
    private PropertyReader() {
    }

    /**
     * Read the property value
     *
     * @param key the property key
     * @return the value or {@code null}
     */
    public static String getProperty(String key) {
        return System.getProperty(key, properties.getProperty(key));
    }
}
