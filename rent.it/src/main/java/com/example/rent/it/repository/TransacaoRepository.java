package com.example.rent.it.repository;

import com.example.rent.it.object.transacao.Transacao;
import com.example.rent.it.object.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    Long countByFkUsuario(Usuario usuario);

    List<Transacao> findAllByFkUsuario(Usuario usuario);


}
