package com.inved.repository;

import com.inved.model.Cliente;
import com.inved.model.Pedido;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DataJpaTest
class PedidoRepositoryTest {
    @Autowired
    private PedidoRepository pedidoRepository;
    @Test
    void findByClienteAndDataPedidoAfter() {
        Cliente cliente = new Cliente(); // Suponha que Cliente seja uma entidade previamente configurada
        cliente.setCpf("12345678901");
        LocalDate dataInicio = LocalDate.now();
        LocalDate dataFim = dataInicio.plusDays(30);
        Pedido pedido = new Pedido(null, "Compra", 100.0, dataInicio, null, null, cliente);
        pedidoRepository.save(pedido);

        // Act: Executa a ação
        List<Pedido> pedidos = pedidoRepository.findByClienteAndDataPedidoAfter("12345678901", dataInicio, dataFim);

        // Assert: Verifica o resultado
        assertFalse(pedidos.isEmpty());
        assertEquals(1, pedidos.size());
    }
}