package com.marketplace.dto;

import com.marketplace.model.Avaliacao;
import java.time.LocalDateTime;

public record AvaliacaoDTO(
        Long id,
        int nota,
        String comentario,
        LocalDateTime dataAvaliacao,
        String nomeCliente,
        String nomeProduto
) {
    public AvaliacaoDTO(Avaliacao avaliacao) {
        this(
                avaliacao.getId(),
                avaliacao.getNota(),
                avaliacao.getComentario(),
                avaliacao.getDataAvaliacao(),
                avaliacao.getCliente() != null ? avaliacao.getCliente().getNome() : "Anônimo",
                avaliacao.getProduto() != null ? avaliacao.getProduto().getNome() : "Produto Desconhecido"
        );
    }
}