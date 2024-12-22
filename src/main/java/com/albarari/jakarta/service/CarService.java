package com.albarari.jakarta.service;

import com.albarari.jakarta.entity.Car;
import com.albarari.jakarta.repository.CarRepository;
import java.util.List;
import jakarta.inject.Inject;
import jakarta.enterprise.context.ApplicationScoped;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Service class for managing Car entities.
 */
@ApplicationScoped
public class CarService {

    private static final Logger logger = LogManager.getLogger(CarService.class);

    @Inject
    private CarRepository carRepository;

    /**
     * Default constructor for CDI.
     */
    public CarService() {
        // Default constructor for CDI
    }

    /**
     * Saves a new Car entity or updates an existing one.
     *
     * @param car the Car entity to save or update
     * @return the saved or updated Car entity
     */
    public Car saveCar(Car car) {
        logger.info("Saving car: {}", car);
        Car savedCar = carRepository.save(car);
        logger.info("Car saved: {}", savedCar);
        return savedCar;
    }

    /**
     * Retrieves a Car entity by its ID.
     *
     * @param id the ID of the Car entity to retrieve
     * @return the found Car entity, or null if not found
     */
    public Car getCarById(String id) {
        logger.info("Retrieving car with ID: {}", id);
        Car car = carRepository.findById(id);
        if (car != null) {
            logger.info("Car retrieved: {}", car);
        } else {
            logger.warn("Car not found with ID: {}", id);
        }
        return car;
    }

    /**
     * Retrieves all Car entities.
     *
     * @return a list of all Car entities
     */
    public List<Car> getAllCars() {
        logger.info("Retrieving all cars");
        List<Car> cars = carRepository.findAll();
        logger.info("Retrieved {} cars", cars.size());
        return cars;
    }

    /**
     * Deletes a Car entity by its ID.
     *
     * @param id the ID of the Car entity to delete
     */
    public void deleteCarById(String id) {
        logger.info("Deleting car with ID: {}", id);
        carRepository.deleteById(id);
        logger.info("Car deleted with ID: {}", id);
    }
}