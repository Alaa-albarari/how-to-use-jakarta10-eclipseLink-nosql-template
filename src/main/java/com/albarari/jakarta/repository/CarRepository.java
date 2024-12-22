package com.albarari.jakarta.repository;

import com.albarari.jakarta.entity.Car;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

/**
 * Repository class for managing Car entities.
 */
@ApplicationScoped
public class CarRepository {

    private static final Logger logger = LogManager.getLogger(CarRepository.class);

    private EntityManager em;

    /**
     * Default constructor for CDI.
     */
    public CarRepository() {
        // Default constructor for CDI
    }

    /**
     * Constructor with EntityManagerFactory injection.
     *
     * @param emf the EntityManagerFactory to create EntityManager
     */
    @Inject
    public CarRepository(EntityManagerFactory emf) {
        this.em = emf.createEntityManager();
    }

    /**
     * Saves a new Car entity or updates an existing one.
     *
     * @param car the Car entity to save or update
     * @return the saved or updated Car entity
     */
    public Car save(Car car) {
        logger.info("Saving car: {}", car);
        em.getTransaction().begin(); // Start a manual transaction
        if (car.getId() == null || em.find(Car.class, car.getId()) == null) {
            em.persist(car);
            logger.info("Car persisted: {}", car);
        } else {
            car = em.merge(car);
            logger.info("Car merged: {}", car);
        }
        em.getTransaction().commit(); // Commit the transaction
        logger.info("Transaction committed for car: {}", car);
        return car;
    }

    /**
     * Finds a Car entity by its ID.
     *
     * @param id the ID of the Car entity to find
     * @return the found Car entity, or null if not found
     */
    public Car findById(String id) {
        logger.info("Finding car by ID: {}", id);
        Car car = em.find(Car.class, id);
        if (car != null) {
            logger.info("Car found: {}", car);
        } else {
            logger.warn("Car not found with ID: {}", id);
        }
        return car;
    }

    /**
     * Finds all Car entities.
     *
     * @return a list of all Car entities
     */
    public List<Car> findAll() {
        logger.info("Finding all cars");
        List<Car> cars = em.createQuery("SELECT c FROM Car c", Car.class).getResultList();
        logger.info("Found {} cars", cars.size());
        return cars;
    }

    /**
     * Deletes a Car entity by its ID.
     *
     * @param id the ID of the Car entity to delete
     */
    public void deleteById(String id) {
        logger.info("Deleting car by ID: {}", id);
        em.getTransaction().begin();
        Car car = em.find(Car.class, id);
        if (car != null) {
            em.remove(car);
            logger.info("Car removed: {}", car);
        } else {
            logger.warn("Car not found with ID: {}", id);
        }
        em.getTransaction().commit();
        logger.info("Transaction committed for car deletion with ID: {}", id);
    }
}