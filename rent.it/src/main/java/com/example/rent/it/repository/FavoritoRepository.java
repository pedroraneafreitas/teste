package com.example.rent.it.repository;

import com.example.rent.it.object.favoritos.Favoritos;
import com.example.rent.it.object.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoritoRepository extends JpaRepository<Favoritos, Long> {
    List<Favoritos> findByUsuarioId(Long id);
}
