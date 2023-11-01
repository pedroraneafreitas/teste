package com.example.rent.it.repository;

import com.example.rent.it.object.cartao.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface CartaoRepository extends JpaRepository<Cartao, Long> {
    List<Cartao> findByUsuarioId(Long id);
}
