package com.santander.banco811.service;

import com.santander.banco811.model.Conta;
import com.santander.banco811.dto.request.ContaRequest;
import com.santander.banco811.dto.response.ContaResponse;
import com.santander.banco811.model.TipoConta;
import com.santander.banco811.projections.ContaView;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;


public interface ContaService {

    List<Conta> getAll();
    ContaResponse create(ContaRequest contaRequest, Integer id);
    Conta update(ContaRequest contaRequest, Integer id);
    void delete(Integer id);
    Conta getById(Integer id);

    Page<Conta> getByAgenciaAndNumero(Integer agencia, Integer numero, int page, int size);

    List<ContaView> getAllViewByTipoConta(TipoConta tipoConta);

    List<Conta> getAllByDataCriacao(String dataCriacao);
}
