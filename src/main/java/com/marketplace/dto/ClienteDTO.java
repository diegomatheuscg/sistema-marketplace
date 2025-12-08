package com.marketplace.dto;

import com.marketplace.model.Cliente;
import java.time.LocalDateTime;
import java.util.List;

public record ClienteDTO(
        Long id,
        String nome,
        String email,
        String cpf,
        LocalDateTime dataCadastro,
        List<EnderecoDTO> enderecos
) {
    public ClienteDTO(Cliente cliente) {
        this(
                cliente.getId(),
                cliente.getNome(), // Herdeiro de Usuario
                cliente.getEmail(), // Herdeiro de Usuario
                cliente.getCpf(),
                cliente.getDataCadastro(),
                // Verifica se a lista não é nula antes de converter para evitar NullPointerException
                cliente.getEndereco() != null
                        ? cliente.getEndereco().stream().map(EnderecoDTO::new).toList()
                        : List.of()
        );
    }
}