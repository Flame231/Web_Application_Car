package org.example.service;

import org.example.dao.DAO;
import org.example.model.Car;

import java.util.List;
import java.util.Set;

public interface CarService extends DAO<Car> {
    void registerCar(Car car);

    List<Car> showAllCars();

    List<Car> showCarsByBrand(String brand);
}
