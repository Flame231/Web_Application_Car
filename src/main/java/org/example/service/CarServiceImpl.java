package org.example.service;

import org.example.connector.HibernateUtil;
import org.example.dao.CarDAO;
import org.example.dao.CarDAOImpl;
import org.example.model.Car;

import java.util.Collections;
import java.util.List;

public class CarServiceImpl implements CarService {

    CarDAO carDAO;
    CarDAOImpl carDAOImpl;

    public CarServiceImpl(CarDAO carDAO) {
        this.carDAO = carDAO;
    }

    @Override
    public void registerCar(Car car) {
            if (carDAO.get(car.getId()) == null) {
                carDAO.save(car);
        }

    }

    @Override
    public List<Car> showAllCars() {
        if (carDAO.getCarList() != null) {
            return carDAO.getCarList();
        }
        return Collections.emptyList();

    }

    @Override
    public List<Car> showCarsByBrand(String brand) {
        if (carDAOImpl.getCarsByBrand(brand) != null) {
            return carDAO.getCarsByBrand(brand);
        }
        return Collections.emptyList();
    }

}
