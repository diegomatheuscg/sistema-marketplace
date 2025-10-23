package com.marketplace.model;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Table(name = "cliente")
public class Cliente extends Usuario {
    private String cpf;
    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos;

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
