package com.marketplace.dao;

import com.marketplace.model.Cliente;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class ClienteDAO extends AbstractDAOImpl<Cliente> implements GenericDAO<Cliente> {

    public ClienteDAO(EntityManager em) {
        super(em, Cliente.class);
    }

    public Cliente buscarPorEmail(String email) {
        try {
            String jpql = "SELECT c FROM Cliente c WHERE c.email = :email";
            return em.createQuery(jpql, Cliente.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public boolean existeCpf(String cpf) {
        String jpql = "SELECT COUNT(c) FROM Cliente c WHERE c.cpf = :cpf";
        Long quantidade = em.createQuery(jpql, Long.class)
                .setParameter("cpf", cpf)
                .getSingleResult();

        return quantidade > 0;
    }

    public boolean existeEmail(String email) {
        String jpql = "SELECT COUNT(c) FROM Cliente c WHERE c.email = :email";
        Long quantidade = em.createQuery(jpql, Long.class)
                .setParameter("email", email)
                .getSingleResult();

        return quantidade > 0;
    }

    public Cliente buscarPorIdComEnderecos(Long id) {
        String jpql = "SELECT c FROM Cliente c LEFT JOIN FETCH c.enderecos WHERE c.id = :id";
        try {
            return em.createQuery(jpql, Cliente.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}