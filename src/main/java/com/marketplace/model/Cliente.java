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
    @OneToMany(mappedBy = "cliente")
    private List<Avaliacao> avaliacoes;


    public Cliente() {
        super();
    }

    public Cliente(String nome, String email, String senha, List<Endereco> endereco, LocalDateTime dataCadastro,
            LocalDateTime dataAtualizacao, String cpf) {
        super(nome, email, senha, endereco, dataCadastro, dataAtualizacao);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes){
        this.avaliacoes = avaliacoes;
    }

}
