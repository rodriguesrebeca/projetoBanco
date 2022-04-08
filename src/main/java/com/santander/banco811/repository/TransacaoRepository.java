package com.santander.banco811.repository;

import com.santander.banco811.model.Transacao;
import com.santander.banco811.projections.TransacaoView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Integer> {

    List<TransacaoView> findAllByTipoTransacao(String tipoTransacao);
}
