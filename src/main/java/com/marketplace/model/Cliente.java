package com.marketplace.model;

import java.time.LocalDateTime;

public class Cliente extends Usuario {
    private String cpf;

    public Cliente(Long id, String nome, String email, String senha, Endereco endereco, LocalDateTime dataCadastro,
            LocalDateTime dataAtualizacao, String cpf) {
        super(id, nome, email, senha, endereco, dataCadastro, dataAtualizacao);
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}
