package com.marketplace.dao;

import javax.persistence.EntityManager;
import java.util.List;

public abstract class AbstractDAOImpl<T> implements GenericDAO<T> {

    // O EntityManager agora é recebido, não mais criado aqui.
    protected EntityManager entityManager;
    private final Class<T> aClass;

    public AbstractDAOImpl(EntityManager entityManager, Class<T> aClass) {
        this.entityManager = entityManager;
        this.aClass = aClass;
    }

    @Override
    public void create(T entity) {
        this.entityManager.persist(entity);
    }

    @Override
    public void update(T entity) {
        this.entityManager.merge(entity);
    }

    @Override
    public void delete(T entity) {
        this.entityManager.remove(this.entityManager.contains(entity) ? entity : this.entityManager.merge(entity));
    }

    @Override
    public T findById(Long id) {
        return this.entityManager.find(aClass, id);
    }

    @Override
    public List<T> findAll() {
        return this.entityManager.createQuery("SELECT e FROM " + aClass.getSimpleName() + " e", aClass).getResultList();
    }
}
