package com.marketplace.dto;

import com.marketplace.model.ItemPedido;

import java.math.BigDecimal;

public record ItemPedidoDTO(
        String nomeProduto,
        int quantidade,
        BigDecimal precoUnitario,
        BigDecimal subTotal
) {
    public ItemPedidoDTO(ItemPedido item) {
        this(
                item.getProduto().getNome(),
                item.getQuantidade(),
                item.getPrecoUnitario(),
                item.getValorTotal()
        );
    }
}