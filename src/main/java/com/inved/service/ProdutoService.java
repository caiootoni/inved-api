package com.inved.service;

import com.inved.model.Produto;
import com.inved.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Optional<Produto> listar(String nomeProduto, Integer codigoProduto) {
        return produtoRepository.findByNomeProdutoAndCodigoProduto(nomeProduto, codigoProduto);
    }
}
