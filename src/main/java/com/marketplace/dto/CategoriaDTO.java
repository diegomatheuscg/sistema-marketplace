package com.marketplace.dto;

import com.marketplace.model.Categoria;

public record CategoriaDTO(
        Long id,
        String nome,
        String descricao
) {
    public CategoriaDTO(Categoria categoria) {
        this(
                categoria.getId(),
                categoria.getNome(),
                categoria.getDescricao()
        );
    }
}