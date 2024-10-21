package com.inved.service;

import com.inved.model.Pedido;
import com.inved.repository.PedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido salvar(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> buscarPedidosPorClienteData(String cpf,LocalDate dataInicio) {
        LocalDate dataFim = dataInicio.plusDays(30);
        return pedidoRepository.findByClienteAndDataPedidoAfter(cpf, dataInicio,dataFim);
    }
}
