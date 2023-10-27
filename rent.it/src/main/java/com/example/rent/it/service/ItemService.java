package com.example.rent.it.service;

import com.example.rent.it.dto.favotirosDto.FavoritosMapper;
import com.example.rent.it.dto.favotirosDto.ItemFavoritarDto;
import com.example.rent.it.dto.itemDto.ItemCriacaoDto;
import com.example.rent.it.dto.itemDto.ItemDto;
import com.example.rent.it.dto.itemDto.ItemMapper;
import com.example.rent.it.dto.itemDto.ItemPesquisaAvancadaDto;
import com.example.rent.it.object.categoria.Categoria;
import com.example.rent.it.object.favoritos.Favoritos;
import com.example.rent.it.object.item.Item;
import com.example.rent.it.repository.CategoriaRepository;
import com.example.rent.it.repository.FavoritoRepository;
import com.example.rent.it.repository.ItemRepository;
import com.example.rent.it.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {


    private ItemRepository itemRepository;
    private CategoriaRepository categoriaRepository;
    private UsuarioRepository usuarioRepository;
    private FavoritoRepository favoritoRepository;

    public ItemService(ItemRepository itemRepository, CategoriaRepository categoriaRepository,
                       UsuarioRepository usuarioRepository, FavoritoRepository favoritos) {
        this.itemRepository = itemRepository;
        this.categoriaRepository = categoriaRepository;
        this.usuarioRepository = usuarioRepository;
        this.favoritoRepository = favoritos;
    }

    public Item criar(ItemCriacaoDto itemDto) {

        if(this.categoriaRepository.existsById(itemDto.getCategoria()) &&
                this.usuarioRepository.existsById(itemDto.getUsuario()
                )){final Item novoItem = ItemMapper.of(itemDto,
                this.categoriaRepository.findById(itemDto.getCategoria()).get(),
                this.usuarioRepository.findById(itemDto.getUsuario()).get());

                    return this.itemRepository.save(novoItem);
        }
        return null;

    }
    // Achar todos os itens
    public List<Item> acharTodos(){
        return this.itemRepository.findAll();
    }

    public byte[] buscarFoto(Long id) {

        if(!this.itemRepository.existsFotoById(id)){
            return this.itemRepository.findFotoById(id - id);
        }

        return this.itemRepository.findFotoById(id);
    }

    public boolean atualizaFoto(Long id, byte[] foto) {

        if(this.itemRepository.existsById(id)){

            this.itemRepository.atualizaFoto(id,foto);
            return true;
        }

        return false;
    }

    public List<ItemDto> buscarPorUsuario(Long id) {

        return ItemMapper.of(this.itemRepository.findByUsuarioId(id));
    }

    public Favoritos favoritar(ItemFavoritarDto itemFavoritarDto) {
        return this.favoritoRepository.save(FavoritosMapper.of(
                this.usuarioRepository.findById(itemFavoritarDto.getUsuario()).get(),
                this.itemRepository.findById(itemFavoritarDto.getItem()).get()));
    }

    public List<ItemDto> acharFavoritos(Long id) {

        return FavoritosMapper.of(this.favoritoRepository.findByUsuarioId(id));
    }

    public Integer update(ItemCriacaoDto item) {
          Item itemUpdate = ItemMapper.of(item,
                  this.categoriaRepository.findById(item.getCategoria()).get(),
                  this.usuarioRepository.findById(item.getUsuario()).get());

        return this.itemRepository.updateItem(itemUpdate.getNome(), itemUpdate.getDescricao()
        ,itemUpdate.getValorDia(), itemUpdate.getCategoria(),itemUpdate.getId());
    }

    public List<ItemDto> pesquisaAvancada(ItemPesquisaAvancadaDto itemPesquisa) {

        List<Categoria> categorias =
                this.categoriaRepository.findAllById(itemPesquisa.getCategorias());


        return ItemMapper.of(
                this.itemRepository.findAllByNomeContainingIgnoreCaseAndCategoriaInAndValorDiaLessThan(
                        itemPesquisa.getNome(),categorias, itemPesquisa.getPreco()));


    }
}
