package com.inved.controller;

import com.inved.model.Cliente;
import com.inved.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @PostMapping("/")
    public Cliente criar(@RequestBody Cliente cliente) {
        return clienteService.salvar(cliente);
    }
    @GetMapping("/")
    public List<Cliente> listar() {
        return clienteService.listar();
    }
}
