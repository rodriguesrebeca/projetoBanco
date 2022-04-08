package com.santander.banco811.service.impl;

import com.santander.banco811.dto.request.TransacaoRequest;
import com.santander.banco811.model.Transacao;
import com.santander.banco811.projections.TransacaoView;
import com.santander.banco811.repository.TransacaoRepository;
import com.santander.banco811.service.ContaService;
import com.santander.banco811.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class TransacaoServiceImpl implements TransacaoService {

    @Autowired
    TransacaoRepository transacaoRepository;

    @Autowired
    ContaService contaService;

    @Override
    public List<Transacao> getAll() {
        return transacaoRepository.findAll();
    }

    @Override
    public Transacao create(TransacaoRequest transacaoRequest, Integer id){
        var conta = contaService.getById(id);
        Transacao transacao = new Transacao(conta, transacaoRequest);
        return transacaoRepository.save(transacao);
    }

    @Override
    public Transacao getById(Integer id){
        return transacaoRepository.findById(id).orElseThrow();
    }

    @Override
    public void delete(Integer id){
        var transacao = transacaoRepository.findById(id).orElseThrow();
        transacaoRepository.delete(transacao);
    }

    @Override
    public List<TransacaoView> getAllByTipoTransacao(String tipoTransacao){
        return transacaoRepository.findAllByTipoTransacao(tipoTransacao);
    }

}
