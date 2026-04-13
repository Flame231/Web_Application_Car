package org.example.dao;


import jakarta.persistence.EntityManager;
import lombok.Getter;
import org.hibernate.HibernateException;

import java.io.Serializable;

@Getter
public class DAOImpl<T> implements DAO<T> {
    private EntityManager em;
    private Class<T> tclass;

    public DAOImpl(EntityManager em, Class<T> tclass) {
        this.em = em;
        this.tclass =tclass;
    }

    @Override
    public void save(T t) {
        try {
            em.getTransaction().begin();
            em.persist(t);
            em.getTransaction().commit();
        } catch (HibernateException e) {
        }
    }

    @Override
    public T get(Serializable id){
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
            em.getTransaction().begin();
            em.merge(t);
            em.getTransaction().commit();
        } catch (HibernateException e) {
        }
    }

    @Override
    public void delete(Serializable id) {
        try {
            em.getTransaction().begin();
            em.remove(get(id));
            em.getTransaction().commit();
        } catch (Exception e) {
        }
    }
}
