package com.marketplace.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "cliente")
public class Cliente extends Usuario {

    @Column(nullable = false, length = 30, unique = true)
    private String cpf;

    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos;

    @OneToMany(mappedBy = "cliente")
    private List<Avaliacao> avaliacoes;

    public Cliente() {
    }

    private Cliente(Builder builder) {
        super(builder);
        this.cpf = builder.cpf;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends Usuario.Builder<Builder> {
        private String cpf;

        public Builder cpf(String cpf) {
            this.cpf = cpf;
            return this;
        }

        @Override
        public Cliente build() {
            return new Cliente(this);
        }
    }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public List<Pedido> getPedidos() { return pedidos; }
    public void setPedidos(List<Pedido> pedidos) { this.pedidos = pedidos; }
    public List<Avaliacao> getAvaliacoes() { return avaliacoes; }
    public void setAvaliacoes(List<Avaliacao> avaliacoes) { this.avaliacoes = avaliacoes; }

    @Override
    public String toString() {
        return new StringBuilder(super.toString())
                .append("\n")
                .append("CPF: ").append(cpf)
                .toString();
    }
}