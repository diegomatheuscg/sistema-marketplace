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


    public boolean clienteComprouProduto(Long idCliente, Long idProduto) {
        String jpql = "SELECT COUNT(p) FROM Pedido p JOIN p.itens i " +
                "WHERE p.cliente.id = :idCliente " +
                "AND i.produto.id = :idProduto " +
                "AND p.status = 'ENTREGUE'";

        Long count = em.createQuery(jpql, Long.class)
                .setParameter("idCliente", idCliente)
                .setParameter("idProduto", idProduto)
                .getSingleResult();

        return count > 0;
    }

    public Pedido buscarPedidoComItens(Long id) {
        String jpql = "SELECT p FROM Pedido p JOIN FETCH p.itens i JOIN FETCH i.produto WHERE p.id = :id";
        try {
            return em.createQuery(jpql, Pedido.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (javax.persistence.NoResultException e) {
            return null;
        }
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
