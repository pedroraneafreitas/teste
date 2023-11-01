package com.example.rent.it.dto.cartaoDto;

import com.example.rent.it.object.cartao.Cartao;

import java.util.ArrayList;
import java.util.List;

public class CartaoMapper {

    public static CartaoDto of(Cartao cartao){
         final CartaoDto cartaoDto = new CartaoDto();
         cartaoDto.setId(cartao.getId());
         cartaoDto.setNumCartao(cartao.getNumCartao());
         cartaoDto.setValidade(cartao.getValidade());
         cartaoDto.setNomeUsuario(cartao.getUsuario().getNome());
         cartaoDto.setCpf(cartao.getCpfTitular());
         return cartaoDto;
    }

    public static List<CartaoDto> of(List<Cartao> cartao){
        List<CartaoDto> cartaoDtos = new ArrayList<>();
        for(int i = 0; i < cartao.size(); i++) {
            final CartaoDto cartaoDto = new CartaoDto();
            cartaoDto.setId(cartao.get(i).getId());
            cartaoDto.setNumCartao(cartao.get(i).getNumCartao());
            cartaoDto.setValidade(cartao.get(i).getValidade());
            cartaoDto.setNomeUsuario(cartao.get(i).getNomeImpresso());
            cartaoDto.setCpf(cartao.get(i).getCpfTitular());
            cartaoDtos.add(cartaoDto);

        }
        return cartaoDtos;
    }


    public static Cartao of(CartaoCriacaoDto dto){

        final Cartao cartao = new Cartao();
        cartao.setId(dto.getId());
        cartao.setCpfTitular(dto.getCpfTitular().replace(".", "")
                .replace("-",""));
        cartao.setNomeImpresso(dto.getNomeImpresso());
        cartao.setNumCartao(dto.getNumCartao().replace(" ",""));
        cartao.setValidade(dto.getValidade());

        return cartao;

    }
}
