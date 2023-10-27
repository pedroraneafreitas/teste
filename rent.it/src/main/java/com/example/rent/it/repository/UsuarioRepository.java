package com.example.rent.it.repository;

import com.example.rent.it.object.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByEmail(String email);
    boolean existsById(Long id);
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findById(Long id);

    @Query("SELECT u.foto FROM Usuario u WHERE u.id = ?1")
    byte[] findFotoById(Long id);

    boolean existsFotoById(Long id);
    @Modifying
    @Transactional
    @Query("UPDATE Usuario u SET u.foto = ?2 WHERE u.id = ?1")
    void atualizaFoto(Long id, byte[] foto);

}
