package com.makemytrip.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Configuration Reader Class
 * Reads configuration from properties file
 */
public class ConfigReader {
    private static final Logger logger = LogManager.getLogger(ConfigReader.class);
    private static Properties properties;

    static {
        loadProperties();
    }

    /**
     * Load properties from config file
     */
    public static void loadProperties() {
        properties = new Properties();
        try {
            InputStream inputStream = ConfigReader.class
                    .getClassLoader()
                    .getResourceAsStream("config.properties");

            if (inputStream != null) {
                properties.load(inputStream);
                logger.info("Configuration file loaded successfully");
            } else {
                logger.error("Configuration file not found");
            }
        } catch (IOException e) {
            logger.error("Error loading configuration file: " + e.getMessage());
        }
    }

    /**
     * Get property value
     */
    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value != null) {
            logger.info("Property [" + key + "] = " + value);
            return value;
        }
        logger.warn("Property [" + key + "] not found");
        return null;
    }

    /**
     * Get property value with default
     */
    public static String getProperty(String key, String defaultValue) {
        String value = properties.getProperty(key, defaultValue);
        logger.info("Property [" + key + "] = " + value);
        return value;
    }
}
