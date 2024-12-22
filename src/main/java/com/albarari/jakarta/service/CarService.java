package com.albarari.jakarta.service;

import com.albarari.jakarta.entity.Car;
import com.albarari.jakarta.repository.CarRepository;
import java.util.List;
import jakarta.inject.Inject;

@jakarta.enterprise.context.ApplicationScoped
public class CarService {

    @Inject
    private CarRepository carRepository;

    public CarService() {
        // Default constructor for CDI
    }
    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    public Car getCarById(String id) {
        return carRepository.findById(id);
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public void deleteCarById(String id) {
        carRepository.deleteById(id);
    }
}