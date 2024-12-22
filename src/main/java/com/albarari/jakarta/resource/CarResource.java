package com.albarari.jakarta.resource;

import com.albarari.jakarta.entity.Car;
import com.albarari.jakarta.service.CarService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

/**
 * RESTful resource class for managing Car entities.
 */
@Path("/cars") // Base URL for this resource
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CarResource {

    private static final Logger logger = LogManager.getLogger(CarResource.class);

    @Inject
    private CarService carService;

    /**
     * Retrieves a Car entity by its ID.
     *
     * @param id the ID of the Car entity to retrieve
     * @return the Response containing the Car entity or a status code
     */
    @GET
    @Path("/{id}")
    public Response getCarById(@PathParam("id") String id) {
        logger.info("Retrieving car with ID: {}", id);
        try {
            Car car = carService.getCarById(id);
            if (car == null) {
                logger.warn("Car not found with ID: {}", id);
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            logger.info("Car retrieved: {}", car);
            return Response.ok(car).build();
        } catch (IllegalArgumentException e) {
            logger.error("Invalid ObjectId format for id: {}", id, e);
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid ObjectId format for id: " + id)
                    .build();
        }
    }

    /**
     * Retrieves all Car entities.
     *
     * @return a list of all Car entities
     */
    @GET
    public List<Car> getAllCars() {
        logger.info("Retrieving all cars");
        List<Car> cars = carService.getAllCars();
        logger.info("Retrieved {} cars", cars.size());
        return cars;
    }

    /**
     * Adds a new Car entity.
     *
     * @param car the Car entity to add
     * @return the Response containing the created Car entity
     */
    @POST
    public Response addCar(Car car) {
        logger.info("Adding new car: {}", car);
        Car createdCar = carService.saveCar(car);
        logger.info("Car added: {}", createdCar);
        return Response.status(Response.Status.CREATED).entity(createdCar).build();
    }
}