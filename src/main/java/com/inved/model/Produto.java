package com.inved.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "produto não pode estar vazio")
    private String nomeProduto;
    @NotNull(message = "código do produto não pode estar vazio")
    private Integer codigoProduto;


    public Produto() {
    }

    public Produto(Long id, String nomeProduto, Integer codigoProduto) {
        this.id = id;
        this.nomeProduto = nomeProduto;
        this.codigoProduto = codigoProduto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Integer getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(Integer codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

}
