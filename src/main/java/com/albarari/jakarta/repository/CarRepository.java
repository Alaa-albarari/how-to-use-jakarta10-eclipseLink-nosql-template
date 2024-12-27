package com.albarari.jakarta.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.transaction.Transactional;
import com.albarari.jakarta.config.MongoDBConfig;
import com.albarari.jakarta.entity.Car;
import org.bson.types.ObjectId;
import java.util.List;

/**
 * Repository class for managing Car entities.
 */
@ApplicationScoped
@Transactional
public class CarRepository {

    private final EntityManagerFactory emf;

    /**
     * Constructor to initialize the CarRepository with an EntityManagerFactory.
     */
    public CarRepository() {
        this.emf = MongoDBConfig.createEntityManagerFactory();
    }

    /**
     * Saves a Car entity to the database.
     *
     * @param car the Car entity to save
     * @return the saved Car entity
     */
    public Car save(Car car) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            if (car.getId() != null && em.find(Car.class, car.getId()) != null) {
                car = em.merge(car);
            } else {
                em.persist(car);
            }
            em.getTransaction().commit();
            return car;
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
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
    public void deleteById(ObjectId id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Car car = em.find(Car.class, id);
            if (car != null) {
                em.remove(car);
            }
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}