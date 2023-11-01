package com.example.rent.it.service;

import com.example.rent.it.dto.cartaoDto.CartaoCriacaoDto;
import com.example.rent.it.dto.cartaoDto.CartaoDto;
import com.example.rent.it.dto.cartaoDto.CartaoMapper;
import com.example.rent.it.object.cartao.Cartao;
import com.example.rent.it.repository.CartaoRepository;
import com.example.rent.it.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartaoService {

    private final CartaoRepository cartaoRepository;
    private final UsuarioRepository usuarioRepository;

    public CartaoService(CartaoRepository cartaoRepository,
                         UsuarioRepository usuarioRepository) {
        this.cartaoRepository = cartaoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public CartaoDto criar(CartaoCriacaoDto cartaoDto) {
        Cartao cartao = new Cartao();

        cartao = CartaoMapper.of(cartaoDto);
        if(this.usuarioRepository.existsById(cartaoDto.getUsuario())){
           cartao.setUsuario(this.usuarioRepository.findById(cartaoDto.getUsuario()).get());
           cartao = this.cartaoRepository.save(cartao);

            return CartaoMapper.of(cartao);


        }
         return null;
    }

    public List<CartaoDto> acharPorUsuario(Long id) {
        return CartaoMapper.of(this.cartaoRepository.findByUsuarioId(id));
    }
    public CartaoDto acharPorId(Long id) {

        if (!this.cartaoRepository.existsById(id)){
            return null;
        }

        return CartaoMapper.of(this.cartaoRepository.findById(id).get());
    }

    public void deletarPorId(Long id) {
         this.cartaoRepository.deleteById(id);
    }

    public boolean exiteById(Long id) {
        return this.cartaoRepository.existsById(id);
    }
}
