package com.albarari.jakarta.resource;

import com.albarari.jakarta.entity.Car;
import com.albarari.jakarta.service.CarService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import org.bson.types.ObjectId;

@Path("/cars") // Base URL for this resource
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CarResource {

    @Inject
    private CarService carService;

    // Get a car by ID
    @GET
    @Path("/{id}")
    public Response getCarById(@PathParam("id") String id) {
        try {
            // Convert the string id to ObjectId
//            ObjectId objectId = new ObjectId(id);
            Car car = carService.getCarById(id);

            if (car == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(car).build();
        } catch (IllegalArgumentException e) {
            // Handle invalid ObjectId format
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Invalid ObjectId format for id: " + id)
                    .build();
        }
    }

    // Get all cars
    @GET
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    // Add a new car
    @POST
    public Response addCar(Car car) {
        Car createdCar = carService.addCar(car);
        return Response.status(Response.Status.CREATED).entity(createdCar).build();
    }
}

