package com.marketplace.dao;

import com.marketplace.model.Produto;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.List;

public class ProdutoDAO extends AbstractDAOImpl<Produto> implements GenericDAO<Produto> {

    public ProdutoDAO(EntityManager em) {
        super(em, Produto.class);
    }

    public List<Produto> findByName(String nome) {
        return em.createQuery("SELECT p FROM Produto p WHERE lower(p.nome) LIKE lower(:nome)", Produto.class)
                .setParameter("nome", "%" + nome + "%")
                .getResultList();
    }

    public List<Produto> findByCategory(String nomeCategoria) {
        return em.createQuery("SELECT p FROM Produto p JOIN p.categoria c WHERE lower(c.nome) = lower(:nomeCategoria)", Produto.class)
                .setParameter("nomeCategoria", nomeCategoria)
                .getResultList();
    }

    public List<Produto> buscarPorTermo(String termo) {
        String jpql = "SELECT p FROM Produto p WHERE lower(p.nome) LIKE lower(:termo) OR lower(p.descricao) LIKE lower(:termo)";
        return em.createQuery(jpql, Produto.class)
                .setParameter("termo", "%" + termo + "%")
                .getResultList();
    }

    public List<Produto> buscarPorFiltros(String nomeCategoria, BigDecimal precoMin, BigDecimal precoMax) {
        String jpql = "SELECT p FROM Produto p LEFT JOIN p.categoria c WHERE " +
                "(:categoria IS NULL OR lower(c.nome) = lower(:categoria)) AND " +
                "(:min IS NULL OR p.preco >= :min) AND " +
                "(:max IS NULL OR p.preco <= :max)";

        return em.createQuery(jpql, Produto.class)
                .setParameter("categoria", nomeCategoria)
                .setParameter("min", precoMin)
                .setParameter("max", precoMax)
                .getResultList();
    }

    public List<Produto> buscarProdutosComBaixoEstoque(int limiteMinimo) {
        return em.createQuery("SELECT p FROM Produto p WHERE p.estoque < :limite", Produto.class)
                .setParameter("limite", limiteMinimo)
                .getResultList();
    }

    public Produto buscarPorIdComDetalhes(Long id) {
        String jpql = "SELECT p FROM Produto p JOIN FETCH p.categoria WHERE p.id = :id";
        try {
            return em.createQuery(jpql, Produto.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}