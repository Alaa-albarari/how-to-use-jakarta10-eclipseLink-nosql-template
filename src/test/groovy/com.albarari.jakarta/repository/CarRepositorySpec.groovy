package com.albarari.jakarta.repository

import spock.lang.Specification
import jakarta.persistence.EntityManager
import jakarta.persistence.EntityManagerFactory
import com.albarari.jakarta.config.MongoDBConfig
import com.albarari.jakarta.entity.Car
import org.bson.types.ObjectId

class CarRepositorySpec extends Specification {

    EntityManagerFactory emf
    EntityManager em
    CarRepository carRepository

    def setup() {
        // Create an EntityManagerFactory for testing
        emf = MongoDBConfig.createEntityManagerFactory() // Use your persistence unit name
        em = emf.createEntityManager()

        // Pass the EntityManager to the repository
        carRepository = new CarRepository()
        carRepository.em = em // Manually inject the EntityManager
    }

    def cleanup() {
        // Close EntityManager and EntityManagerFactory after tests
        if (em.isOpen()) em.close()
        if (emf.isOpen()) emf.close()
    }

    def "should save a new car and retrieve it by ID"() {
        given: "a new car to persist"
        def car = new Car(id: new ObjectId().toHexString(), make: "Toyota", model: "Corolla", year: "2023")

        when: "saving the car"
        def savedCar = carRepository.save(car)

        then: "the car is successfully saved and retrievable"
        def retrievedCar = carRepository.findById(car.id)
        retrievedCar != null
        retrievedCar.make == "Toyota"
        retrievedCar.model == "Corolla"
        retrievedCar.year == "2023"
    }

    def "should update an existing car"() {
        given: "an existing car in the repository"
        def car = new Car(id: new ObjectId().toHexString(), make: "Honda", model: "Civic", year: "2022")
        carRepository.save(car)

        when: "updating the car's details"
        car.make = "Updated Honda"
        car.model = "Updated Civic"
        def updatedCar = carRepository.save(car)

        then: "the updated car is retrievable"
        def retrievedCar = carRepository.findById(car.id)
        retrievedCar.make == "Updated Honda"
        retrievedCar.model == "Updated Civic"
    }

    def "should retrieve all cars"() {
        given: "multiple cars saved in the repository"
        def car1 = new Car(id: new ObjectId().toHexString(), make: "Ford", model: "Focus", year: "2020")
        def car2 = new Car(id: new ObjectId().toHexString(), make: "Chevrolet", model: "Malibu", year: "2021")
        carRepository.save(car1)
        carRepository.save(car2)

        when: "retrieving all cars"
        def cars = carRepository.findAll()

        then: "all cars are retrieved"
        cars.size() >= 2
        cars.any { it.id == car1.id && it.make == "Ford" }
        cars.any { it.id == car2.id && it.make == "Chevrolet" }
    }

    def "should delete a car by ID"() {
        given: "a car saved in the repository"
        def car = new Car(id: new ObjectId().toHexString(), make: "Tesla", model: "Model S", year: "2022")
        carRepository.save(car)

        when: "deleting the car"
        carRepository.deleteById(car.id)

        then: "the car is no longer retrievable"
        carRepository.findById(car.id) == null
    }
}