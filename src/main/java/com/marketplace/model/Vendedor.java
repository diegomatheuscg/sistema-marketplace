package com.marketplace.model;

import java.time.LocalDateTime;

public class Vendedor extends Usuario {
    private String cnpj;
    private String razaoSocial;
    private String nomeFantasia;

    public Vendedor(Long id, String nome, String email, String senha, Endereco endereco, LocalDateTime dataCadastro,
            LocalDateTime dataAtualizacao, String cnpj, String razaoSocial, String nomeFantasia) {
        super(id, nome, email, senha, endereco, dataCadastro, dataAtualizacao);
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

}
