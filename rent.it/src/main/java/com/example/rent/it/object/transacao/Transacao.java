package com.example.rent.it.object.transacao;

import com.example.rent.it.object.item.Item;
import com.example.rent.it.object.usuario.Usuario;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "transacao")
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTransacao")
    private Long idTransacao;
    @ManyToOne
    @JoinColumn(name = "fkItem", unique = false)
    private Item fkItem;
    @ManyToOne
    @JoinColumn(name = "fkUsuario", unique = false)
    private Usuario fkUsuario;
    @Column(name = "dtInicio")
    private Date dtInicio;
    @Column(name = "dtFim")
    private Date dtFim;
    @Column(name = "recebido")
     private int recebido;
    @Column(name = "valorFinal")
    private Double valorFinal;

    public Double getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(Double valorFinal) {
        this.valorFinal = valorFinal;
    }

    public Long getIdTransacao() {
        return idTransacao;
    }

    public void setIdTransacao(Long idTransacao) {
        this.idTransacao = idTransacao;
    }

    public Item getFkItem() {
        return fkItem;
    }

    public void setFkItem(Item fkItem) {
        this.fkItem = fkItem;
    }

    public Usuario getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(Usuario fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

    public Date getDtInicio() {
        return dtInicio;
    }

    public void setDtInicio(Date dtInicio) {
        this.dtInicio = dtInicio;
    }

    public Date getDtFim() {
        return dtFim;
    }

    public void setDtFim(Date dtFim) {
        this.dtFim = dtFim;
    }
    public void setDtFim(String correcao) {
        this.dtFim = dtFim;
    }
    public int getRecebido() {
        return recebido;
    }

    public void setRecebido(int recebido) {
        this.recebido = recebido;
    }
}
