package com.albarari.jakarta.config;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.LoaderOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;

/**
 * Singleton class for loading application configuration from a YAML file.
 */
public class ConfigLoader {
    private static final Logger logger = LogManager.getLogger(ConfigLoader.class);
    private static ConfigLoader instance;
    private AppConfig config;

    private ConfigLoader() {
        loadConfig();
    }

    /**
     * Returns the singleton instance of ConfigLoader.
     *
     * @return the singleton instance of ConfigLoader
     */
    public static ConfigLoader getInstance() {
        if (instance == null) {
            instance = new ConfigLoader();
        }
        return instance;
    }

    /**
     * Loads the application configuration from the `application-config.yaml` file.
     *
     * @throws RuntimeException if the configuration file cannot be loaded or parsed
     */
    private void loadConfig() {
        LoaderOptions loaderOptions = new LoaderOptions();
        Constructor constructor = new Constructor(AppConfig.class, loaderOptions);
        Yaml yaml = new Yaml(constructor);
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("application-config.yaml")) {
            if (input == null) {
                throw new RuntimeException("Could not find application-config.yaml");
            }
            config = yaml.load(input);
            validateConfig(config.getMongodb());
            logger.info("Configuration loaded successfully");
        } catch (Exception e) {
            logger.error("Failed to load configuration", e);
            throw new RuntimeException("Failed to load configuration", e);
        }
    }

    /**
     * Validates the MongoDB configuration properties.
     *
     * @param mongoConfig the MongoDB configuration to validate
     */
    private void validateConfig(AppConfig.MongoDBConfig mongoConfig) {
        if (mongoConfig.getHost() == null || mongoConfig.getHost().isEmpty()) {
            throw new IllegalArgumentException("MongoDB host is not configured");
        }
        if (mongoConfig.getPort() <= 0) {
            throw new IllegalArgumentException("MongoDB port is not configured or invalid");
        }
        if (mongoConfig.getDatabase() == null || mongoConfig.getDatabase().isEmpty()) {
            throw new IllegalArgumentException("MongoDB database is not configured");
        }
        if (mongoConfig.getUsername() == null || mongoConfig.getUsername().isEmpty()) {
            throw new IllegalArgumentException("MongoDB username is not configured");
        }
        if (mongoConfig.getPassword() == null || mongoConfig.getPassword().isEmpty()) {
            throw new IllegalArgumentException("MongoDB password is not configured");
        }
    }

    /**
     * Returns the loaded application configuration.
     *
     * @return the loaded application configuration
     */
    public AppConfig getConfig() {
        return config;
    }
}