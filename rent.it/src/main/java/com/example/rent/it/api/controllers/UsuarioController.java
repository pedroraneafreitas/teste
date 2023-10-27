package com.example.rent.it.api.controllers;
import com.example.rent.it.dto.usuarioDto.UsuarioCriacao;
import com.example.rent.it.dto.usuarioDto.UsuarioLogin;
import com.example.rent.it.dto.usuarioDto.UsuarioToken;
import com.example.rent.it.object.usuario.*;
import com.example.rent.it.service.UsuarioService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import com.example.rent.it.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;



    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna o usuário com o ID especificado"),
            @ApiResponse(responseCode = "404", description = "Não foi encontrado usuário com o ID especificado")
    })
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if(usuarioOptional.isPresent()) {
            return ResponseEntity.ok(usuarioOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuários listados com sucesso")
    })
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return ResponseEntity.ok(usuarios);
    }



    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não foi encontrado usuário com o ID especificado")
    })
    public Usuario updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        if (this.usuarioRepository.existsById(id)) {
            usuario.setId(id);
            return this.usuarioRepository.save(usuario);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Não foi encontrado usuário com o ID especificado")
    })
    public void deleteUsuario(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
    }

    @PostMapping("/login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário autenticado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    })
    public ResponseEntity<UsuarioToken> login(@RequestBody UsuarioLogin usuarioLoginDto) {
        UsuarioToken usuarioTokenDto = this.usuarioService.autenticar(usuarioLoginDto);

        return ResponseEntity.status(200).body(usuarioTokenDto);
    }

    @PostMapping("/cadastrar")
    @ApiResponses(value = {
            @ApiResponse(responseCode ="201", description = "Usuário cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Não foi possível cadastrar o usuário"),
            @ApiResponse(responseCode = "409", description = "Já existe um usuário cadastrado com esse email")
    })
    @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Void> criar(@RequestBody UsuarioCriacao usuarioCriacaoDto
                                     // ,@RequestBody MultipartFile foto
    ) {
        this.usuarioService.criar(usuarioCriacaoDto);
        return ResponseEntity.status(201).build();
    }


    @GetMapping(value = "/foto/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    @ApiResponses(value = {
            @ApiResponse(responseCode ="200", description = "Itens encontrados"),
            @ApiResponse(responseCode = "404", description = "Nenhum item encontrado")
    })
    public ResponseEntity<byte[]> getFotoItem(@PathVariable Long id){

        return ResponseEntity.status(200).header("content-disposition",
                        " filename=\"usuario.jpg\"")
                .body(this.usuarioService.buscarFoto(id));

    }

    @CrossOrigin("*")
    @PatchMapping(value = "/foto/{id}", consumes = "image/jpg")
    @ApiResponses(value = {
            @ApiResponse(responseCode ="200", description = "Foto do Item Atualizada"),
            @ApiResponse(responseCode = "404", description = "Nenhum item encontrado")
    })
    public ResponseEntity<ResponseEntity> atualizaFotoItem(@PathVariable Long id,
                                                           @RequestBody byte[] foto){
        boolean resposta = this.usuarioService.atualizaFoto(id, foto);

        if(!resposta){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();

    }

}