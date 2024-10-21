package com.inved.repository;

import com.inved.model.Produto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DataJpaTest
class ProdutoRepositoryTest {
    @Autowired
    private ProdutoRepository produtoRepository;
    @Test
    void findByNomeProdutoAndCodigoProduto() {
        Produto produto = new Produto();
        produto.setNomeProduto("ps4");
        produto.setCodigoProduto(5);
        produtoRepository.save(produto);
        Produto produto2 = new Produto();
        produto2.setNomeProduto("ps5");
        produto2.setCodigoProduto(6);
        produtoRepository.save(produto2);
        Optional<Produto> produtos = produtoRepository.findByNomeProdutoAndCodigoProduto("ps4", 5);
        assertFalse(produtos.isEmpty());
        assertEquals(1, produtos.get().getCodigoProduto());


    }
}