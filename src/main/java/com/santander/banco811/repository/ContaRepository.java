package com.santander.banco811.repository;

import com.santander.banco811.model.Conta;

import com.santander.banco811.model.TipoConta;
import com.santander.banco811.projections.ContaView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer> {

    @Query("select c from Conta c where c.agencia = :agencia AND c.numero = :numero")
    Page<Conta> findByAgenciaAndNumero(@Param("agencia")Integer agencia, @Param("numero") Integer numero, Pageable pageable);

    List<ContaView> findAllByTipoConta(TipoConta tipoConta);

    List<Conta> findAllByDataCriacao(LocalDateTime localDateTime);
}
