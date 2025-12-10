package com.marketplace.model;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
@Table(name = "avaliacao")
public class Avaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int nota;
    private String comentario;
    private LocalDateTime dataAvaliacao;
    @ManyToOne
    @JoinColumn(name="id_produto")
    private Produto produto;
    @ManyToOne
    @JoinColumn(name="id_cliente")
    private Cliente cliente;

    public Avaliacao(){

    }

    public Avaliacao(int nota, String comentario, LocalDateTime dataAvaliacao) {
        this.nota = nota;
        this.comentario = comentario;
        this.dataAvaliacao = dataAvaliacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDateTime getDataAvaliacao() {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(LocalDateTime dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Nota: ").append(nota).append("/5\n")
                .append("Comentário: \"").append(comentario).append("\"")
                .toString();
    }
}
