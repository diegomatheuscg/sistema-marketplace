package com.marketplace.service;

import com.marketplace.dao.AvaliacaoDAO;
import com.marketplace.model.Avaliacao;
import com.marketplace.util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class AvaliacaoService {

    public void avaliarProduto(Avaliacao avaliacao) {
        EntityManager em = JPAUtil.getEntityManager();
        AvaliacaoDAO dao = new AvaliacaoDAO(em);

        try {
            em.getTransaction().begin();

            if (avaliacao.getCliente() == null || avaliacao.getProduto() == null) {
                throw new IllegalArgumentException("Avaliação deve estar vinculada a um cliente e um produto.");
            }

            boolean jaAvaliou = dao.existeAvaliacaoDoCliente(
                    avaliacao.getCliente().getId(),
                    avaliacao.getProduto().getId()
            );

            if (jaAvaliou) {
                throw new IllegalArgumentException("Você já avaliou este produto anteriormente.");
            }

            dao.create(avaliacao);

            em.getTransaction().commit();
            System.out.println("Avaliação registrada com sucesso!");

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao registrar avaliação: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public List<Avaliacao> listarAvaliacoesDoProduto(Long idProduto) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return new AvaliacaoDAO(em).buscarPorProduto(idProduto);
        } finally {
            em.close();
        }
    }

    public Double buscarMediaDoProduto(Long idProduto) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return new AvaliacaoDAO(em).buscarMediaNotaProduto(idProduto);
        } finally {
            em.close();
        }
    }
}