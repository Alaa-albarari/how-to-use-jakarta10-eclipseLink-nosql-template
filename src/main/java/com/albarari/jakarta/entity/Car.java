package com.albarari.jakarta.entity;

import jakarta.persistence.*;
import org.eclipse.persistence.nosql.annotations.NoSql;
import org.eclipse.persistence.nosql.annotations.DataFormatType;
import org.bson.types.ObjectId;

@Entity
@Table(name = "Car")
@NoSql(dataType = "Car", dataFormat=DataFormatType.MAPPED)
public class Car {

    @Id
    @Column(name = "_id")
    private String id;// MongoDB document ID

    private String make; // Car make (e.g., Toyota, Ford)
    private String model; // Car model (e.g., Camry, Focus)
    private String year; // Manufacturing year

    // Default Constructor
    public Car() {
//        this.id = new ObjectId();
    }

    // Parameterized Constructor
    public Car(String id, String make, String model, String year) {
//        this.id = id == null ? new ObjectId() : id;;
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        //this.id = id == null ? new ObjectId() : id;
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @PrePersist
    private void ensureId() {
        this.id = this.id ==null ? (new ObjectId()).toHexString() : this.id;
    }
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

