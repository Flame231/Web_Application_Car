package org.example.dao;


import jakarta.persistence.EntityManager;
import lombok.Getter;
import org.hibernate.HibernateException;

import java.io.Serializable;

@Getter
public class DAOImpl<T> implements DAO<T> {
    EntityManager em;
    private Class<T> tclass;

    public DAOImpl(Class<T> tclass, EntityManager em) {
        this.tclass = tclass;
        this.em = em;
    }

    @Override
    public void save(T t) {
        try {
            em.persist(t);
        } catch (HibernateException e) {

        }
    }

    @Override
    public T get(Serializable id) {
        T t = null;
        try {
            t = em.find(tclass, id);
        } catch (Exception e) {
                    }
        return t;
    }

    @Override
    public void update(T t) {
        try {
            em.merge(t);
        } catch (HibernateException e) {
        }
    }

    @Override
    public void delete(Serializable id) {
        try {
            try {
                T t = this.get(id);
                em.remove(t);
            } catch (Exception e) {
                throw new Exception();
            }
        } catch (Exception e) {
        }
    }
}
