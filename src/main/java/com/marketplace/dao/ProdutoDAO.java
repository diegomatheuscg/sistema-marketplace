package com.marketplace.dao;

import com.marketplace.model.Produto;
import javax.persistence.EntityManager;
import java.util.List;

public class ProdutoDAO extends AbstractDAOImpl<Produto> implements GenericDAO<Produto> {

    // O construtor agora recebe o EntityManager e o passa para a classe pai.
    public ProdutoDAO(EntityManager em) {
        super(em, Produto.class);
    }

    public List<Produto> findByName(String nome) {
        return entityManager.createQuery("SELECT p FROM Produto p WHERE lower(p.nome) LIKE lower(:nome)", Produto.class)
                .setParameter("nome", "%" + nome + "%")
                .getResultList();
    }

    public List<Produto> findByCategory(String nomeCategoria) {
        return entityManager.createQuery("SELECT p FROM Produto p JOIN p.categoria c WHERE lower(c.nome) = lower(:nomeCategoria)", Produto.class)
                .setParameter("nomeCategoria", nomeCategoria)
                .getResultList();
    }
}
