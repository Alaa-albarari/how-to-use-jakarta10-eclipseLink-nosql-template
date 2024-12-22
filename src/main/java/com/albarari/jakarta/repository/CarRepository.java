package com.albarari.jakarta.repository;

import com.albarari.jakarta.entity.Car;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.transaction.Transactional;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

@ApplicationScoped
public class CarRepository {

    private EntityManager em;
    public CarRepository() {
        // Default constructor for CDI
    }
    @Inject
    public CarRepository(EntityManagerFactory emf) {
        this.em = emf.createEntityManager();
    }

    public Car save(Car car) {
        if (car.getId() == null || em.find(Car.class, car.getId()) == null) {
            em.getTransaction().begin(); // Start a manual transaction
            em.persist(car);
            em.getTransaction().commit(); // Commit the transaction
            return car; // Return the newly persisted entity
        } else {
            em.getTransaction().begin(); // Start a manual transaction
            Car updatedCar = em.merge(car);
            em.getTransaction().commit(); // Commit the transaction
            return updatedCar; // Return the updated entity
        }
    }

    public Car findById(String id) {
        return em.find(Car.class, id);
    }

    public List<Car> findAll() {
        return em.createQuery("SELECT c FROM Car c", Car.class).getResultList();
    }

    public void deleteById(String id) {
        em.getTransaction().begin();
        Car car = em.find(Car.class, id);
        if (car != null) {
            System.out.println("Deleting car: " + car);
            em.remove(car);
        } else {
            System.out.println("Car with ID " + id + " not found.");
        }
        em.getTransaction().commit();
    }
}