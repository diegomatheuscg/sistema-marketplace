package com.marketplace.dao;

import com.marketplace.model.Endereco;

import javax.persistence.EntityManager;
import java.util.List;

public class EnderecoDAO extends AbstractDAOImpl<Endereco> implements GenericDAO<Endereco> {

    public EnderecoDAO(EntityManager em) {
        super(em, Endereco.class);
    }

    public List<Endereco> buscarPorUsuario(Long idUsuario) {
        String jpql = "SELECT e FROM Endereco e WHERE e.usuario.id = :idUsuario";
        return em.createQuery(jpql, Endereco.class)
                .setParameter("idUsuario", idUsuario)
                .getResultList();
    }

    public List<Endereco> buscarPorEstado(String estado) {
        String jpql = "SELECT e FROM Endereco e WHERE e.estado = :estado";
        return em.createQuery(jpql, Endereco.class)
                .setParameter("estado", estado)
                .getResultList();
    }

    public Long contarEnderecosDoUsuario(Long idUsuario) {
        String jpql = "SELECT COUNT(e) FROM Endereco e WHERE e.usuario.id = :idUsuario";
        return em.createQuery(jpql, Long.class)
                .setParameter("idUsuario", idUsuario)
                .getSingleResult();
    }

    public void deletarTodosDoUsuario(Long idUsuario) {
        String jpql = "DELETE FROM Endereco e WHERE e.usuario.id = :idUsuario";
        em.createQuery(jpql)
                .setParameter("idUsuario", idUsuario)
                .executeUpdate();
    }
}