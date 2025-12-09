package com.marketplace.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String codigo;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime dataPedido;

    private LocalDateTime dataPagamento;
    private LocalDateTime dataEnvio;

    private BigDecimal valorProdutos = BigDecimal.ZERO;
    private BigDecimal valorFrete = BigDecimal.ZERO;
    private BigDecimal valorTotal = BigDecimal.ZERO;

    @ManyToOne
    @JoinColumn(name = "id_endereco_entrega")
    private Endereco enderecoEntrega;

    private String codigoRastreio;

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> itens = new ArrayList<>();

    public Pedido() {
    }

    private Pedido(Builder builder) {
        this.codigo = builder.codigo;
        this.cliente = builder.cliente;
        this.enderecoEntrega = builder.enderecoEntrega;
        this.status = StatusPedido.PENDENTE_PAGAMENTO;
    }

    public void adicionarItem(ItemPedido item) {
        if (item == null) {
            return;
        }
        item.setPedido(this);
        this.itens.add(item);
        recalcularValores();
    }

    public void recalcularValores() {
        this.valorProdutos = this.itens.stream()
                .map(ItemPedido::getValorTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        this.valorTotal = this.valorProdutos.add(this.valorFrete);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public LocalDateTime getDataPedido() { return dataPedido; }
    public void setDataPedido(LocalDateTime dataPedido) { this.dataPedido = dataPedido; }
    public LocalDateTime getDataPagamento() { return dataPagamento; }
    public void setDataPagamento(LocalDateTime dataPagamento) { this.dataPagamento = dataPagamento; }
    public LocalDateTime getDataEnvio() { return dataEnvio; }
    public void setDataEnvio(LocalDateTime dataEnvio) { this.dataEnvio = dataEnvio; }
    public BigDecimal getValorProdutos() { return valorProdutos; }
    public void setValorProdutos(BigDecimal valorProdutos) { this.valorProdutos = valorProdutos; }
    public BigDecimal getValorFrete() { return valorFrete; }
    public void setValorFrete(BigDecimal valorFrete) { this.valorFrete = valorFrete; recalcularValores(); }
    public BigDecimal getValorTotal() { return valorTotal; }
    public void setValorTotal(BigDecimal valorTotal) { this.valorTotal = valorTotal; }
    public Endereco getEnderecoEntrega() { return enderecoEntrega; }
    public void setEnderecoEntrega(Endereco enderecoEntrega) { this.enderecoEntrega = enderecoEntrega; }
    public String getCodigoRastreio() { return codigoRastreio; }
    public void setCodigoRastreio(String codigoRastreio) { this.codigoRastreio = codigoRastreio; }
    public StatusPedido getStatus() { return status; }
    public void setStatus(StatusPedido status) { this.status = status; }
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public List<ItemPedido> getItens() { return itens; }
    public void setItens(List<ItemPedido> itens) { this.itens = itens; }

    public static class Builder {
        private String codigo;
        private Cliente cliente;
        private Endereco enderecoEntrega;

        public Builder codigo(String codigo) {
            this.codigo = codigo;
            return this;
        }

        public Builder cliente(Cliente cliente) {
            this.cliente = cliente;
            return this;
        }

        public Builder enderecoEntrega(Endereco enderecoEntrega) {
            this.enderecoEntrega = enderecoEntrega;
            return this;
        }

        public Pedido build() {
            return new Pedido(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}