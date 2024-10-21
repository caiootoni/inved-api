package com.inved.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inved.model.Produto;
import com.inved.service.ProdutoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProdutoController.class)
class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProdutoService produtoService;

    @Test
    void deveListarProduto() throws Exception {
        Produto produto = new Produto(1L, "ps4", 5);
        when(produtoService.listar("ps4", 5)).thenReturn(Optional.of(produto));

        mockMvc.perform(get("/produto/listarProduto")
                        .param("nomeProduto", "ps4")
                        .param("codigoProduto", "5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nomeProduto").value("ps4"));
    }

    @Test
    void salvarProduto() throws Exception {
        Produto produto = new Produto(1L, "ps5", 6);
        when(produtoService.salvar(any(Produto.class))).thenReturn(produto);

        ObjectMapper objectMapper = new ObjectMapper();
        String produtoJson = objectMapper.writeValueAsString(produto);

        mockMvc.perform(post("/produto/salvarProduto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(produtoJson))
                .andExpect(status().isOk())
                .andExpect(content().json(produtoJson));
    }
}
