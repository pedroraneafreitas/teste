package com.example.rent.it.api.controllers;

import com.example.rent.it.dto.TransacaoDto.TrasacaoAlugar;
import com.example.rent.it.dto.avaliacao.AvaliacaoNotaDto;
import com.example.rent.it.dto.avaliacao.AvaliacaoNotaRetornoDto;
import com.example.rent.it.dto.avaliacao.TransacaoAvaliacaoDto;
import com.example.rent.it.dto.itemDto.ItemDto;
import com.example.rent.it.object.transacao.Transacao;
import com.example.rent.it.service.AvalicaoService;
import com.example.rent.it.service.TransacaoService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.print.attribute.standard.Media;
import java.io.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @Autowired
    private AvalicaoService avaliacaoService;


  @GetMapping("/csv/{id}")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Arquivo enviado com sucesso"),
          @ApiResponse(responseCode = "404", description = "Usuario não encontrado")
  })
    public ResponseEntity<byte[]> getListaAluguel(@PathVariable Long id){
          try {
              InputStream fileInputStream = new FileInputStream(this.transacaoService.getAlugueis(id));
              return ResponseEntity.status(200)
                      .header("Content-Disposition",
                              "attachment; filename=" + LocalDate.now() + ".csv")
                      .body(fileInputStream.readAllBytes());

             } catch(Exception e){
              e.printStackTrace();
          }


     return null;
  }
    @GetMapping("/csv/ordenado/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Arquivo enviado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuario não encontrado")
             })
    public ResponseEntity<byte[]> getListaAluguelOrdenado(@PathVariable Long id){
        try {
            InputStream fileInputStream = new FileInputStream(this.transacaoService.getAlugueisOrdenado(id));
            return ResponseEntity.status(200)
                    .header("Content-Disposition",
                            "attachment; filename=" + LocalDate.now() + ".csv")
                    .body(fileInputStream.readAllBytes());

        } catch(Exception e){
            e.printStackTrace();
        }


        return null;
    }
  @GetMapping
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "Arquivo enviado com sucesso"),
          @ApiResponse(responseCode = "500", description = "Serviço não disponivel")
  })
    public List<Transacao> getTransacoes(){
      return this.transacaoService.getTransacoes();
  }
    @GetMapping("/precoalugado/{id}/{preco}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Arquivo enviado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuario não encontrado")
    })
    public Optional<Transacao> getTransacaoPorPreco(@PathVariable int preco,
                                                    @PathVariable Long id){

        return this.transacaoService.getTransacaoPorPreco(preco, id);
    }




   //exportação do primeiro txt com itens alugados(pilha)

    @GetMapping("/alugados/txt/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Arquivo enviado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuario não encontrado")
    })

    public ResponseEntity<byte[]>getAlugados(@RequestParam Long id){

        try {
            InputStream fileInputStream = new FileInputStream(this.transacaoService.getAlugados(id));
            return ResponseEntity.status(200)
                    .header("Content-Disposition",
                            "attachment; filename=" + LocalDate.now() + ".txt")
                    .body(fileInputStream.readAllBytes());

        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //importação de itens para alugar(fila)


    @PostMapping(value = "/alugar/{id}",   consumes = "image/jpg" )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Itens Criados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuario não encontrado")
    })

    public ResponseEntity<List<ItemDto>> alugaItensEmMassa(@PathVariable Long id
                                                              , @RequestBody byte[] arquivo){
      List<ItemDto> retorno = this.transacaoService.alugaItensEmMassa(id, arquivo);
        if(retorno != null) {
            return ResponseEntity.ok(retorno);
        }
        return ResponseEntity.status(404).build();


    }

    @PostMapping(value = "/alugar-item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Itens Criados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuario não encontrado")
    })

    public ResponseEntity<Transacao> alugarItem(@RequestBody TrasacaoAlugar aluguel){
      Transacao t = new Transacao();
      t = this.transacaoService.alugarItem(aluguel);
      if(t != null) {
          return ResponseEntity.ok(t);
      }
      return ResponseEntity.status(401).build();
    }

    @PostMapping(value = "/avaliar")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Avaliação feita com sucesso"),
            @ApiResponse(responseCode = "404", description = "Houve um erro na avaliação")
    })
    public ResponseEntity<Transacao> avliar(@RequestBody TransacaoAvaliacaoDto avaliacao){
      Transacao t = this.avaliacaoService.avaliar(avaliacao);
      if(t != null) {
          return ResponseEntity.ok(t);
      }
      return  ResponseEntity.status(404).build();
    }

    @GetMapping(value = "/get-avaliar")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Avaliação achada"),
            @ApiResponse(responseCode = "404", description = "Houve um erro ao achar avaliação")
    })
    public ResponseEntity<AvaliacaoNotaRetornoDto> getAvaliacao(Long id){
      return ResponseEntity.ok(this.avaliacaoService.getAvaliacao(id));
    }



}
