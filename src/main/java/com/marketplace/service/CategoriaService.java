package com.marketplace.service;

import com.marketplace.dao.CategoriaDAO;
import com.marketplace.model.Categoria;
import com.marketplace.util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class CategoriaService {
    private CategoriaDAO categoriaDAO;

    public void cadastrarCategoria(Categoria categoria) {
        EntityManager em = JPAUtil.getEntityManager();
        CategoriaDAO categoriaDAO = new CategoriaDAO(em);

        try {
            em.getTransaction().begin();
            List<Categoria> categoriasEncontradas = categoriaDAO.findByName(categoria.getNome());
            if(categoria.getNome() == null || categoria.getNome() == ""){
                throw new IllegalArgumentException("O nome da categoria não pode ser nulo.");
            }else if (!categoriasEncontradas.isEmpty()) {
                throw new IllegalArgumentException("Essa categoria já existe!");
            }
            categoriaDAO.create(categoria);
            em.getTransaction().commit();
            System.out.println("Categoria cadastrada com sucesso!");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException(e);
        } finally {
            em.close();
        }
    }

    public void atualizarCategoria(Categoria categoria, String nome) {
        EntityManager em = JPAUtil.getEntityManager();
        CategoriaDAO categoriaDAO = new CategoriaDAO(em);
        try {
            em.getTransaction().begin();
            if(categoria == null){
                throw new IllegalArgumentException("A categoria não existe.");
            }else if (nome == null || nome == ""){
                throw new IllegalArgumentException("O nome não pode ser nulo.");
            }
            categoria.setNome(nome);
            categoriaDAO.update(categoria);
            em.getTransaction().commit();
            System.out.println("Categoria atualizada com sucesso!");
        }catch (Exception e){
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            throw new RuntimeException(e);
        }finally {
            em.close();
        }
    }

    public void deletarCategoria(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        CategoriaDAO categoriaDAO = new CategoriaDAO(em);


        try{
            em.getTransaction().begin();
            Categoria categoria = categoriaDAO.findById(id);
            if(categoria == null){
                throw new IllegalArgumentException("A categoria não existe.");
            }else if(categoria.getProdutos()!=null && !categoria.getProdutos().isEmpty()){
                throw new IllegalArgumentException("A categoria possui produtos e não pode ser deletada.");
            }
            categoriaDAO.delete(categoria);
            em.getTransaction().commit();
            System.out.println("Categoria deletada com sucesso!");
        }catch(Exception e){
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }finally{
            em.close();
        }
    }

    public List<Categoria> buscarCategoriasComProdutos() {
        EntityManager em = JPAUtil.getEntityManager();
        CategoriaDAO categoriaDAO = new CategoriaDAO(em);
        List<Categoria> categorias;
        try {
            categorias = categoriaDAO.findCategoriesWithProducts();
            if (categorias.isEmpty()) {
                System.out.println("Não foram encontradas categorias com produtos.");
                return null;
            }
        } finally {
            em.close();
        }
        return categorias;
    }

    public Categoria buscarCategoriaPorId(Long id){
        EntityManager em = JPAUtil.getEntityManager();
        CategoriaDAO categoriaDAO = new CategoriaDAO(em);
        Categoria categoria;
        try {
            categoria = categoriaDAO.findById(id);
            if (categoria != null) {
                System.out.println("Categoria encontrada: " + categoria.getNome());
            } else {
                System.out.println("Categoria não encontrada.");
                return null;
            }
        }finally{
            em.close();
        }
         return categoria;
    }
}
