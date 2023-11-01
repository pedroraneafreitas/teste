package com.example.rent.it.api.controllers;

import com.example.rent.it.dto.cartaoDto.CartaoCriacaoDto;
import com.example.rent.it.dto.cartaoDto.CartaoDto;
import com.example.rent.it.service.CartaoService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cartaos")
public class cartaoController {


    private final CartaoService cartaoService;

    public cartaoController(CartaoService cartaoService) {
        this.cartaoService = cartaoService;
    }



    @PostMapping("/cadastrar")
    @ApiResponses(value = {
            @ApiResponse(responseCode ="201", description = "Cartão cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Não foi possível cadastrar o Cartão"),
               })

    public ResponseEntity<CartaoDto> criar(@RequestBody CartaoCriacaoDto cartao) {
        this.cartaoService.criar(cartao);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/usuario/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode ="201", description = "Cartão cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Não foi possível cadastrar o Cartão"),
    })

    public ResponseEntity<List<CartaoDto>> acharPorIdUsuario(@RequestParam Long id) {

        return ResponseEntity.status(201).body( this.cartaoService.acharPorUsuario(id));
    }

    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode ="201", description = "Cartão cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Não foi possível cadastrar o Cartão"),
    })

    public ResponseEntity<CartaoDto> acharPorId(@RequestParam Long id) {

        return ResponseEntity.status(200).body(this.cartaoService.acharPorId(id));
    }

    @PatchMapping("/atuzalizar")
    @ApiResponses(value = {
            @ApiResponse(responseCode ="201", description = "Cartão cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Não foi possível cadastrar o Cartão"),
    })

    public ResponseEntity<CartaoDto> atualizar(@RequestBody CartaoCriacaoDto cartao) {
        this.cartaoService.criar(cartao);
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/deletar/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode ="201", description = "Cartão cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Não foi possível cadastrar o Cartão"),
    })
    public ResponseEntity<CartaoDto> deleterPorId(@RequestParam Long id) {

        if(this.cartaoService.exiteById(id)){
            this.cartaoService.deletarPorId(id);
        return ResponseEntity.status(200).build();
     }
        return  ResponseEntity.notFound() .build();
    }




}
