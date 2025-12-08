package com.marketplace.dto;

import com.marketplace.model.Produto;
import java.math.BigDecimal;

public record ProdutoDTO(
        Long id,
        String sku,
        String nome,
        String descricao,
        BigDecimal preco,
        String urlImagem,
        String nomeCategoria
) {
    public ProdutoDTO(Produto produto) {
        this(
                produto.getId(),
                produto.getSku(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getUrlImagem(),
                // Tratamento seguro para null caso o produto esteja sem categoria
                produto.getCategoria() != null ? produto.getCategoria().getNome() : "Sem Categoria"
        );
    }
}