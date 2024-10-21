package com.inved.controller;

import com.inved.model.Produto;
import com.inved.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;
    @GetMapping("/listarProduto")
    public Optional<Produto> listarProduto(String nomeProduto, Integer codigoProduto) {
        return produtoService.listar(nomeProduto, codigoProduto);
    }
    @PostMapping("/salvarProduto")
    public Produto salvarProduto(Produto produto) {
        return produtoService.salvar(produto);
    }
}
