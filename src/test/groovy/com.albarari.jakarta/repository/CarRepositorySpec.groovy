package com.albarari.jakarta.repository

import com.albarari.jakarta.config.MongoDBConfig
import spock.lang.Specification
import com.albarari.jakarta.entity.Car
import org.bson.types.ObjectId

/**
 * Specification class for testing CarRepository.
 */
class CarRepositorySpec extends Specification {

    CarRepository carRepository
    List<ObjectId> insertedCarIds = []

    /**
     * Sets up the test environment by initializing the CarRepository.
     */
    def setup() {
        def mongoDBConfig = new MongoDBConfig()

        // Initialize the repository with the Mongodb
        carRepository = new CarRepository(mongoDBConfig)
    }

    /**
     * Cleans up the test environment by deleting inserted cars.
     */
    def cleanup() {
        // Delete only the cars inserted by the test
        insertedCarIds.each { id ->
            carRepository.deleteById(id)
        }
    }

    /**
     * Tests saving a new car and retrieving it by ID.
     */
    def "should save a new car and retrieve it by ID"() {
        given: "a new car to persist"
        def car = new Car(id: new ObjectId(), make: "Toyota", model: "Corolla", year: "2023")

        when: "saving the car"
        def savedCar = carRepository.save(car)
        insertedCarIds.add(savedCar.id)

        then: "the car is successfully saved and retrievable"
        def retrievedCar = carRepository.findById(car.id)
        retrievedCar != null
        retrievedCar.make == "Toyota"
        retrievedCar.model == "Corolla"
        retrievedCar.year == "2023"
    }

    /**
     * Tests updating an existing car.
     */
    def "should update an existing car"() {
        given: "an existing car in the repository"
        def car = new Car(id: new ObjectId(), make: "Honda", model: "Civic", year: "2022")
        carRepository.save(car)
        insertedCarIds.add(car.id)

        when: "updating the car's details"
        car.make = "Updated Honda"
        car.model = "Updated Civic"
        def updatedCar = carRepository.save(car)

        then: "the updated car is retrievable"
        def retrievedCar = carRepository.findById(car.id)
        retrievedCar.make == "Updated Honda"
        retrievedCar.model == "Updated Civic"
    }

    /**
     * Tests retrieving all cars.
     */
    def "should retrieve all cars"() {
        given: "multiple cars saved in the repository"
        def car1 = new Car(id: new ObjectId(), make: "Ford", model: "Focus", year: "2020")
        def car2 = new Car(id: new ObjectId(), make: "Chevrolet", model: "Malibu", year: "2021")
        carRepository.save(car1)
        carRepository.save(car2)
        insertedCarIds.add(car1.id)
        insertedCarIds.add(car2.id)

        when: "retrieving all cars"
        def cars = carRepository.findAll()

        then: "all cars are retrieved"
        cars.size() >= 2
        cars.any { it.id == car1.id && it.make == "Ford" }
        cars.any { it.id == car2.id && it.make == "Chevrolet" }
    }

    /**
     * Tests deleting a car by ID.
     */
    def "should delete a car by ID"() {
        given: "a car saved in the repository"
        def car = new Car(id: new ObjectId(), make: "Tesla", model: "Model S", year: "2022")
        carRepository.save(car)
        insertedCarIds.add(car.id)

        when: "deleting the car"
        carRepository.deleteById(car.id)

        then: "the car is no longer retrievable"
        carRepository.findById(car.id) == null
    }
}