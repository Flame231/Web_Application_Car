package org.example.dao;


import jakarta.persistence.EntityManager;
import lombok.Getter;
import org.example.connector.HibernateUtil;
import org.hibernate.HibernateException;

import java.io.Serializable;

@Getter
public class DAOImpl<T> implements DAO<T> {

    private Class<T> tclass;

    protected EntityManager getEm() {
        return HibernateUtil.getEntityManager();
    }
    public DAOImpl(Class<T> tclass) {
        this.tclass = tclass;
    }

    @Override
    public void save(T t) {
        begin();
        getEm().persist(t);
        commit();
    }

    @Override
    public T get(Serializable id) {
        return getEm().find(tclass, id);
    }

    @Override
    public void update(T t) {
        begin();
        getEm().merge(t);
        commit();
    }

    @Override
    public void delete(Serializable id) {
        T t = this.get(id);
        begin();
        getEm().remove(t);
        commit();
    }

    @Override
    public void begin() {
        getEm().getTransaction().begin();
    }

    @Override
    public void commit() {
        getEm().getTransaction().commit();
        getEm().clear();
    }
}
