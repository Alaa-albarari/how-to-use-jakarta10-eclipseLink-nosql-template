package com.albarari.jakarta.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManagerFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Producer class for creating and providing an EntityManagerFactory instance.
 */
@ApplicationScoped
public class EntityManagerProducer {

    private static final Logger logger = LogManager.getLogger(EntityManagerProducer.class);

    /**
     * Produces an EntityManagerFactory instance configured for MongoDB.
     *
     * @return the EntityManagerFactory configured for MongoDB
     */
    @Produces
    @ApplicationScoped
    public EntityManagerFactory entityManagerFactory() {
        try {
            logger.info("Producing EntityManagerFactory for MongoDB");
            return MongoDBConfig.createEntityManagerFactory();
        } catch (Exception e) {
            logger.error("Failed to produce EntityManagerFactory", e);
            throw new RuntimeException("Failed to produce EntityManagerFactory", e);
        }
    }
}