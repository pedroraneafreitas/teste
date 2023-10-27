package com.example.rent.it.dto.TransacaoDto;

import com.example.rent.it.armazenamento.PilhaObj;
import com.example.rent.it.object.item.Item;
import com.example.rent.it.object.transacao.Transacao;
import com.example.rent.it.object.usuario.Usuario;

import java.util.List;

public class TransacaoMapper {

    public static PilhaObj<TransacaoRetornoDto> of(List<Transacao> transacoes){
        PilhaObj<TransacaoRetornoDto> retorno = new PilhaObj<>(transacoes.size());

        for (Transacao t:
             transacoes) {
           TransacaoRetornoDto dto = new TransacaoRetornoDto();

           dto.setNomeItem(t.getFkItem().getNome());
           dto.setDtInicio(t.getDtInicio().toString());
           dto.setDtFim(t.getDtFim().toString());
           dto.setValorFinal(t.getValorFinal());
           dto.setDescricao(t.getFkItem().getDescricao());
           if(t.getFkItem().getDisponivel() == 1){
           dto.setDisponivel("SIM");
           }else {
               dto.setDisponivel("NÃO");
           }
           dto.setValorItem(t.getFkItem().getValorDia());
           dto.setNomeUsu(t.getFkUsuario().getNome());
           retorno.push(dto);

        }
        return retorno;

    }

    public static Transacao of(Item i, Usuario u, TrasacaoAlugar t){
      Transacao retorno = new Transacao();
      retorno.setFkItem(i);
      retorno.setFkUsuario(u);
      retorno.setValorFinal(t.getValorFinal());
      retorno.setRecebido(1);
      retorno.setDtFim(t.getDtFim());
      retorno.setDtInicio(t.getDtInicio());

      return retorno;
    }

}
