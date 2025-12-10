package com.marketplace.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message="O CEP está vazio.")
    private String cep;

    @NotNull(message="O logradouro está vazio.")
    private String logradouro;

    @NotNull(message="O número está vazio.")
    private String numero;
    private String complemento;

    @NotNull(message="O bairro está vazio.")
    private String bairro;

    private String cidade;
    private String estado;
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    public Endereco(){

    }

    private Endereco(Builder builder) {
        this.cep = builder.cep;
        this.logradouro = builder.logradouro;
        this.numero = builder.numero;
        this.complemento = builder.complemento;
        this.bairro = builder.bairro;
        this.cidade = builder.cidade;
        this.estado = builder.estado;
        this.usuario = builder.usuario;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public static Builder builder(){
        return new Builder();
    }
    public static class Builder{
        private Long id;
        private String cep;
        private String logradouro;
        private String numero;
        private String complemento;
        private String bairro;
        private String cidade;
        private String estado;
        private Usuario usuario;

        public Builder cep(String cep){
            this.cep = cep;
            return this;
        }

        public Builder logradouro(String logradouro){
            this.logradouro = logradouro;
            return this;
        }

        public Builder numero(String numero){
            this.numero = numero;
            return this;
        }

        public Builder complemento(String complemento){
            this.complemento = complemento;
            return this;
        }

        public Builder bairro(String bairro){
            this.bairro = bairro;
            return this;
        }

        public Builder cidade(String cidade){
            this.cidade = cidade;
            return this;
        }

        public Builder estado(String estado){
            this.estado = estado;
            return this;
        }

        public Builder usuario(Usuario usuario){
            this.usuario = usuario;
            return this;
        }

        public Endereco build(){
            return new Endereco(this);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(logradouro+"\n")
                .append(bairro+"\n")
                .append(cidade+" - "+estado+"\n")
                .append(cep);

        return sb.toString();
    }
}
