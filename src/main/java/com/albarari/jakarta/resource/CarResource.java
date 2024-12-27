package com.albarari.jakarta.resource;

import com.albarari.jakarta.dto.CarDto;
import com.albarari.jakarta.dto.ErrorResponse;
import com.albarari.jakarta.entity.Car;
import com.albarari.jakarta.mapper.CarMapper;
import com.albarari.jakarta.service.CarService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * RESTful resource class for managing Car entities.
 * Provides endpoints for CRUD operations on Car entities.
 */
@Path("/cars") // Base URL for this resource
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CarResource {

    private static final Logger logger = LogManager.getLogger(CarResource.class);

    @Inject
    private CarService carService;

    private static final CarMapper carMapper = CarMapper.INSTANCE;

    /**
     * Retrieves a Car entity by its ID.
     *
     * @param id the ID of the Car entity to retrieve
     * @return a Response containing the Car entity or a 404 status if not found
     */
    @GET
    @Path("/{id}")
    public Response getCarById(@PathParam("id") String id) {
        logger.info("Retrieving car with ID: {}", id);
        try {
            Car car = carService.getCarById(id);
            if (car == null) {
                logger.warn("Car not found with ID: {}", id);
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(new ErrorResponse("Car not found with ID: " + id, Response.Status.NOT_FOUND.getStatusCode()))
                        .build();
            }
            CarDto carDto = carMapper.carToCarDto(car);
            logger.info("Car retrieved: {}", carDto);
            return Response.ok(carDto).build();
        } catch (IllegalArgumentException e) {
            logger.error("Invalid ObjectId format for id: {}", id, e);
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponse("Invalid ObjectId format for id: " + id, Response.Status.BAD_REQUEST.getStatusCode()))
                    .build();
        }
    }

    /**
     * Retrieves all Car entities.
     *
     * @return a list of all Car entities
     */
    @GET
    public List<CarDto> getAllCars() {
        logger.info("Retrieving all cars");
        List<Car> cars = carService.getAllCars();
        List<CarDto> carDtos = cars.stream()
                                   .map(carMapper::carToCarDto)
                                   .collect(Collectors.toList());
        logger.info("Retrieved {} cars", carDtos.size());
        return carDtos;
    }

    /**
     * Adds a new Car entity.
     *
     * @param carDto the CarDto to add
     * @return a Response containing the created Car entity
     */
    @POST
    public Response addCar(CarDto carDto) {
        logger.info("Adding new car: {}", carDto);
        Car car = carMapper.carDtoToCar(carDto);
        Car createdCar = carService.saveCar(car);
        CarDto createdCarDto = carMapper.carToCarDto(createdCar);
        logger.info("Car added: {}", createdCarDto);
        return Response.status(Response.Status.CREATED).entity(createdCarDto).build();
    }
}