package com.albarari.jakarta.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.transaction.Transactional;
import com.albarari.jakarta.config.MongoDBConfig;
import com.albarari.jakarta.entity.Car;
import org.bson.types.ObjectId;
import java.util.List;

/**
 * Repository class for managing Car entities.
 */
@ApplicationScoped
public class CarRepository {

    private final EntityManagerFactory emf;

    public CarRepository() {
        this.emf = null; // or initialize as needed
    }

    /**
     * Constructor to initialize the CarRepository with an EntityManagerFactory.
     */
    @Inject
    public CarRepository(MongoDBConfig mongoDBConfig) {
        this.emf = mongoDBConfig.createEntityManagerFactory();
    }

    /**
     * Saves a Car entity to the database.
     *
     * @param car the Car entity to save
     * @return the saved Car entity
     */
    @Transactional
    public Car save(Car car) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            if (car.getId() != null && em.find(Car.class, car.getId()) != null) {
                car = em.merge(car);
            } else {
                em.persist(car);
            }
            transaction.commit();
            return car;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    /**
     * Finds a Car entity by its ID.
     *
     * @param id the ID of the Car entity
     * @return the found Car entity, or null if not found
     */
    @Transactional
    public Car findById(ObjectId id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Car.class, id);
        } finally {
            em.close();
        }
    }

    /**
     * Retrieves all Car entities from the database.
     *
     * @return a list of all Car entities
     */
    @Transactional
    public List<Car> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT c FROM Car c", Car.class).getResultList();
        } finally {
            em.close();
        }
    }

    /**
     * Deletes a Car entity by its ID.
     *
     * @param id the ID of the Car entity to delete
     */
    @Transactional
    public void deleteById(ObjectId id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Car car = em.find(Car.class, id);
            if (car != null) {
                em.remove(car);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
}