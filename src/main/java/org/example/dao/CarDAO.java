package org.example.dao;

import org.example.model.Car;

import java.util.List;
import java.util.function.DoubleToIntFunction;

public interface CarDAO extends DAO<Car>{

    List<Car> getCarList();

    List<Car> getCarsByBrand(String brand);
}
