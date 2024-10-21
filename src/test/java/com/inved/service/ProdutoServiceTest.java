package com.inved.service;

import com.inved.model.Produto;
import com.inved.repository.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProdutoServiceTest {
    @Mock
    private ProdutoRepository produtoRepository;
    @InjectMocks
    private ProdutoService produtoService;




    public ProdutoServiceTest() {
        MockitoAnnotations.openMocks(this); // Inicializa os mocks
    }

    @Test
    void salvar() {
        Produto produto = new Produto(2L, "ps4", 1);
        when(produtoRepository.save(produto)).thenReturn(produto);

        Produto produtoSalvo = produtoService.salvar(produto);

        assertNotNull(produtoSalvo); // Verifica se o objeto salvo não é nulo
        assertEquals(produto.getNomeProduto(), produtoSalvo.getNomeProduto()); // Verifica se o nome do produto salvo é igual ao esperado
        verify(produtoRepository, times(1)).save(produto); // Verifica se o método save foi chamado uma vez
    }

    @Test
    void listar() {
        Produto produto = new Produto(2L, "ps4", 1);
        when(produtoRepository.findByNomeProdutoAndCodigoProduto("ps4", 1)).thenReturn(Optional.of(produto));

        Optional<Produto> produtoEncontrado = produtoService.listar("ps4", 1);

        assertTrue(produtoEncontrado.isPresent()); // Verifica se o produto foi encontrado
        assertEquals(produto.getNomeProduto(), produtoEncontrado.get().getNomeProduto()); // Verifica o nome do produto
        assertEquals(produto.getCodigoProduto(), produtoEncontrado.get().getCodigoProduto()); // Verifica o código do produto
        verify(produtoRepository, times(1)).findByNomeProdutoAndCodigoProduto("ps4", 1); // Verifica se o método foi chamado corretamente
    }
}