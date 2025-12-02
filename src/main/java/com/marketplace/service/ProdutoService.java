package com.marketplace.service;

import com.marketplace.dao.CategoriaDAO;
import com.marketplace.dao.ProdutoDAO;
import com.marketplace.model.Categoria;
import com.marketplace.model.Produto;
import com.marketplace.util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class ProdutoService {

    public void cadastrarProduto(Produto produto, Long idCategoria) {
        EntityManager em = JPAUtil.getEntityManager();
        ProdutoDAO produtoDAO = new ProdutoDAO(em);
        CategoriaDAO categoriaDAO = new CategoriaDAO(em);
        try{
            em.getTransaction().begin();
            Categoria categoria = categoriaDAO.findById(idCategoria)
        }catch(Exception ex){
            if(categoria == null){
            em.getTransaction().rollback();
        }

            if (categorias.isEmpty()) {
                throw new RuntimeException("Categoria '" + nomeCategoria + "' não encontrada!");
            }
            produto.setCategoria(categorias.get(0));
            produtoDAO.create(produto);
            em.getTransaction().commit();

    }
}
