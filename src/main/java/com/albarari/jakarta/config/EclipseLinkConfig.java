package com.albarari.jakarta.config;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

public class EclipseLinkConfig {

    public static EntityManagerFactory createEntityManagerFactory() {
        // Configuration properties for EclipseLink and MongoDB
        Map<String, String> properties = new HashMap<>();
        properties.put("eclipselink.target-database", "org.eclipse.persistence.nosql.adapters.mongo.MongoPlatform");
        properties.put("eclipselink.nosql.connection-spec", "org.eclipse.persistence.nosql.adapters.mongo.MongoConnectionSpec");
        properties.put("eclipselink.nosql.property.mongo.host", "127.0.0.1"); // Ensure the correct host
        properties.put("eclipselink.nosql.property.mongo.port", "27017"); // Ensure the correct port
        properties.put("eclipselink.nosql.property.mongo.db", "carsdb"); // Your MongoDB database name
        properties.put("eclipselink.nosql.property.mongo.user", "root"); // MongoDB username
        properties.put("eclipselink.nosql.property.mongo.password", "example"); // MongoDB password
        properties.put("eclipselink.logging.level", "FINE"); // Optional: Set logging level
        properties.put("eclipselink.logging.parameters", "true"); // Optional: Log parameters

        // Create and return an EntityManagerFactory for the specified persistence unit
        return Persistence.createEntityManagerFactory("mongoPU", properties);
    }
}