package com.example.rent.it.api.controllers;

import com.example.rent.it.dto.itemDto.*;
import com.example.rent.it.dto.favotirosDto.ItemFavoritarDto;
import com.example.rent.it.object.item.Item;
import com.example.rent.it.repository.ItemRepository;
import com.example.rent.it.service.ItemService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/itens")
public class itemController {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemService itemService;




    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não foi encontrado Item com o ID especificado")
    })
    public Integer updateItem(@PathVariable Long id, @RequestBody ItemCriacaoDto item) {
        if (this.itemRepository.existsById(id)) {
            item.setId(id);
            return this.itemService.update(item);
        }
        return null;
    }
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não foi encontrado Item com o ID especificado")
    })
    public void deleteItem(@PathVariable Long id) {
        itemRepository.deleteById(id);
    }

    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna o item com o ID especificado"),
            @ApiResponse(responseCode = "404", description = "Não foi encontrado item com o ID especificado")
    })
    public ResponseEntity<ItemRetornoDto> getItensById(@PathVariable Long id) {
        Optional<Item> itemOptional = itemRepository.findById(id);
        if(itemOptional.isPresent()) {

            return ResponseEntity.ok(ItemMapper.of(itemOptional.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Itens listados com sucesso")
    })
    public List<ItemDto> getAllresumidos() {

        return ItemMapper.of(this.itemService.acharTodos());
    }
    @PostMapping("/cadastrar")
    @ApiResponses(value = {
            @ApiResponse(responseCode ="201", description = "Item cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Não foi possível cadastrar o item")
    })
    public ResponseEntity<Item> cadastrar(@RequestBody ItemCriacaoDto itemCriacaoDto) {
       Item i = this.itemService.criar(itemCriacaoDto);
       if(i != null){

           return ResponseEntity.status(201).body(i);
       }
     return ResponseEntity.status(401).build();
    }
    @GetMapping("/nome/{nome}")
    @ApiResponses(value = {
            @ApiResponse(responseCode ="200", description = "Itens encontrados"),
            @ApiResponse(responseCode = "404", description = "Nenhum item encontrado")
    })
    public ResponseEntity<List<ItemDto>> getItemByNome(@PathVariable String nome){
        Optional<List<Item>> itemOptional = itemRepository.findByNomeContainingIgnoreCase(nome);
        if(itemOptional.isPresent()) {
            return ResponseEntity.ok(ItemMapper.of(itemOptional.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/foto/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    @ApiResponses(value = {
            @ApiResponse(responseCode ="200", description = "Itens encontrados"),
            @ApiResponse(responseCode = "404", description = "Nenhum item encontrado")
    })
    public ResponseEntity<byte[]> getFotoItem(@PathVariable Long id){

        return ResponseEntity.status(200).header("content-disposition",
                " filename=\"item.jpg\"")
                   .body(this.itemService.buscarFoto(id));

    }

    @CrossOrigin("*")
    @PatchMapping(value = "/foto/{id}", consumes = "image/jpg")
   @ApiResponses(value = {
            @ApiResponse(responseCode ="200", description = "Foto do Item Atualizada"),
            @ApiResponse(responseCode = "404", description = "Nenhum item encontrado")
    })
    public ResponseEntity<ResponseEntity> atualizaFotoItem(@PathVariable Long id,
                                                           @RequestBody byte[] foto){
       boolean resposta = this.itemService.atualizaFoto(id, foto);

     if(!resposta){
          return ResponseEntity.notFound().build();
      }

        return ResponseEntity.ok().build();

    }

    @GetMapping("/usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode ="200", description = "Itens encontrados"),
            @ApiResponse(responseCode = "404", description = "Nenhum item encontrado")
    })
    public ResponseEntity<List<ItemDto>> getItensByUsuario(@RequestParam Long id){

        return ResponseEntity.status(200)
                .body(this.itemService.buscarPorUsuario(id));

    }

    @PostMapping("/favoritar")
    @ApiResponses(value = {
            @ApiResponse(responseCode ="201", description = "Item cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Não foi possível cadastrar o item")
    })
    public ResponseEntity<Void> cadastrar(@RequestBody ItemFavoritarDto itemCriacaoDto) {
        this.itemService.favoritar(itemCriacaoDto);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/favoritos")
    @ApiResponses(value = {
            @ApiResponse(responseCode ="201", description = "Item cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Não foi possível cadastrar o item")
    })
    public ResponseEntity<List<ItemDto>> findByFavoritos(@RequestParam Long id) {

        return ResponseEntity.status(201).body(this.itemService.acharFavoritos(id));
    }

    @PostMapping("/pesquisar")
    @ApiResponses(value = {
            @ApiResponse(responseCode ="200", description = "Itens encontrados"),
            @ApiResponse(responseCode = "404", description = "Nenhum item encontrado")
    })
    public ResponseEntity<List<ItemDto>> pesquisaAvancada(@RequestBody ItemPesquisaAvancadaDto
                                                          itemPesquisa){

        return ResponseEntity.ok(itemService.pesquisaAvancada(itemPesquisa));
    }

}
