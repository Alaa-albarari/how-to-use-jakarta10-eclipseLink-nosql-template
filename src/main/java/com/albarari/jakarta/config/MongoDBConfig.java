package com.albarari.jakarta.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.HashMap;
import java.util.Map;

/**
 * Configuration class for setting up the MongoDB connection using EclipseLink.
 */
@ApplicationScoped
public class MongoDBConfig {

    private static final Logger logger = LogManager.getLogger(MongoDBConfig.class);

    /**
     * Creates an EntityManagerFactory with the MongoDB configuration.
     *
     * @return the EntityManagerFactory configured for MongoDB
     */
    public  EntityManagerFactory createEntityManagerFactory() {
        try {
            logger.info("Loading MongoDB configuration");
            // Load MongoDB configuration dynamically (e.g., from YAML or environment variables)
            AppConfig.Mongodb mongoConfig = ConfigLoader.getInstance().getConfig().getMongodb();

            // Define EclipseLink properties dynamically
            Map<String, Object> overrides = new HashMap<>();
            overrides.put("eclipselink.nosql.property.mongo.host", mongoConfig.getHost());
            overrides.put("eclipselink.nosql.property.mongo.port", String.valueOf(mongoConfig.getPort()));
            overrides.put("eclipselink.nosql.property.mongo.db", mongoConfig.getDatabase());
            overrides.put("eclipselink.nosql.property.user", mongoConfig.getUsername());
            overrides.put("eclipselink.nosql.property.password", mongoConfig.getPassword());

            logger.info("Creating EntityManagerFactory with MongoDB configuration: host={}, port={}, database={}",
                        mongoConfig.getHost(), mongoConfig.getPort(), mongoConfig.getDatabase());

            // Create and return the EntityManagerFactory
            return Persistence.createEntityManagerFactory("mongoPU", overrides);
        } catch (Exception e) {
            logger.error("Failed to create EntityManagerFactory", e);
            throw new RuntimeException("Failed to create EntityManagerFactory", e);
        }
    }
}