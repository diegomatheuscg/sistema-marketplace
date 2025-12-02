package com.marketplace.service;

import com.marketplace.dao.CategoriaDAO;
import com.marketplace.model.Categoria;
import com.marketplace.util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class CategoriaService {
    private CategoriaDAO categoriaDAO;

    public void cadastrarCategoria(Categoria categoria) {
        EntityManager em = JPAUtil.getEntityManager();
        CategoriaDAO categoriaDAO = new CategoriaDAO(em);

        try{
            em.getTransaction().begin();
            List<Categoria> categoriasEncontradas = categoriaDAO.findByName(categoria.getNome());

            if(categoriasEncontradas.size()>0){
                throw new IllegalArgumentException("Essa categoria já existe!");
            }

            categoriaDAO.create(categoria);

            System.out.println("Categoria cadastrada com sucesso!");
            em.getTransaction().commit();
        } catch (Exception e) {
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            throw new RuntimeException(e);
        }finally{
            em.close();
        }
    }
}
