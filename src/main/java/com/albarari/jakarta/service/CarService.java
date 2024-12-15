

package com.albarari.jakarta.service;
import com.albarari.jakarta.entity.Car;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;

import java.util.List;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@ApplicationScoped
public class CarService {

    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("mongoPU");
    public Car getCarById(String id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.find(Car.class, id);
        } finally {
            entityManager.close();
        }
    }

    public List<Car> getAllCars() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.createQuery("SELECT c FROM Car c", Car.class).getResultList();
        } finally {
            entityManager.close();
        }
    }

    public Car addCar(Car car) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(car);
            entityManager.getTransaction().commit();
            return car;
        } finally {
            entityManager.close();
        }
    }
}