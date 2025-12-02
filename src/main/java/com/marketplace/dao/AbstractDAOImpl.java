package com.marketplace.dao;

import javax.persistence.EntityManager;
import java.util.List;

public abstract class AbstractDAOImpl<T> implements GenericDAO<T> {

    // O EntityManager agora é recebido, não mais criado aqui.
    protected EntityManager em;
    private final Class<T> aClass;

    public AbstractDAOImpl(EntityManager em, Class<T> aClass) {
        this.em = em;
        this.aClass = aClass;
    }

    @Override
    public void create(T entity) {
        this.em.persist(entity);
    }

    @Override
    public void update(T entity) {
        this.em.merge(entity);
    }

    @Override
    public void delete(T entity) {
        this.em.remove(this.em.contains(entity) ? entity : this.em.merge(entity));
    }

    @Override
    public T findById(Long id) {
        return this.em.find(aClass, id);
    }

    @Override
    public List<T> findAll() {
        return this.em.createQuery("SELECT e FROM " + aClass.getSimpleName() + " e", aClass).getResultList();
    }
}
