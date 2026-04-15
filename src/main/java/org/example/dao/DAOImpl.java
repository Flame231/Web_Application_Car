package org.example.dao;


import jakarta.persistence.EntityManager;
import lombok.Getter;
import org.example.connector.HibernateUtil;
import org.hibernate.HibernateException;

import java.io.Serializable;

@Getter
public class DAOImpl<T> implements DAO<T> {
    private EntityManager em = HibernateUtil.getEntityManager();
    private Class<T> tclass;

    public DAOImpl(Class<T> tclass) {
        this.tclass = tclass;
    }

    @Override
    public void save(T t) {
        begin();
        em.persist(t);
        commit();
    }

    @Override
    public T get(Serializable id) {
        return em.find(tclass, id);
    }

    @Override
    public void update(T t) {
        begin();
        em.merge(t);
        commit();
    }

    @Override
    public void delete(Serializable id) {
        T t = this.get(id);
        begin();
        em.remove(t);
        commit();
    }

    @Override
    public void begin() {
        em.getTransaction().begin();
    }

    @Override
    public void commit() {
        em.getTransaction().commit();
    }
}
