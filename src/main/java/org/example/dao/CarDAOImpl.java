package org.example.dao;

import jakarta.persistence.EntityManager;
import org.example.model.Car;

import java.util.List;

public class CarDAOImpl extends DAOImpl<Car> implements CarDAO {
    @Override
    public List<Car> getCarList() {
        return getEm().createQuery("from Car car", Car.class)
                .getResultStream().toList();
    }

    public CarDAOImpl(EntityManager em) {
        super(em, Car.class);

    }
}
