package com.example.rent.it.dto.itemDto;
import com.example.rent.it.armazenamento.FilaObj;
import com.example.rent.it.armazenamento.PilhaObj;
import com.example.rent.it.object.categoria.Categoria;
import com.example.rent.it.object.item.Item;
import com.example.rent.it.object.usuario.Usuario;

import java.util.ArrayList;
import java.util.List;

public class ItemMapper {


    public static Item of(ItemDto itemDto) {
        Item item = new Item();
        item.setNome(itemDto.getNome());
        item.setDescricao(itemDto.getDescricao());
        item.setValorDia(itemDto.getValorDia());

        return item;
    }
    public static Item of(ItemCriacaoDto itemDto, Categoria categoria,
                          Usuario usuario) {
        Item item = new Item();
        item.setId(itemDto.getId());
        item.setNome(itemDto.getNome());
        item.setDescricao(itemDto.getDescricao());
        item.setDisponivel(itemDto.getDisponivel());
        item.setValorDia(itemDto.getValorDia());
        item.setCategoria(categoria);
        item.setUsuario(usuario);

        return item;
    }

    public static ItemRetornoDto of (Item item){
        ItemRetornoDto retorno = new ItemRetornoDto();

        retorno.setId(item.getId());
        retorno.setNomeItem(item.getNome());
        retorno.setDescricao(item.getDescricao());
        retorno.setValorDia(item.getValorDia());
        retorno.setDisponivel(item.getDisponivel());
        retorno.setCategoria(item.getCategoria().getId());
        retorno.setIdUsuario(item.getUsuario().getId());
        retorno.setNomeUsuario(item.getUsuario().getNome());
        retorno.setApelidoUsario(item.getUsuario().getApelido());
        retorno.setEmail(item.getUsuario().getEmail());
        retorno.setTelefone(item.getUsuario().getTelefone());
        return retorno;

    }

    public static ItemToken of(Item item, String token) {
        ItemToken itemToken = new ItemToken();

        itemToken.setId(item.getId());
        itemToken.setCategoria((item.getCategoria()));
        itemToken.setDescricao(item.getDescricao());
        itemToken.setNome(item.getNome());
        itemToken.setValorDia(item.getValorDia());
        itemToken.setToken(token);
        return itemToken;
    }

    public static List<ItemDto> of(List<Item> itens){
        List<ItemDto> itensDto = new ArrayList<>();
        ItemDto itemDto;

        for (int i = 0; i < itens.size();i++ ) {
           itemDto  = new ItemDto();
           itemDto.setId(itens.get(i).getId());
           itemDto.setNome(itens.get(i).getNome());
           itemDto.setCategoria(itens.get(i).getCategoria().getId());
           itemDto.setDescricao(itens.get(i).getDescricao());
           itemDto.setValorDia(itens.get(i).getValorDia());
           itensDto.add(itemDto);

        }
       return itensDto;
    }

//    public static List<ItemDto> of(FilaObj<Item> itens){
//        List<ItemDto> itensDto = new ArrayList<>();
//        Item item;
//
//        for (int i = 0; i < itens.getTamanho();i++ ) {
//            item = itens.poll();
//           ItemDto itemDto  = new ItemDto();
//
//            itemDto.setId(item.getId());
//            itemDto.setNome(item.getNome());
//            itemDto.setCategoria(item.getCategoria().getNomeCategoria());
//            itemDto.setDescricao(item.getDescricao());
//            itemDto.setValorDia(item.getValorDia());
//            itensDto.add(itemDto);
//
//        }
//        return itensDto;
//    }
    public static List<Item> of(FilaObj<Item> itens){
        List<Item> retorno = new ArrayList<>();
        if(itens !=  null) {
            while (!itens.isEmpty()) {
                retorno.add(itens.poll());
            }
        }
        return retorno;
    }

    }
