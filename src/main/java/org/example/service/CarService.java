package org.example.service;

import org.example.dto.CarDTO;
import org.example.model.Car;

import java.io.Serializable;
import java.util.List;

public interface CarService {

    CarDTO toCarDTO(Car car);

    Car toCarEntity(CarDTO carDTO);

    void registerCar(CarDTO carDTO);

    Car findCar(Serializable id);

    void updateCar(CarDTO carDTO);

    void removeCar(Serializable id);

    List<CarDTO> showAllCars();

    List<Car> showCarsByBrand(String brand);

}
