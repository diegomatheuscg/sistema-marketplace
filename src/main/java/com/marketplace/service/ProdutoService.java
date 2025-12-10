package com.marketplace.service;

import com.marketplace.dao.ProdutoDAO;
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
            em.getTransaction().commit();
            System.out.println("Produto cadastrado: " + produto.getNome());
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao cadastrar produto: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void atualizarProduto(Produto produto) {
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDAO dao = new ProdutoDAO(em);
        try {
            em.getTransaction().begin();
            dao.update(produto);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void removerProduto(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(em);

        try {
            em.getTransaction().begin();
            Produto produto = produtoDAO.findById(id);

            if (produto == null) {
                throw new IllegalArgumentException("Nenhum produto com esse ID foi encontrado");
            }
            produtoDAO.delete(produto);
            em.getTransaction().commit();
        } catch (Exception e) {
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao remover o produto" + e.getMessage());
        } finally {
            em.close();
        }
    }

    public List<Produto> listarTodos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return new ProdutoDAO(em).findAll();
        } finally {
            em.close();
        }
    }

    public List<Produto> buscarPorTermo(String termo) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return new ProdutoDAO(em).buscarPorTermo(termo);
        } finally {
            em.close();
        }
    }

    public Produto buscarDetalhesProduto(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return new ProdutoDAO(em).buscarPorIdComDetalhes(id);
        } finally {
            em.close();
        }
    }

    public void atualizarEstoque(Long idProduto, int novaQuantidade) {
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDAO dao = new ProdutoDAO(em);
        try {
            em.getTransaction().begin();
            Produto produto = dao.findById(idProduto);
            if (produto != null) {
                produto.setEstoque(novaQuantidade);
                dao.update(produto);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}