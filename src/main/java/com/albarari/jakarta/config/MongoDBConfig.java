package com.albarari.jakarta.config;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

public class MongoDBConfig {

    public static EntityManagerFactory createEntityManagerFactory() {
        // Load MongoDB configuration dynamically (e.g., from YAML or environment variables)
        AppConfig.MongoDBConfig mongoConfig = ConfigLoader.getInstance().getConfig().getMongodb();

        // Define EclipseLink properties dynamically
        Map<String, Object> overrides = new HashMap<>();
        overrides.put("eclipselink.nosql.property.mongo.host", mongoConfig.getHost());
        overrides.put("eclipselink.nosql.property.mongo.port", String.valueOf(mongoConfig.getPort()));
        overrides.put("eclipselink.nosql.property.mongo.db", mongoConfig.getDatabase());
        overrides.put("eclipselink.nosql.property.user", mongoConfig.getUsername());
        overrides.put("eclipselink.nosql.property.password", mongoConfig.getPassword());



        // Create and return the EntityManagerFactory
        return Persistence.createEntityManagerFactory("mongoPU", overrides);
    }
}
