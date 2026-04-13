package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.example.connector.HibernateUtil;
import org.example.model.Car;

import java.util.List;

public class CarDAOImpl extends DAOImpl<Car> implements CarDAO {
    @Override
    public List<Car> getCarList() {
        return getEm().createQuery("from Car car", Car.class)
                .getResultStream().toList();
    }

    @Override
    public List<Car> getCarsByBrand(String brand) {
        Query query = HibernateUtil.getEntityManager().createQuery("from Car car where car.brand =: brand", Car.class);
        query.setParameter("brand",brand);
        return query.getResultList();
    }

    public CarDAOImpl(EntityManager em) {
        super(em, Car.class);

    }
}
