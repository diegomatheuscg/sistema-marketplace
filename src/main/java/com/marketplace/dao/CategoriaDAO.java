package com.marketplace.dao;

import com.marketplace.model.Categoria;
import javax.persistence.EntityManager;
import java.util.List;

public class CategoriaDAO extends AbstractDAOImpl<Categoria> implements GenericDAO<Categoria> {

    public CategoriaDAO(EntityManager em) {
        super(em, Categoria.class);
    }

    public List<Categoria> findByName(String nome) {
        return entityManager.createQuery("SELECT c FROM Categoria c WHERE lower(c.nome) LIKE lower(:nome)", Categoria.class)
                .setParameter("nome", "%" + nome + "%")
                .getResultList();
    }
}
