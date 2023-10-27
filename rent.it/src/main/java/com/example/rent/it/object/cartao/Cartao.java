package com.example.rent.it.object.cartao;

import com.example.rent.it.object.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "cartao")
public class Cartao {



        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "idCartao")
        private Long id;

    @Column(name = "numCartao")
    private String numCartao;

    @Column(name = "validade")
    private String validade;

    @Column(name = "cpfTitular")
    private String cpfTitular;

    @Column(name = "nomeImpresso")
    private String nomeImpresso;

    @ManyToOne
    @JoinColumn(name = "fkUsuario")
    private Usuario usuario;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumCartao() {
        return numCartao;
    }

    public void setNumCartao(String numCartao) {
        this.numCartao = numCartao;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public String getCpfTitular() {
        return cpfTitular;
    }

    public void setCpfTitular(String cpfTitular) {
        this.cpfTitular = cpfTitular;
    }

    public String getNomeImpresso() {
        return nomeImpresso;
    }

    public void setNomeImpresso(String nomeImpresso) {
        this.nomeImpresso = nomeImpresso;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
