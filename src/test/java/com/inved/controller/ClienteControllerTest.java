package com.inved.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inved.model.Cliente;
import com.inved.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ClienteController.class)
class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    @Test
    void criar() throws Exception {
        Cliente cliente = new Cliente(1L, "Nome Teste", "email@test.com", "senha123", "12345678901");
        when(clienteService.salvar(any(Cliente.class))).thenReturn(cliente);

        ObjectMapper objectMapper = new ObjectMapper();
        String clienteJson = objectMapper.writeValueAsString(cliente);

        mockMvc.perform(post("/cliente/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(clienteJson))
                .andExpect(status().isOk())
                .andExpect(content().json(clienteJson));

        verify(clienteService, times(1)).salvar(any(Cliente.class));
    }

    @Test
    void listar() throws Exception {
        List<Cliente> clientes = Arrays.asList(new Cliente(1L, "Nome Teste", "email@test.com", "senha123", "12345678901"));
        when(clienteService.listar()).thenReturn(clientes);

        mockMvc.perform(get("/cliente/"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id':1,'nome':'Nome Teste','email':'email@test.com','senha':'senha123','cpf':'12345678901'}]"));

        verify(clienteService, times(1)).listar();
    }
}

