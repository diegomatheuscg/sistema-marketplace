package com.marketplace;

import com.marketplace.dto.*;
import com.marketplace.model.*;
import com.marketplace.service.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Main {

    // Instanciamos os services como estáticos para usar em todos os métodos
    private static final CategoriaService categoriaService = new CategoriaService();
    private static final ProdutoService produtoService = new ProdutoService();
    private static final ClienteService clienteService = new ClienteService();
    private static final EnderecoService enderecoService = new EnderecoService();
    private static final PedidoService pedidoService = new PedidoService();
    private static final AvaliacaoService avaliacaoService = new AvaliacaoService();

    public static void main(String[] args) {

        System.out.println("------ Testando fluxo feliz 😂✌️ ------");
        testarFluxo();
        System.out.println("------ Testando a visualização dos dados ------");
        testarListagens();
    }

    private static void testarFluxo() {
        System.out.println("\n-----------------------------------------");
        System.out.println("▶️ EXECUTANDO: testarFluxo (Cadastros e Venda)");
        System.out.println("-----------------------------------------");

        try {
            Categoria cat = new Categoria("Eletrodomésticos", "Linha branca");
            try {
                categoriaService.cadastrarCategoria(cat);
            } catch (Exception e) { }

            Produto prod = new Produto.Builder()
                    .nome("Geladeira Inox")
                    .sku("GEL-INOX-" + System.currentTimeMillis())
                    .preco(new BigDecimal("4200.00"))
                    .descricao("Geladeira duas portas")
                    .estoque(10)
                    .categoria(cat)
                    .build();
            produtoService.cadastrarProduto(prod);

            String emailUnico = "maria" + System.currentTimeMillis() + "@email.com";
            Cliente cliente = new Cliente.Builder()
                    .nome("Maria Oliveira")
                    .cpf("999.888.777-" + (System.currentTimeMillis() % 100))
                    .email(emailUnico)
                    .senha("123456")
                    .build();
            clienteService.cadastrarCliente(cliente);

            Endereco end = new Endereco.Builder()
                    .cep("80000-000")
                    .logradouro("Rua das Palmeiras")
                    .numero("50")
                    .bairro("Jardim Botânico")
                    .cidade("Curitiba").estado("PR")
                    .usuario(cliente).build();
            enderecoService.cadastrarEndereco(end);

            System.out.println("✅ Dados iniciais cadastrados (Produto e Cliente).");

            Pedido pedido = new Pedido.Builder()
                    .cliente(cliente)
                    .enderecoEntrega(end)
                    .build();

            pedido.adicionarItem(new ItemPedido(prod, 1));
            pedidoService.realizarPedido(pedido);

            System.out.println("✅ Pedido realizado! Código: " + pedido.getCodigo());

            pedidoService.atualizarStatus(pedido.getId(), StatusPedido.ENTREGUE);
            System.out.println("🚚 Pedido entregue.");

            Avaliacao av = new Avaliacao(5, "Amei a geladeira!", LocalDateTime.now());
            av.setCliente(cliente);
            av.setProduto(prod);
            avaliacaoService.avaliarProduto(av);
            System.out.println("⭐ Avaliação registrada.");

        } catch (Exception e) {
            System.out.println("❌ Erro no fluxo: " + e.getMessage());
            e.printStackTrace();
        }
    }


    private static void testarListagens() {
        System.out.println("\n-----------------------------------------");
        System.out.println("▶️ EXECUTANDO: testarListagens (Consultas)");
        System.out.println("-----------------------------------------");

        try {
            System.out.println("\n📦 [LISTA DE PRODUTOS]");
            List<ProdutoDTO> produtos = produtoService.listarTodos();
            for (ProdutoDTO p : produtos) {
                System.out.println("   - " + p.nome() + " | R$ " + p.preco() + " | Categ: " + p.nomeCategoria());
            }

            System.out.println("\n🔍 [BUSCA POR TERMO: 'Gela']");
            List<ProdutoDTO> busca = produtoService.buscarPorTermo("Gela");
            if (busca.isEmpty()) {
                System.out.println("   Nenhum produto encontrado.");
            } else {
                for (ProdutoDTO p : busca) {
                    System.out.println("   Achado: " + p.nome() + " (" + p.sku() + ")");
                }
            }

            Long idClienteTeste = 1L;

            System.out.println("\n📄 [HISTÓRICO DE PEDIDOS DO CLIENTE ID " + idClienteTeste + "]");
            List<PedidoDTO> pedidos = pedidoService.listarPedidosDoCliente(idClienteTeste);

            if (pedidos.isEmpty()) {
                System.out.println("   Este cliente não tem pedidos.");
            } else {
                for (PedidoDTO ped : pedidos) {
                    System.out.println("   ------------------------------");
                    System.out.println("   Pedido: " + ped.codigo() + " [" + ped.status() + "]");
                    System.out.println("   Data: " + ped.dataPedido());
                    System.out.println("   Total: R$ " + ped.valorTotal());
                    System.out.println("   Itens:");
                    for (ItemPedidoDTO item : ped.itens()) {
                        System.out.println("     -> " + item.quantidade() + "x " + item.nomeProduto() +
                                " (Unit: R$ " + item.precoUnitario() + ")");
                    }
                }
            }

            if (!produtos.isEmpty()) {
                Long idProd = produtos.get(0).id();
                System.out.println("\n⭐ [AVALIAÇÕES DO PRODUTO: " + produtos.get(0).nome() + "]");

                List<AvaliacaoDTO> reviews = avaliacaoService.listarAvaliacoesDoProduto(idProd);
                for (AvaliacaoDTO r : reviews) {
                    System.out.println("   \"" + r.comentario() + "\" - Nota: " + r.nota() + " (Cliente: " + r.nomeCliente() + ")");
                }
            }

        } catch (Exception e) {
            System.out.println("❌ Erro nas listagens: " + e.getMessage());
        }
    }
}