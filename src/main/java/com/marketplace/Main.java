package com.marketplace;

import com.marketplace.model.Produto;
import com.marketplace.model.Categoria;
import com.marketplace.service.CategoriaService;
import com.marketplace.service.ProdutoService;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        //SERVICES
        CategoriaService categoriaService = new CategoriaService();
        ProdutoService produtoService = new ProdutoService();
        //MOCKS
        Categoria categoria = categoriaService.buscarCategoriaPorId(1L);
        Produto produto = new Produto.Builder()
                .sku("NOTE-01")
                .nome("Acer Aspire 5")
                .descricao("Processador Intel Core i7 13ª Geração, 16GB de RAM, 15.6' IPS")
                .categoria(categoria)
                .build();


        //TESTES
        categoriaService.cadastrarCategoria(categoria);
        produtoService.cadastrarProduto(produto);

    }


}