package com.marketplace;

import com.marketplace.model.*;
import com.marketplace.dto.*;
import com.marketplace.service.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- INICIANDO E-COMMERCE ---");

        // 1. services
        CategoriaService categoriaService = new CategoriaService();
        ProdutoService produtoService = new ProdutoService();
        ClienteService clienteService = new ClienteService();
        EnderecoService enderecoService = new EnderecoService();
        PedidoService pedidoService = new PedidoService();
        AvaliacaoService avaliacaoService = new AvaliacaoService();

        Pedido pedido = pedidoService.buscarPedidoCompleto(1L);

        System.out.println(pedido.getCliente().getNome()+"\n"+pedido.getCliente().getEmail());
    }
}