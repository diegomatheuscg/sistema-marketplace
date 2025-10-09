package com.marketplace.dao;

import javax.persistence.EntityManager;

public abstract class AbstractDAOImpl<T> implements GenericDAO<T> {
    protected EntityManager entityManager;
    protected Class<T> entity;

    public AbstractDAOImpl(EntityManager entityManager, Class<T> entity) {
        this.entityManager = entityManager;
        this.entity = entity;
    }

    @Override
    public void create(T entity){
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    public void update(T entity){
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
    }

    public void delete(T entity){

    }
}
