package org.example.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.dao.CarDAO;
import org.example.dao.CarDAOImpl;
import org.example.dto.CarDTO;
import org.example.model.Car;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CarServiceImpl implements CarService {

    CarDAO carDAO = new CarDAOImpl();

    @Override
    public CarDTO toCarDTO(Car car) {

        return new CarDTO(car.getId(), car.getBrand(),
                car.getModel(), car.getCreateDateTime(), car.getUpdateDateTime());
    }

    @Override
    public Car toCarEntity(CarDTO carDTO) {

        return new Car(carDTO.getId(), carDTO.getBrand(), carDTO.getModel());
    }

    @Override
    public void registerCar(CarDTO carDTO) {
        Car car = toCarEntity(carDTO);
        carDAO.save(car);
    }

    @Override
    public CarDTO findCar(Serializable id) {
        Car car = carDAO.get(id);
        return toCarDTO(car);
    }

    @Override
    public List<CarDTO> showAllCars() {

        List<CarDTO> carDTOList = new ArrayList<>();
        List<Car> carList = carDAO.getCarList();
        for (Car car : carList) {
            CarDTO carDTO = toCarDTO(car);
            carDTOList.add(carDTO);
        }
        return carDTOList;
    }

    @Override
    public List<CarDTO> showCarsByBrand(String brand) {
        List<CarDTO> carDTOList = new ArrayList<>();
        List<Car> carList = carDAO.getCarsByBrand(brand);
        for (Car car : carList) {
            CarDTO carDTO = toCarDTO(car);
            carDTOList.add(carDTO);
        }
        return carDTOList;

    }

    @Override
    public void updateCar(CarDTO carDTO) {
        Car car = carDAO.get(carDTO.getId());
        car.setBrand(carDTO.getBrand());
        car.setModel(carDTO.getModel());
        carDAO.update(car);
    }

    @Override
    public void removeCar(Serializable id) {
        carDAO.delete(id);
    }
}
