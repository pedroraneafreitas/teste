package com.example.rent.it.repository;

import com.example.rent.it.object.endereco.Endereco;
import com.example.rent.it.object.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    Optional<Endereco> findById(Long id);

    List<Endereco> findAllByUsuario(Usuario usuario);

    Endereco findByUsuario(Usuario usuario);

    boolean existsByUsuario(Usuario usuario);
}
