package com.marketplace.service;

import com.marketplace.dao.PedidoDAO;
import com.marketplace.dao.ProdutoDAO;
import com.marketplace.dao.ClienteDAO;
import com.marketplace.dto.PedidoDTO;
import com.marketplace.model.ItemPedido;
import com.marketplace.model.Pedido;
import com.marketplace.model.Produto;
import com.marketplace.model.StatusPedido;
import com.marketplace.util.JPAUtil;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

public class PedidoService {

    public void realizarPedido(Pedido pedido) {
        EntityManager em = JPAUtil.getEntityManager();
        PedidoDAO pedidoDAO = new PedidoDAO(em);
        ProdutoDAO produtoDAO = new ProdutoDAO(em);
        ClienteDAO clienteDAO = new ClienteDAO(em);

        try {
            em.getTransaction().begin();

            if (pedido.getCliente() == null || pedido.getCliente().getId() == null) {
                throw new IllegalArgumentException("Pedido sem cliente identificado.");
            }

            pedido.setCliente(clienteDAO.findById(pedido.getCliente().getId()));

            if (pedido.getItens() == null || pedido.getItens().isEmpty()) {
                throw new IllegalArgumentException("O pedido deve ter pelo menos um item.");
            }


            for (ItemPedido item : pedido.getItens()) {
                Produto produtoBanco = produtoDAO.findById(item.getProduto().getId());

                if (produtoBanco == null) {
                    throw new IllegalArgumentException("Produto não encontrado: ID " + item.getProduto().getId());
                }

                if (produtoBanco.getEstoque() < item.getQuantidade()) {
                    throw new IllegalArgumentException("Estoque insuficiente para o produto: " + produtoBanco.getNome());
                }

                produtoBanco.setEstoque(produtoBanco.getEstoque() - item.getQuantidade());
                produtoDAO.update(produtoBanco);
                item.setProduto(produtoBanco);
                item.setPrecoUnitario(produtoBanco.getPreco());
                item.setPedido(pedido);
            }

            pedido.recalcularValores();
            pedido.setStatus(StatusPedido.PENDENTE_PAGAMENTO);


            if (pedido.getCodigo() == null || pedido.getCodigo().isEmpty()) {
                pedido.setCodigo("PED-" + System.currentTimeMillis());
            }

            pedidoDAO.create(pedido);

            em.getTransaction().commit();
            System.out.println("Pedido realizado com sucesso! Valor Total: " + pedido.getValorTotal());

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }

            throw new RuntimeException("Falha ao processar pedido: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void cancelarPedido(Long idPedido) {
        EntityManager em = JPAUtil.getEntityManager();
        PedidoDAO pedidoDAO = new PedidoDAO(em);
        ProdutoDAO produtoDAO = new ProdutoDAO(em);

        try {
            em.getTransaction().begin();
            Pedido pedido = pedidoDAO.buscarPedidoComItens(idPedido);

            if (pedido.getStatus() == StatusPedido.CANCELADO) {
                throw new IllegalArgumentException("Pedido já está cancelado.");
            }

            for (ItemPedido item : pedido.getItens()) {
                Produto produto = item.getProduto();
                produto.setEstoque(produto.getEstoque() + item.getQuantidade());
                produtoDAO.update(produto);
            }

            pedido.setStatus(StatusPedido.CANCELADO);
            pedidoDAO.update(pedido);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao cancelar: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void atualizarStatus(Long idPedido, StatusPedido novoStatus) {
        EntityManager em = JPAUtil.getEntityManager();
        PedidoDAO dao = new PedidoDAO(em);

        try {
            em.getTransaction().begin();
            Pedido pedido = dao.findById(idPedido);

            if (pedido == null) throw new IllegalArgumentException("Pedido não encontrado");

            pedido.setStatus(novoStatus);

            if (novoStatus == StatusPedido.ENVIADO) {
                pedido.setDataEnvio(LocalDateTime.now());
            }

            dao.update(pedido);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public List<PedidoDTO> listarPedidosDoCliente(Long idCliente) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return new PedidoDAO(em).listarPorCliente(idCliente)
                    .stream()
                    .map(PedidoDTO::new) // O DTO já trata a lista de itens e o endereço
                    .toList();
        } finally {
            em.close();
        }
    }

    public PedidoDTO buscarPedidoCompleto(Long id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Pedido pedido = new PedidoDAO(em).buscarPedidoComItens(id);
            if (pedido == null) return null;

            return new PedidoDTO(pedido);
        } finally {
            em.close();
        }
    }
}