package com.albarari.jakarta.config;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.LoaderOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.enterprise.context.ApplicationScoped;
import java.io.InputStream;

@ApplicationScoped
public class ConfigLoader {
    private static final Logger logger = LogManager.getLogger(ConfigLoader.class);
    private static ConfigLoader instance;
    private AppConfig config;

    private ConfigLoader() {
        loadConfig();
    }

    public static ConfigLoader getInstance() {
        if (instance == null) {
            instance = new ConfigLoader();
        }
        return instance;
    }

    private void loadConfig() {
        LoaderOptions loaderOptions = new LoaderOptions();
        Constructor constructor = new Constructor(AppConfig.class, loaderOptions);
        Yaml yaml = new Yaml(constructor);

        String activeProfile = getActiveProfile();
        String configFileName = "application-" + activeProfile + ".yaml";
        logger.info("Active profile: {}", activeProfile);
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(configFileName)) {
            if (input == null) {
                throw new RuntimeException("Could not find " + configFileName);
            }
            config = yaml.load(input);
            validateConfig(config.getMongodb());
            logger.info("Configuration loaded successfully for profile: {}", activeProfile);
        } catch (Exception e) {
            logger.error("Failed to load configuration", e);
            throw new RuntimeException("Failed to load configuration", e);
        }
    }

    public String getActiveProfile() {

        // Check for system property
        String profile = System.getProperty("active.profile");
        if (profile != null) {
            logger.debug("Profile found from system property: {}", profile);
        }


        // Check for environment variable
        if (profile == null || profile.isEmpty()) {
            profile = System.getenv("APP_PROFILE");
            if (profile != null) {
                logger.debug("Profile found from environment variable: {}", profile);
            }
        }

        // Fallback to default
        if (profile == null || profile.isEmpty()) {
            profile = "development";
            logger.warn("No profile specified. Falling back to default profile: {}", profile);
        }
        return profile;
    }

    private void validateConfig(AppConfig.Mongodb mongoConfig) {
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

    public AppConfig getConfig() {
        return config;
    }
}