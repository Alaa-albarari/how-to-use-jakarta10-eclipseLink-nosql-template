package com.albarari.jakarta.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManagerFactory;

@ApplicationScoped
public class EntityManagerProducer {

    @Produces
    @ApplicationScoped
    public EntityManagerFactory entityManagerFactory() {
        return MongoDBConfig.createEntityManagerFactory();
    }
}
