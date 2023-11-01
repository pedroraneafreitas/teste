package com.example.rent.it.service;

import com.example.rent.it.dto.avaliacao.AvaliacaoMapper;
import com.example.rent.it.dto.avaliacao.AvaliacaoNotaRetornoDto;
import com.example.rent.it.dto.avaliacao.TransacaoAvaliacaoDto;
import com.example.rent.it.object.avaliacao.Avaliacao;
import com.example.rent.it.object.transacao.Transacao;
import com.example.rent.it.repository.AvaliacaoRepository;
import com.example.rent.it.repository.ItemRepository;
import com.example.rent.it.repository.TransacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvalicaoService {
    private final AvaliacaoRepository avaliacaoRepository;
    private final TransacaoRepository transacaoRepository;
    private final ItemRepository itemRepository;

    public AvalicaoService(AvaliacaoRepository avaliacaoRepository, TransacaoRepository transacaoRepository,
                           ItemRepository i) {
        this.avaliacaoRepository = avaliacaoRepository;
        this.transacaoRepository = transacaoRepository;
        this.itemRepository = i;
    }


    public Transacao avaliar(TransacaoAvaliacaoDto avaliacao) {
        Optional<Transacao> transacao = this.transacaoRepository.findById(avaliacao.getTransacao());
        if(transacao.isPresent()){
            this.avaliacaoRepository.save(AvaliacaoMapper.of(transacao.get(), avaliacao.getNota()));
            return transacao.get();
        }
        return null;
    }

    public AvaliacaoNotaRetornoDto getAvaliacao(Long id) {
        List<Avaliacao> avaliacoes = this.avaliacaoRepository.findAllByTransacaoFkItem(
                this.itemRepository.findById(id).get()
        );

        Double notaBruta = 0.0;

        for(Avaliacao a:
            avaliacoes){
            notaBruta += a.getNota();
        }
        Double nota = notaBruta / avaliacoes.size();

        


        return AvaliacaoMapper.of(nota);
    }
}
;