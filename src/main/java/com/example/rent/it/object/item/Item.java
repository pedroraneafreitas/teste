package com.example.rent.it.object.item;

import com.example.rent.it.object.categoria.Categoria;
import com.example.rent.it.object.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.sql.Blob;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idItem")
    private Long id;
    @Column(name = "nomeItem")
    private String nome;
    @JsonIgnore
    @Column(name = "foto", length = 50 * 1024 * 1024)
    private byte[] foto;
    @Column(name = "descItem")
    private String descricao;
    @Column(name = "valor")
    private double valorDia;

    @Column(name = "disponivel")
    private int  disponivel;
    @Column(name = "dtCadastro")
    private Date dtCadastro;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "fkCategoria")
    private Categoria categoria;
    @ManyToOne
    @JoinColumn(name = "fkUsuario")
    private Usuario usuario;

    public Item() {
    }

    public Item( String nome, String descricao, double valorDia, int disponivel,Categoria categoria, Usuario usuario) {
        this.nome = nome;
        this.descricao = descricao;
        this.valorDia = valorDia;
        this.disponivel = disponivel;
        this.categoria = categoria;
        this.usuario = usuario;

    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public int getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(int disponivel) {
        this.disponivel = disponivel;
    }

    public Date getDtCadastro() {
        return dtCadastro;
    }

    public void setDtCadastro(Date dtCadastro) {
        this.dtCadastro = dtCadastro;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValorDia() {
        return valorDia;
    }

    public void setValorDia(double valorDia) {
        this.valorDia = valorDia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


}
