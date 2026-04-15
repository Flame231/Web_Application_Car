package org.example.dao;

import java.io.Serializable;

public interface DAO <T>{

    void save(T t);

    T get(Serializable id) ;

    void update(T t);

    void delete(Serializable id);

    void begin();

    void commit();
}
