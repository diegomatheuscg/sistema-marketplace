package com.marketplace.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false, unique = true)
    private String sku;

    @Column(nullable = false)
    private String nome;

    private String descricao;
    private int estoque;

    @Column(nullable = false)
    private BigDecimal preco;

    private String urlImagem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    @OneToMany(mappedBy = "produto")
    private List<Avaliacao> avaliacoes;

    public Produto() {
    }

    private Produto(Builder builder) {
        this.sku = builder.sku;
        this.nome = builder.nome;
        this.descricao = builder.descricao;
        this.estoque = builder.estoque;
        this.preco = builder.preco;
        this.urlImagem = builder.urlImagem;
        this.categoria = builder.categoria;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String sku;
        private String nome;
        private String descricao;
        private int estoque;
        private BigDecimal preco;
        private String urlImagem;
        private Categoria categoria;

        public Builder sku(String sku) { this.sku = sku; return this; }
        public Builder nome(String nome) { this.nome = nome; return this; }
        public Builder descricao(String descricao) { this.descricao = descricao; return this; }
        public Builder estoque(int estoque) { this.estoque = estoque; return this; }
        public Builder preco(BigDecimal preco) { this.preco = preco; return this; }
        public Builder urlImagem(String urlImagem) { this.urlImagem = urlImagem; return this; }
        public Builder categoria(Categoria categoria) { this.categoria = categoria; return this; }

        public Produto build() {
            return new Produto(this);
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public int getEstoque() { return estoque; }
    public void setEstoque(int estoque) { this.estoque = estoque; }
    public BigDecimal getPreco() { return preco; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }
    public String getUrlImagem() { return urlImagem; }
    public void setUrlImagem(String urlImagem) { this.urlImagem = urlImagem; }
    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
    public List<Avaliacao> getAvaliacoes() { return avaliacoes; }
    public void setAvaliacoes(List<Avaliacao> avaliacoes) { this.avaliacoes = avaliacoes; }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("[SKU: ").append(sku).append("] ").append(nome).append("\n")
                .append("Preço: R$ ").append(preco).append("\n")
                .append("Estoque: ").append(estoque).append(" un.")
                .toString();
    }
}