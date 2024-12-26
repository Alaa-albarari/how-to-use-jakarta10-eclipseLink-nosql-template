package com.albarari.jakarta.mapper;

import com.albarari.jakarta.dto.CarDto;
import com.albarari.jakarta.entity.Car;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.types.ObjectId;

/**
 * Mapper class for converting between Car and CarDto.
 */
@ApplicationScoped
public class CarMapper {

    /**
     * Converts a Car entity to a CarDto.
     *
     * @param car the Car entity to convert
     * @return the converted CarDto
     */
    public CarDto carToCarDto(Car car) {
        if (car == null) {
            return null;
        }
        CarDto carDto = new CarDto();
        carDto.setId(car.getId().toHexString());
        carDto.setMake(car.getMake());
        carDto.setModel(car.getModel());
        carDto.setYear(car.getYear());
        return carDto;
    }

    /**
     * Converts a CarDto to a Car entity.
     *
     * @param carDto the CarDto to convert
     * @return the converted Car entity
     */
    public Car carDtoToCar(CarDto carDto) {
        if (carDto == null) {
            return null;
        }
        Car car = new Car();
        car.setId(carDto.getId() != null ? new ObjectId(carDto.getId()) : null);
        car.setMake(carDto.getMake());
        car.setModel(carDto.getModel());
        car.setYear(carDto.getYear());
        return car;
    }
}