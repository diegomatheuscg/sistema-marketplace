package com.marketplace;

import com.marketplace.model.*;
import com.marketplace.dto.*;
import com.marketplace.service.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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

        Cliente cliente = new Cliente.Builder()
                .nome("Diego Matheus de Carvalho Gonçalves")
                .cpf("099.992.159-20")
                .email("diegogon699@gmail.com")
                .senha("six944565").build();
        clienteService.cadastrarCliente(cliente);

        Endereco endereco1 = new Endereco.Builder()
                .cep("87704-200")
                .bairro("Jardim Ouro Branco")
                .complemento("Próximo ao mercado Amigão")
                .logradouro("Rua Lapa")
                .numero("1374")
                .usuario(cliente).build();

        enderecoService.cadastrarEndereco(endereco1);

    }
}