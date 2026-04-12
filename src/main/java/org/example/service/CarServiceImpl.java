package org.example.service;

import org.example.connector.HibernateUtil;
import org.example.dao.CarDAO;
import org.example.dao.CarDAOImpl;
import org.example.model.Car;

import java.util.List;

public class CarServiceImpl implements CarService {

    @Override
    public void registerCar(Car car) {
        CarDAO carDAO = new CarDAOImpl(HibernateUtil.getEntityManager());
        carDAO.save(car);
    }

    @Override
    public List<Car> showAllCars() {
        CarDAO carDAO = new CarDAOImpl(HibernateUtil.getEntityManager());
        return carDAO.getCarList();
    }
}
