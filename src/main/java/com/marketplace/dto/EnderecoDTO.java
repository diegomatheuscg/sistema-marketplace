package com.marketplace.dto;

import com.marketplace.model.Endereco;

public record EnderecoDTO(
        Long id,
        String cep,
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        String estado
) {
    public EnderecoDTO(Endereco endereco) {
        this(
                endereco.getId(),
                endereco.getCep(),
                endereco.getLogradouro(),
                endereco.getNumero(),
                endereco.getComplemento(),
                endereco.getBairro(),
                endereco.getCidade(),
                endereco.getEstado()
        );
    }
}