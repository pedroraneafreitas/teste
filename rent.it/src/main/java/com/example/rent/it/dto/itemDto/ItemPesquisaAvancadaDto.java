package com.example.rent.it.dto.itemDto;

import java.util.List;

public class ItemPesquisaAvancadaDto {
    private  String nome;
    private List<Integer> categoria;
    private Double preco;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Integer> getCategorias() {
        return categoria;
    }

    public void setCategorias(List<Integer> categorias) {
        this.categoria = categorias;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
}
