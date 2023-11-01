package com.example.rent.it.dto.favotirosDto;

import com.example.rent.it.dto.itemDto.ItemDto;
import com.example.rent.it.object.favoritos.Favoritos;
import com.example.rent.it.object.item.Item;
import com.example.rent.it.object.usuario.Usuario;

import java.util.ArrayList;
import java.util.List;

public class FavoritosMapper {

    public static Favoritos of(Usuario usuario, Item item){
        final Favoritos favorito = new Favoritos();

        favorito.setFkItem(item);
        favorito.setUsuario(usuario);

        return favorito;
    }

    public static List<ItemDto> of(List<Favoritos> favoritos){
        List<ItemDto> itensDto = new ArrayList<>();
        ItemDto itemDto;

        for (int i = 0; i < favoritos.size();i++ ) {
            itemDto  = new ItemDto();
            itemDto.setId(favoritos.get(i).getFkItem().getId());
            itemDto.setNome(favoritos.get(i).getFkItem().getNome());
            itemDto.setCategoria(favoritos.get(i).getFkItem().getCategoria().getId());
            itemDto.setValorDia(favoritos.get(i).getFkItem().getValorDia());
            itensDto.add(itemDto);

        }
        return itensDto;
    }

}
