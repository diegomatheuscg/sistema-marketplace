package com.marketplace.service;

import com.marketplace.dao.CategoriaDAO;
import com.marketplace.dto.CategoriaDTO;
import com.marketplace.model.Categoria;
import com.marketplace.util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class CategoriaService {

    public void cadastrarCategoria(Categoria categoria) {
        EntityManager em = JPAUtil.getEntityManager();
        CategoriaDAO categoriaDAO = new CategoriaDAO(em);

        try {
            em.getTransaction().begin();

            if (categoria.getNome() == null || categoria.getNome().trim().isEmpty()) {
                throw new IllegalArgumentException("O nome da categoria não pode ser vazio.");
            }

            if (categoriaDAO.existePorNome(categoria.getNome())) {
                throw new IllegalArgumentException("Já existe uma categoria com este nome: " + categoria.getNome());
            }

            categoriaDAO.create(categoria);

            em.getTransaction().commit();
            System.out.println("Categoria cadastrada com sucesso: " + categoria.getNome());

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao cadastrar categoria: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void atualizarCategoria(Long id, String novoNome) {
        EntityManager em = JPAUtil.getEntityManager();
        CategoriaDAO categoriaDAO = new CategoriaDAO(em);

        try {
            em.getTransaction().begin();

            Categoria categoria = categoriaDAO.findById(id);

            if (categoria == null) {
                throw new IllegalArgumentException("Categoria não encontrada.");
            }

            if (novoNome == null || novoNome.trim().isEmpty()) {
                throw new IllegalArgumentException("O novo nome não pode ser vazio.");
            }

            if (!categoria.getNome().equalsIgnoreCase(novoNome) && categoriaDAO.existePorNome(novoNome)) {
                throw new IllegalArgumentException("Já existe outra categoria com o nome: " + novoNome);
            }

            categoria.setNome(novoNome);
            categoriaDAO.update(categoria);

            em.getTransaction().commit();
            System.out.println("Categoria atualizada com sucesso!");

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao atualizar categoria: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void deletarCategoria(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        CategoriaDAO categoriaDAO = new CategoriaDAO(em);

        try {
            em.getTransaction().begin();

            Categoria categoria = categoriaDAO.buscarPorIdComProdutos(id);

            if (categoria == null) {
                categoria = categoriaDAO.findById(id);
                if (categoria == null) throw new IllegalArgumentException("Categoria não encontrada.");
            }

            if (categoria.getProdutos() != null && !categoria.getProdutos().isEmpty()) {
                throw new IllegalArgumentException("Não é possível deletar a categoria '" + categoria.getNome() +
                        "' pois ela possui " + categoria.getProdutos().size() + " produtos vinculados.");
            }

            categoriaDAO.delete(categoria);

            em.getTransaction().commit();
            System.out.println("Categoria removida com sucesso.");

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao deletar categoria: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public List<CategoriaDTO> listarTodas() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return new CategoriaDAO(em).findAll()
                    .stream()
                    .map(CategoriaDTO::new)
                    .toList();
        } finally {
            em.close();
        }
    }

    public List<CategoriaDTO> buscarCategoriasComProdutos() {
        EntityManager em = JPAUtil.getEntityManager();
        try {

            return new CategoriaDAO(em).findCategoriesWithProducts()
                    .stream()
                    .map(CategoriaDTO::new)
                    .toList();
        } finally {
            em.close();
        }
    }

    public CategoriaDTO buscarPorId(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Categoria categoria = new CategoriaDAO(em).findById(id);
            if (categoria == null) return null;

            return new CategoriaDTO(categoria);
        } finally {
            em.close();
        }
    }
}