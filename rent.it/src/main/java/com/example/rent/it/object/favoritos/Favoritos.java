package com.example.rent.it.object.favoritos;

import com.example.rent.it.object.item.Item;
import com.example.rent.it.object.usuario.Usuario;
import jakarta.persistence.*;

@Entity
@Table(name = "favoritos")
public class Favoritos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fkUsuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "fkItem")
    private Item fkItem;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Item getFkItem() {
        return fkItem;
    }

    public void setFkItem(Item fkItem) {
        this.fkItem = fkItem;
    }
}
