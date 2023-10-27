package com.example.rent.it.repository;

import com.example.rent.it.object.avaliacao.Avaliacao;
import com.example.rent.it.object.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AvaliacaoRepository extends JpaRepository <Avaliacao, Long>{
     List<Avaliacao> findAllByTransacaoFkItem(Item Item);
}
