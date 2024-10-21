package com.inved.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inved.model.Cliente;
import com.inved.model.Pedido;
import com.inved.service.PedidoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PedidoController.class)
class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PedidoService pedidoService;

    @Test
    void deveListarPedidos() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setCpf("12345678901");
        LocalDate dataInicio = LocalDate.now();
        List<Pedido> pedidos = Collections.singletonList(new Pedido(null, "Compra", 100.0, dataInicio, null, null, cliente));
        when(pedidoService.buscarPedidosPorClienteData("12345678901", dataInicio)).thenReturn(pedidos);

        mockMvc.perform(get("/pedido/listarPedido")
                        .param("cpf", "12345678901")
                        .param("dataInicio", dataInicio.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].descricao").value("Compra"));
    }

    @Test
    void salvarPedido() throws Exception {
        Pedido pedido = new Pedido(1L, "Compra", 100.0, LocalDate.now(), null, null, new Cliente(1L, "Cliente Teste", "email@test.com", "senha123", "12345678901"));
        when(pedidoService.salvar(any(Pedido.class))).thenReturn(pedido);

        ObjectMapper objectMapper = new ObjectMapper();
        String pedidoJson = objectMapper.writeValueAsString(pedido);

        mockMvc.perform(post("/pedido/salvarPedido")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(pedidoJson))
                .andExpect(status().isOk())
                .andExpect(content().json(pedidoJson));
    }
}

