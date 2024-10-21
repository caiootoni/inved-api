package com.inved.controller;

import com.inved.model.Pedido;
import com.inved.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/listarPedido")
    public List<Pedido> listarPedido( @RequestParam String cpf,@RequestParam String dataInicio) {
        DateTimeFormatter  formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataInicioParsed;

         dataInicioParsed = LocalDate.parse(dataInicio, formatter);
        return pedidoService.buscarPedidosPorClienteData(cpf, dataInicioParsed);
    }
    @PostMapping("/salvarPedido")
    public Pedido salvarPedido(@RequestBody Pedido pedido) {
        return pedidoService.salvar(pedido);
    }
}
