package com.marketplace.service;

import com.marketplace.dao.ClienteDAO;
import com.marketplace.dto.ClienteDTO;
import com.marketplace.model.Cliente;
import com.marketplace.util.JPAUtil;

import javax.persistence.EntityManager;

public class ClienteService {

    public void cadastrarCliente(Cliente cliente) {
        EntityManager em = JPAUtil.getEntityManager();
        ClienteDAO dao = new ClienteDAO(em);

        try {
            em.getTransaction().begin();

            if (dao.existeCpf(cliente.getCpf())) {
                throw new IllegalArgumentException("Já existe um cliente cadastrado com este CPF: " + cliente.getCpf());
            }

            if (dao.existeEmail(cliente.getEmail())) {
                throw new IllegalArgumentException("Este e-mail já está em uso: " + cliente.getEmail());
            }

            dao.create(cliente);

            em.getTransaction().commit();
            System.out.println("Cliente cadastrado com sucesso: " + cliente.getNome());

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao cadastrar cliente: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public ClienteDTO buscarPerfilCliente(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Cliente cliente = new ClienteDAO(em).buscarPorIdComEnderecos(id);
            if (cliente == null) return null;

            return new ClienteDTO(cliente);
        } finally {
            em.close();
        }
    }

    public Cliente buscarPorEmail(String email) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return new ClienteDAO(em).buscarPorEmail(email);
        } finally {
            em.close();
        }
    }

    public void atualizarCliente(Cliente cliente) {
        EntityManager em = JPAUtil.getEntityManager();
        ClienteDAO dao = new ClienteDAO(em);
        try {
            em.getTransaction().begin();

            if (dao.findById(cliente.getId()) == null) {
                throw new IllegalArgumentException("Cliente não encontrado.");
            }

            dao.update(cliente);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao atualizar cliente: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public ClienteDTO autenticar(String email, String senha) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            ClienteDAO dao = new ClienteDAO(em);
            Cliente cliente = dao.buscarPorEmail(email);

            if (cliente == null || !cliente.getSenha().equals(senha)) {
                throw new IllegalArgumentException("Usuário ou senha inválidos.");
            }

            return new ClienteDTO(cliente);
        } finally {
            em.close();
        }
    }
}