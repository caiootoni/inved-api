package com.inved.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "descrição não pode estar vazia")
    private String descricao;
    @NotNull(message = "valor não pode estar vazia")
    @DecimalMin(value = "0.0", inclusive = false, message = "Valor deve ser positivo")
    private Double valor;
    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "data do pedido não pode estar vazia")
    private LocalDate dataPedido;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataInicio;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFim;
    @ManyToOne
    private Cliente cliente;

    public Pedido() {
    }

    public Pedido(Long id, String descricao, Double valor, LocalDate dataPedido,LocalDate dataInicio,LocalDate dataFim, Cliente cliente) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.dataPedido = dataPedido;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.cliente = cliente;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
