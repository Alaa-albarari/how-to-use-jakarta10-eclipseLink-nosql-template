package com.albarari.jakarta.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Configuration class for loading MongoDB settings using ConfigLoader.
 */
public class MongoConfig {
    private static final Logger logger = LogManager.getLogger(MongoConfig.class);

    private String host;
    private int port;
    private String database;
    private String username;
    private String password;

    /**
     * Loads the MongoDB configuration using ConfigLoader.
     *
     * @return the MongoConfig instance with loaded settings
     * @throws RuntimeException if the configuration cannot be loaded
     */
    public static MongoConfig getConfig() {
        logger.info("Loading MongoDB configuration");
        AppConfig.Mongodb mongoConfig = ConfigLoader.getInstance().getConfig().getMongodb();

        MongoConfig config = new MongoConfig();
        config.host = mongoConfig.getHost();
        config.port = mongoConfig.getPort();
        config.database = mongoConfig.getDatabase();
        config.username = mongoConfig.getUsername();
        config.password = mongoConfig.getPassword();

        logger.info("MongoDB configuration loaded: host={}, port={}, database={}",
                    config.host, config.port, config.database);
        return config;
    }

    // Getters
    public String getHost() { return host; }
    public int getPort() { return port; }
    public String getDatabase() { return database; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
}