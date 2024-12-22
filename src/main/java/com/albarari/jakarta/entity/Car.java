package com.albarari.jakarta.entity;

import jakarta.persistence.*;
import org.eclipse.persistence.nosql.annotations.NoSql;
import org.eclipse.persistence.nosql.annotations.DataFormatType;
import org.bson.types.ObjectId;

/**
 * Represents a Car entity that will be stored in a MongoDB database.
 */
@Entity
@Table(name = "Car")
@NoSql(dataType = "Car", dataFormat = DataFormatType.MAPPED)
public class Car {

    /**
     * The unique identifier for the Car document in MongoDB.
     */
    @Id
    @Column(name = "_id")
    private String id;

    /**
     * The make of the car (e.g., Toyota, Ford).
     */
    private String make;

    /**
     * The model of the car (e.g., Camry, Focus).
     */
    private String model;

    /**
     * The manufacturing year of the car.
     */
    private String year;

    /**
     * Default constructor.
     */
    public Car() {
    }

    /**
     * Parameterized constructor to create a Car instance.
     *
     * @param id    the unique identifier for the Car document
     * @param make  the make of the car
     * @param model the model of the car
     * @param year  the manufacturing year of the car
     */
    public Car(String id, String make, String model, String year) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
    }

    /**
     * Gets the unique identifier for the Car document.
     *
     * @return the unique identifier
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the Car document.
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
     * Ensures that the id is set before persisting the Car document.
     * If the id is null, a new ObjectId is generated and set.
     */
    @PrePersist
    private void ensureId() {
        this.id = this.id == null ? (new ObjectId()).toHexString() : this.id;
    }

    /**
     * Returns a string representation of the Car object.
     *
     * @return a string representation of the Car object
     */
    @Override
    public String toString() {
        return "Car{" +
                "id='" + id + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                '}';
    }
}