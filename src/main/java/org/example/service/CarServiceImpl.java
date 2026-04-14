package org.example.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Id;
import org.example.dao.CarDAO;
import org.example.dao.CarDAOImpl;
import org.example.model.Car;

import java.io.Serializable;
import java.nio.channels.ScatteringByteChannel;
import java.util.Collections;
import java.util.List;

public class CarServiceImpl implements CarService {
    CarDAO carDAO;
    EntityManager em;

    public CarServiceImpl(EntityManager em) {
        carDAO = new CarDAOImpl(em);
        this.em = em;
    }

    @Override
    public void registerCar(Car car) {
        em.getTransaction().begin();
            carDAO.save(car);
        em.getTransaction().commit();
    }

    @Override
    public Car findCar(Serializable id) {
        Car car = carDAO.get(id);
        if (car == null) {
            throw new EntityNotFoundException("Автомобиль с id" + id + "не найден");
        }
        return car;
    }

    @Override
    public List<Car> showAllCars() {
        em.getTransaction().begin();
        if (carDAO.getCarList() != null) {
            return carDAO.getCarList();
        }
        em.getTransaction().commit();
        return Collections.emptyList();
    }

    @Override
    public List<Car> showCarsByBrand(String brand) {
        if (carDAO.getCarsByBrand(brand) != null) {
            return carDAO.getCarsByBrand(brand);
        }
        return Collections.emptyList();
    }

    @Override
    public void updateCar(Car car) {
        em.getTransaction().begin();
        if (carDAO.get(car.getId()) != null) {
            carDAO.update(car);
        }
        em.getTransaction().commit();
    }

    @Override
    public void removeCar(Car car) {
        em.getTransaction().begin();
        carDAO.delete(car.getId());
        em.getTransaction().commit();
    }
}
