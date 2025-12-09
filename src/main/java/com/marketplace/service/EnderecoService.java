package com.marketplace.service;

import com.marketplace.dao.EnderecoDAO;
import com.marketplace.model.Endereco;
import com.marketplace.util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class EnderecoService {

    public void cadastrarEndereco(Endereco endereco) {
        EntityManager em = JPAUtil.getEntityManager();
        EnderecoDAO dao = new EnderecoDAO(em);

        try {
            em.getTransaction().begin();

            if (endereco.getUsuario() == null) {
                throw new IllegalArgumentException("O endereço deve estar vinculado a um usuário.");
            }

            Long totalExistente = dao.contarEnderecosDoUsuario(endereco.getUsuario().getId());
            if (totalExistente >= 5) {
                throw new IllegalArgumentException("Limite de endereços atingido. Remova um antigo para adicionar novo.");
            }

            dao.create(endereco);
            em.getTransaction().commit();
            System.out.println("Endereço cadastrado com sucesso!");

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao cadastrar endereço: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public List<Endereco> listarEnderecosDoUsuario(Long idUsuario) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return new EnderecoDAO(em).buscarPorUsuario(idUsuario);
        } finally {
            em.close();
        }
    }

    public void removerEndereco(Long idEndereco) {
        EntityManager em = JPAUtil.getEntityManager();
        EnderecoDAO dao = new EnderecoDAO(em);

        try {
            em.getTransaction().begin();
            Endereco endereco = dao.findById(idEndereco);

            if (endereco == null) {
                throw new IllegalArgumentException("Endereço não encontrado.");
            }

            dao.delete(endereco);
            em.getTransaction().commit();
            System.out.println("Endereço removido com sucesso.");

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao remover endereço: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public List<Endereco> buscarPorEstado(String estado) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return new EnderecoDAO(em).buscarPorEstado(estado);
        } finally {
            em.close();
        }
    }
}