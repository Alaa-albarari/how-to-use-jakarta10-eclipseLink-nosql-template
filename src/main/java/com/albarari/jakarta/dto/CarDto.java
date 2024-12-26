package com.albarari.jakarta.dto;

/**
 * Data Transfer Object (DTO) for Car entity.
 */
public class CarDto {

    private String id;
    private String make;
    private String model;
    private String year;

    /**
     * Default constructor.
     */
    public CarDto() {
    }

    /**
     * Parameterized constructor to create a CarDto instance.
     *
     * @param id    the unique identifier for the Car
     * @param make  the make of the car
     * @param model the model of the car
     * @param year  the manufacturing year of the car
     */
    public CarDto(String id, String make, String model, String year) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
    }

    /**
     * Gets the unique identifier for the Car.
     *
     * @return the unique identifier
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the Car.
     *
     * @param id the unique identifier
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the make of the car.
     *
     * @return the make of the car
     */
    public String getMake() {
        return make;
    }

    /**
     * Sets the make of the car.
     *
     * @param make the make of the car
     */
    public void setMake(String make) {
        this.make = make;
    }

    /**
     * Gets the model of the car.
     *
     * @return the model of the car
     */
    public String getModel() {
        return model;
    }

    /**
     * Sets the model of the car.
     *
     * @param model the model of the car
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Gets the manufacturing year of the car.
     *
     * @return the manufacturing year of the car
     */
    public String getYear() {
        return year;
    }

    /**
     * Sets the manufacturing year of the car.
     *
     * @param year the manufacturing year of the car
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * Returns a string representation of the CarDto object.
     *
     * @return a string representation of the CarDto object
     */
    @Override
    public String toString() {
        return "CarDto{" +
                "id='" + id + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year='" + year + '\'' +
                '}';
    }
}