package com.santander.banco811.service.impl;

import com.santander.banco811.model.Conta;
import com.santander.banco811.model.TipoConta;
import com.santander.banco811.projections.ContaView;
import com.santander.banco811.repository.ContaRepository;
import com.santander.banco811.service.ContaService;
import com.santander.banco811.service.UsuarioService;
import com.santander.banco811.dto.request.ContaRequest;
import com.santander.banco811.dto.response.ContaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ContaServiceImpl implements ContaService {


    @Autowired
    ContaRepository contaRepository;

    @Autowired
    UsuarioService usuarioService;

    @Override
    public List<Conta> getAll() {
        return contaRepository.findAll();
    }

    @Override
    public ContaResponse create(ContaRequest contaRequest, Integer id) {
        var usuario = usuarioService.getById(id);
        Conta conta = new Conta(usuario, contaRequest);
        return new ContaResponse(contaRepository.save(conta));
    }

    @Override
    public Conta update(ContaRequest contaRequest, Integer id) {
        var conta = contaRepository.findById(id).orElseThrow();
        conta.setNumero(contaRequest.getNumero());
        conta.setAgencia(contaRequest.getAgencia());
        conta.setTipoConta(contaRequest.getTipoConta());
        conta.setSaldo(contaRequest.getSaldo());

        return contaRepository.save(conta);
    }

    @Override
    public void delete(Integer id) {
        var conta = contaRepository.findById(id).orElseThrow();
        contaRepository.delete(conta);
    }

    @Override
    public Conta getById(Integer id) {
        return contaRepository.findById(id).orElseThrow();
    }

    @Override
    public Page<Conta> getByAgenciaAndNumero(Integer agencia, Integer numero, int page, int size){
        PageRequest pageRequest = PageRequest.of(
                page,
                size);

        return contaRepository.findByAgenciaAndNumero(agencia, numero, pageRequest);
    }

    @Override
    public List<ContaView> getAllViewByTipoConta(TipoConta tipoConta) {
        return contaRepository.findAllByTipoConta(tipoConta);
    }

    @Override
    public List<Conta> getAllByDataCriacao(String dataCriacao){
        return contaRepository.findAllByDataCriacao(LocalDateTime.parse(dataCriacao));
    }
}
