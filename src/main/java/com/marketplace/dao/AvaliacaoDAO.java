package com.marketplace.dao;

import com.marketplace.model.Avaliacao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class AvaliacaoDAO extends AbstractDAOImpl<Avaliacao> implements GenericDAO<Avaliacao> {

    public AvaliacaoDAO(EntityManager em) {
        super(em, Avaliacao.class);
    }

    public List<Avaliacao> buscarPorProduto(Long idProduto) {
        String jpql = "SELECT a FROM Avaliacao a WHERE a.produto.id = :idProduto ORDER BY a.dataAvaliacao DESC";
        return em.createQuery(jpql, Avaliacao.class)
                .setParameter("idProduto", idProduto)
                .getResultList();
    }

    public Double buscarMediaNotaProduto(Long idProduto) {
        String jpql = "SELECT AVG(a.nota) FROM Avaliacao a WHERE a.produto.id = :idProduto";
        Double media = em.createQuery(jpql, Double.class)
                .setParameter("idProduto", idProduto)
                .getSingleResult();

        return media != null ? media : 0.0;
    }

    public boolean existeAvaliacaoDoCliente(Long idCliente, Long idProduto) {
        String jpql = "SELECT COUNT(a) FROM Avaliacao a WHERE a.cliente.id = :idCliente AND a.produto.id = :idProduto";
        Long count = em.createQuery(jpql, Long.class)
                .setParameter("idCliente", idCliente)
                .setParameter("idProduto", idProduto)
                .getSingleResult();

        return count > 0;
    }
}