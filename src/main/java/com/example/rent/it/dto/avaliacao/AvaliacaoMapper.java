package com.example.rent.it.dto.avaliacao;

import com.example.rent.it.object.avaliacao.Avaliacao;
import com.example.rent.it.object.transacao.Transacao;

public class AvaliacaoMapper {
    public static Avaliacao of(Transacao t, Double nota){
      Avaliacao avaliacao = new Avaliacao();
      avaliacao.setNota(nota);
      avaliacao.setTransacao(t);
      return avaliacao;

    }
    public static AvaliacaoNotaRetornoDto of(Double nota){
        AvaliacaoNotaRetornoDto dto = new AvaliacaoNotaRetornoDto();
        dto.setNota(nota);
        return dto;
    }
}