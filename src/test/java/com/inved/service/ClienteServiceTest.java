package com.inved.service;

import com.inved.model.Cliente;
import com.inved.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ClienteServiceTest {
    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void salvar() {
        Cliente cliente = new Cliente(1L, "Dante", "teste@teste.com", "12345678", "11122233344");
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente clienteSalvo = clienteService.salvar(cliente);

        assertEquals(cliente.getNome(), clienteSalvo.getNome());
        verify(clienteRepository, times(1)).save(cliente);
    }

    @Test
    void listar() {
        List<Cliente> clienteLista = List.of(new Cliente(1L, "Dante", "teste@teste.com", "12345678", "11122233344"));
        when(clienteRepository.findAll()).thenReturn(clienteLista);

        List<Cliente> clientes = clienteService.listar();

        assertEquals(clienteLista, clientes);
        verify(clienteRepository, times(1)).findAll();
    }
}
