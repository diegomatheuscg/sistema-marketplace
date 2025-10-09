package com.marketplace.model;

import java.time.LocalDateTime;

public class Pedido {
    public Long id;
    private String codigo;
    private LocalDateTime dataPedido;
    private LocalDateTime dataPagamento;
    private LocalDateTime dataEnvio;
    private double valorProdutos;
    private double valorFrete;
    private double valorTotal;
    private Endereco enderecoEntrega;
    private String codigoRastreio;
    private StatusPedido status;

    public Pedido(Long id, String codigo, LocalDateTime dataPedido, LocalDateTime dataPagamento,
            LocalDateTime dataEnvio, double valorProdutos, double valorFrete, double valorTotal,
            Endereco enderecoEntrega, String codigoRastreio, StatusPedido status) {
        this.id = id;
        this.codigo = codigo;
        this.dataPedido = dataPedido;
        this.dataPagamento = dataPagamento;
        this.dataEnvio = dataEnvio;
        this.valorProdutos = valorProdutos;
        this.valorFrete = valorFrete;
        this.valorTotal = valorTotal;
        this.enderecoEntrega = enderecoEntrega;
        this.codigoRastreio = codigoRastreio;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDateTime getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDateTime dataPedido) {
        this.dataPedido = dataPedido;
    }

    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDateTime dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public LocalDateTime getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(LocalDateTime dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public double getValorProdutos() {
        return valorProdutos;
    }

    public void setValorProdutos(double valorProdutos) {
        this.valorProdutos = valorProdutos;
    }

    public double getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(double valorFrete) {
        this.valorFrete = valorFrete;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Endereco getEnderecoEntrega() {
        return enderecoEntrega;
    }

    public void setEnderecoEntrega(Endereco enderecoEntrega) {
        this.enderecoEntrega = enderecoEntrega;
    }

    public String getCodigoRastreio() {
        return codigoRastreio;
    }

    public void setCodigoRastreio(String codigoRastreio) {
        this.codigoRastreio = codigoRastreio;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

}
