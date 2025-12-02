package com.marketplace;

import com.marketplace.model.Produto;
import com.marketplace.model.Categoria;
import com.marketplace.service.CategoriaService;
import com.marketplace.service.ProdutoService;

import java.util.List;

public class Main {
    public static void main(String[] args) {


        CategoriaService categoriaService = new CategoriaService();
        ProdutoService produtoService = new ProdutoService();

        Categoria categoria = new Categoria("Cosméticos", "Itens para beleza");
        Produto produto1 = new Produto(
                "NTC-GMR-01",
                "Notebook Gamer",
                "Notebook de alta performance para jogos.",
                5999.99,
                50,
                2.5,
                "http://example.com/notebook.jpg"
        );
       List<Categoria> lista = categoriaService.buscarCategoriasComProdutos();

       for(Categoria c : lista){
           System.out.println(c.getNome() + c.getDescricao());
       }

    }


}