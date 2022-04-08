package com.santander.banco811.service;

import com.santander.banco811.dto.request.TransacaoRequest;
import com.santander.banco811.model.Transacao;
import com.santander.banco811.projections.TransacaoView;

import java.util.List;

public interface TransacaoService {
    List<Transacao> getAll();

    Transacao create(TransacaoRequest transacaoRequest, Integer id);

    Transacao getById(Integer id);

    void delete(Integer id);

    List<TransacaoView> getAllByTipoTransacao(String tipoTransacao);
}
