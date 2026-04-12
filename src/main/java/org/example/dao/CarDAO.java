package org.example.dao;

import org.example.model.Car;

import java.util.List;

public interface CarDAO extends DAO<Car>{
    List<Car> getCarList();
}
