package com.marketplace.dao;

import com.marketplace.model.Categoria;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

public class CategoriaDAO extends AbstractDAOImpl<Categoria> implements GenericDAO<Categoria> {

    public CategoriaDAO(EntityManager em) {
        super(em, Categoria.class);
    }

    public List<Categoria> findByName(String nome) {
        return em.createQuery("SELECT c FROM Categoria c WHERE lower(c.nome) LIKE lower(:nome)", Categoria.class)
                .setParameter("nome", "%" + nome + "%")
                .getResultList();
    }


    public boolean existePorNome(String nome) {
        String jpql = "SELECT COUNT(c) FROM Categoria c WHERE lower(c.nome) = lower(:nome)";
        Long count = em.createQuery(jpql, Long.class)
                .setParameter("nome", nome)
                .getSingleResult();
        return count > 0;
    }

    public List<Categoria> findCategoriesWithProducts() {
        String jpql = "SELECT DISTINCT c FROM Categoria c JOIN c.produtos p";
        return em.createQuery(jpql, Categoria.class)
                .getResultList();
    }

    public Categoria buscarPorIdComProdutos(Long id) {
        String jpql = "SELECT c FROM Categoria c LEFT JOIN FETCH c.produtos WHERE c.id = :id";
        try {
            return em.createQuery(jpql, Categoria.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}