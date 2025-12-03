package com.marketplace.model;

import javax.validation.constraints.NotBlank;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name = "Produto")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 10, nullable = false)
    private String sku;
    @Column(nullable = false)
    @NotBlank(message = "O nome do produto não pode ser vazio.")
    private String nome;
    @Size(min = 3, max = 500)
    private String descricao;
    private BigDecimal preco;
    private String urlImagem;
    @ManyToOne
    @JoinColumn(name = "id_categoria")
    @NotNull(message = "A categoria do produto não pode ser vazia.")
    private Categoria categoria;
    @OneToMany(mappedBy = "produto")
    private List<Avaliacao> avaliacoes;

    private Produto(Builder builder) {
        this.sku = builder.sku;
        this.nome = builder.nome;
        this.descricao = builder.descricao;
        this.preco = builder.preco;
        this.urlImagem = builder.urlImagem;
        this.categoria = builder.categoria;
        this.avaliacoes = builder.avaliacoes;
    }

    protected Produto() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    public Categoria getCategoria() { return categoria;}

    public void setCategoria(Categoria categoria) {this.categoria = categoria;}

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {this.preco = preco;}

    public static class Builder {
        private Long id;
        private String sku;
        private String nome;
        private String descricao;
        private BigDecimal preco;
        private String urlImagem;
        private Categoria categoria;
        private List<Avaliacao> avaliacoes;

        public Builder sku(String sku) {
            this.sku = sku;
            return this;
        }

        public Builder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public Builder descricao(String descricao) {
            this.descricao = descricao;
            return this;
        }

        public Builder preco(BigDecimal preco){
            this.preco = preco;
            return this;
        }

        public Builder urlImagem(String urlImagem){
            this.urlImagem = urlImagem;
            return this;
        }

        public Builder categoria(Categoria categoria){
            this.categoria = categoria;
            return this;
        }

        public Builder avaliacoes(List<Avaliacao> avaliacoes){
            this.avaliacoes = avaliacoes;
            return this;
        }

        public Produto build(){
            return new Produto(this);
        }
    }
}
