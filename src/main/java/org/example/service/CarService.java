package org.example.service;

import org.example.model.Car;

import java.util.List;
import java.util.Set;

public interface CarService {
    void registerCar(Car car);

    List<Car> showAllCars();
}
