package org.example.service;

import org.example.dao.DAO;
import org.example.model.Car;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public interface CarService {

    void registerCar(Car car);

    Car findCar(Serializable id);

    void updateCar(Car car);

    void removeCar(Car car);

    List<Car> showAllCars();

    List<Car> showCarsByBrand(String brand);


}
