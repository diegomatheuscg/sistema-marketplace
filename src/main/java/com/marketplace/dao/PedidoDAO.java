package com.marketplace.dao;

import com.marketplace.model.Pedido;
import com.marketplace.model.Produto;
import com.marketplace.model.StatusPedido;

import javax.persistence.EntityManager;
import java.util.List;

public class PedidoDAO extends AbstractDAOImpl<Pedido> implements GenericDAO<Pedido>{
    public PedidoDAO(EntityManager em) {
        super(em, Pedido.class);
    }

    public List<Pedido> listarPorCliente(Long id){
        String jpql = "SELECT p FROM Pedido p WHERE p.cliente.id = :id ORDER BY p.dataPedido DESC";
        return em.createQuery(jpql, Pedido.class).
                setParameter("id", id).
                getResultList();
    }

    public List<Pedido> listarPorStatus(StatusPedido status){
        String jpql = "SELECT p FROM Pedido p WHERE p.status = :status";
        return em.createQuery(jpql, Pedido.class)
                .setParameter("status", status)
                .getResultList();
    }

}
