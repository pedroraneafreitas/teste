package com.example.rent.it.dto.enderecoDto;

import com.example.rent.it.object.endereco.Endereco;
import com.example.rent.it.object.usuario.Usuario;

import java.util.ArrayList;
import java.util.List;

public class EnderecoMapper {
    public static Endereco of(EnderecoCriacao enderecoCriacaoDto, Usuario u) {
        Endereco endereco = new Endereco();


        endereco.setCep(enderecoCriacaoDto.getCep());
        endereco.setCidade(enderecoCriacaoDto.getCidade());
        endereco.setComplemento(enderecoCriacaoDto.getComplemento());
        endereco.setBairro(enderecoCriacaoDto.getBairro());
        endereco.setLogradouro(endereco.getLogradouro());
        endereco.setNumero(enderecoCriacaoDto.getNumero());
        endereco.setUsuario(u);
        return endereco;
    }

    public static Endereco of(EnderecoPutDto enderecoCriacaoDto, Usuario u) {
        Endereco endereco = new Endereco();


        endereco.setCep(enderecoCriacaoDto.getCep());
        endereco.setCidade(enderecoCriacaoDto.getCidade());
        endereco.setComplemento(enderecoCriacaoDto.getComplemento());
        endereco.setLogradouro(enderecoCriacaoDto.getLogradouro());
        endereco.setBairro(enderecoCriacaoDto.getBairro());
        endereco.setNumero(enderecoCriacaoDto.getNumero());
        endereco.setUsuario(u);
        return endereco;
    }

    public static List<EnderecoDto> of(List<Endereco> enderecos){
        List<EnderecoDto> retorno = new ArrayList<>();
        for (Endereco e:
             enderecos) {
            EnderecoDto dto = new EnderecoDto();
            dto.setId(e.getId());
            dto.setCep(e.getCep());
            dto.setCidade(e.getCidade());
            dto.setNumero(e.getNumero());
            dto.setComplemento(e.getComplemento());
            retorno.add(dto);
        }
        return retorno;
    }

    public static EnderecoDto of(Endereco e){

            EnderecoDto dto = new EnderecoDto();

            dto.setId(e.getId());
            dto.setCep(e.getCep());
            dto.setCidade(e.getCidade());
            dto.setNumero(e.getNumero());
            dto.setLogradouro(e.getLogradouro());
            dto.setBairro(e.getBairro());
            dto.setComplemento(e.getComplemento());


        return dto;
    }
}
