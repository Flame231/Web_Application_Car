package org.example.dao;

import jakarta.persistence.EntityManager;
import org.example.Entity.Car;

public class CarDAOImpl extends DAOImpl<Car> implements CarDAO{
    public CarDAOImpl(EntityManager em) {
        super(em, Car.class);
    }
}
