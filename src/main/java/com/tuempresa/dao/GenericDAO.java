package com.tuempresa.dao;

import java.util.List;

public interface GenericDAO<T, ID> {
    T create(T t);

    T update(T t);

    void delete(ID id);

    T findById(ID id);

    List<T> findAll();
}
