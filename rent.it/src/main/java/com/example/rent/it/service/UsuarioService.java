package com.example.rent.it.service;

import com.example.rent.it.Token.GerenciadorTokenJwt;
import com.example.rent.it.dto.usuarioDto.UsuarioCriacao;
import com.example.rent.it.dto.usuarioDto.UsuarioLogin;
import com.example.rent.it.dto.usuarioDto.UsuarioMapper;
import com.example.rent.it.dto.usuarioDto.UsuarioToken;
import com.example.rent.it.object.usuario.*;
import com.example.rent.it.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UsuarioService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private GerenciadorTokenJwt gerenciadorTokenJwt;

    @Autowired
    private AuthenticationManager authenticationManager;

    public void criar(UsuarioCriacao usuarioCriacaoDto) {
        Usuario novoUsuario = UsuarioMapper.of(usuarioCriacaoDto);

        String senhaCriptografada = passwordEncoder.encode(novoUsuario.getPassword());
        novoUsuario.setPassword(senhaCriptografada);

        this.usuarioRepository.save(novoUsuario);
    }

    public UsuarioToken autenticar(UsuarioLogin usuarioLoginDto) {

        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                usuarioLoginDto.getEmail(), usuarioLoginDto.getPassword());

        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        Usuario usuarioAutenticado =
                usuarioRepository.findByEmail(usuarioLoginDto.getEmail())
                        .orElseThrow(
                                () -> new ResponseStatusException(404, "Email do usuário não cadastrado", null)
                        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = gerenciadorTokenJwt.generateToken(authentication);

        return UsuarioMapper.of(usuarioAutenticado, token);
    }


    public byte[] buscarFoto(Long id) {

        if(!this.usuarioRepository.existsFotoById(id)){
            return this.usuarioRepository.findFotoById(id - id);
        }

        return this.usuarioRepository.findFotoById(id);
    }

    public boolean atualizaFoto(Long id, byte[] foto) {

        if(this.usuarioRepository.existsById(id)){

            this.usuarioRepository.atualizaFoto(id,foto);
            return true;
        }

        return false;
    }



}
