package com.example.rent.it.object.avaliacao;

import com.example.rent.it.object.item.Item;
import com.example.rent.it.object.transacao.Transacao;
import jakarta.persistence.*;

@Entity
@Table(name = "avaliacao")
public class Avaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAvaliacao")
    private Integer id;
    @Column(name = "comentario")
    private String comentario;
    @Column(name = "nota")
    private Double nota;
    @ManyToOne
    @JoinColumn(name = "fkTransacao", unique = false)
    private Transacao transacao;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public Transacao getTransacao() {
        return transacao;
    }

    public void setTransacao(Transacao transacao) {
        this.transacao = transacao;
    }
}
