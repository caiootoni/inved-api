package com.inved.service;

import com.inved.model.Cliente;
import com.inved.model.Pedido;
import com.inved.repository.PedidoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Esta anotação configura a integração com Mockito.
class PedidoServiceTest {

    @Mock
    private PedidoRepository pedidoRepository; // Mock do repositório para não usar o banco real.

    @InjectMocks
    private PedidoService pedidoService; // Injeta o mock do repositório no serviço.

    @Test
    void deveBuscarPedidosPorClienteData() {
        // Arrange: Configura o mock e os dados
        Cliente cliente = new Cliente();
        cliente.setCpf("12345678901");
        LocalDate dataInicio = LocalDate.now();
        List<Pedido> pedidos = Collections.singletonList(new Pedido(null, "Compra", 100.0, dataInicio, null, null, cliente));
        when(pedidoRepository.findByClienteAndDataPedidoAfter("12345678901", dataInicio, dataInicio.plusDays(30))).thenReturn(pedidos);

        // Act: Executa a ação
        List<Pedido> result = pedidoService.buscarPedidosPorClienteData("12345678901", dataInicio);

        // Assert: Verifica o resultado
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(pedidoRepository, times(1)).findByClienteAndDataPedidoAfter("12345678901", dataInicio, dataInicio.plusDays(30)); // Verifica se o método foi chamado corretamente
    }
}
