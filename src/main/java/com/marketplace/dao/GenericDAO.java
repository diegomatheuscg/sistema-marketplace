package com.marketplace.dao;

import java.util.List;

public interface GenericDAO<T> {
    void create(T entity);
    void update(T entity);
    void delete(T entity);
    T findById();
    List<T> listAll();
}
