package com.marketplace.service;

import com.marketplace.dao.CategoriaDAO;
import com.marketplace.dao.ProdutoDAO;
import com.marketplace.model.Categoria;
import com.marketplace.model.Produto;
import com.marketplace.util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class ProdutoService {

    public void cadastrarProduto(Produto produto) {
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(em);

        try {
            em.getTransaction().begin();
            produtoDAO.create(produto);
            System.out.println("Cadastro realizado com sucesso!");
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            em.close();
        }
    }
}
