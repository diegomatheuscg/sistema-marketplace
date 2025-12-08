package com.marketplace.dto;

import com.marketplace.model.Pedido;
import com.marketplace.model.StatusPedido;
import java.time.LocalDateTime;
import java.util.List;

public record PedidoDTO(
        Long id,
        String codigo,
        LocalDateTime dataPedido,
        double valorTotal,
        StatusPedido status,
        String nomeCliente,
        EnderecoDTO enderecoEntrega,
        List<ItemPedidoDTO> itens
) {
    public PedidoDTO(Pedido pedido) {
        this(
                pedido.getId(),
                pedido.getCodigo(),
                pedido.getDataPedido(),
                pedido.getValorTotal(),
                pedido.getStatus(),
                pedido.getCliente().getNome(),
                pedido.getEnderecoEntrega() != null ? new EnderecoDTO(pedido.getEnderecoEntrega()) : null,
                pedido.getItens() != null
                        ? pedido.getItens().stream().map(ItemPedidoDTO::new).toList()
                        : List.of()
        );
    }
}