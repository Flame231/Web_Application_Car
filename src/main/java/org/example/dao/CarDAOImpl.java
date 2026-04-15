package org.example.dao;

import jakarta.persistence.Query;
import org.example.model.Car;

import java.util.List;

public class CarDAOImpl extends DAOImpl<Car> implements CarDAO {

    public CarDAOImpl() {
        super(Car.class);
    }

    @Override
    public List<Car> getCarList() {
        super.getEm().clear();
        return super.getEm().createQuery("from Car car", Car.class).getResultList();
    }

    @Override
    public List<Car> getCarsByBrand(String brand) {
        super.getEm().clear();
        Query query = super.getEm().createQuery("from Car car where car.brand =: brand", Car.class);
        query.setParameter("brand", brand);
        return query.getResultList();
    }


}
