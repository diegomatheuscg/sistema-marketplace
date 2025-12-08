package com.marketplace.dto;

import com.marketplace.model.ItemPedido;

public record ItemPedidoDTO(
        String nomeProduto,
        int quantidade,
        double precoUnitario,
        double subTotal
) {
    public ItemPedidoDTO(ItemPedido item) {
        this(
                item.getProduto().getNome(),
                item.getQuantidade(),
                item.getPrecoUnitario(),
                // Usa o método transiente da sua model ou calcula na hora
                item.getValorTotal()
        );
    }
}