package com.albarari.jakarta.config;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.LoaderOptions;


import java.io.InputStream;

public class ConfigLoader {
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
        // Pass LoaderOptions to Constructor
        Constructor constructor = new Constructor(AppConfig.class, loaderOptions);
        Yaml yaml = new Yaml(constructor);
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("application-config.yaml")) {
            if (input == null) {
                throw new RuntimeException("Could not find application-config.yaml");
            }
            config = yaml.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load configuration", e);
        }
    }

    public AppConfig getConfig() {
        return config;
    }
}

