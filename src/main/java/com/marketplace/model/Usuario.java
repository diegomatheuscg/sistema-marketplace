package com.marketplace.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuario")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(nullable = false)
    protected String nome;

    @Column(nullable = false, unique = true)
    protected String email;

    @Column(nullable = false)
    protected String senha;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    protected LocalDateTime dataCadastro;

    @UpdateTimestamp
    protected LocalDateTime dataAtualizacao;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    protected List<Endereco> enderecos = new ArrayList<>();

    public Usuario() {
    }

    protected Usuario(Builder<?> builder) {
        this.nome = builder.nome;
        this.email = builder.email;
        this.senha = builder.senha;
    }

    @SuppressWarnings("unchecked")
    public abstract static class Builder<T extends Builder<T>> {
        private String nome;
        private String email;
        private String senha;

        public T nome(String nome) {
            this.nome = nome;
            return (T) this;
        }

        public T email(String email) {
            this.email = email;
            return (T) this;
        }

        public T senha(String senha) {
            this.senha = senha;
            return (T) this;
        }

        public abstract Usuario build();
    }

    public void adicionarEndereco(Endereco endereco) {
        if (endereco == null) {
            return;
        }
        endereco.setUsuario(this);
        this.enderecos.add(endereco);
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public LocalDateTime getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(LocalDateTime dataCadastro) { this.dataCadastro = dataCadastro; }
    public LocalDateTime getDataAtualizacao() { return dataAtualizacao; }
    public void setDataAtualizacao(LocalDateTime dataAtualizacao) { this.dataAtualizacao = dataAtualizacao; }
    public List<Endereco> getEnderecos() { return enderecos; }
    public void setEnderecos(List<Endereco> enderecos) { this.enderecos = enderecos; }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("ID: ").append(id).append("\n")
                .append("Nome: ").append(nome).append("\n")
                .append("E-mail: ").append(email)
                .toString();
    }
}