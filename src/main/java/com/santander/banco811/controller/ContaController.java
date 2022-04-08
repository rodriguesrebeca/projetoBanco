package com.santander.banco811.controller;

import com.santander.banco811.model.Conta;
import com.santander.banco811.dto.request.ContaRequest;
import com.santander.banco811.dto.response.ContaResponse;
import com.santander.banco811.model.TipoConta;
import com.santander.banco811.projections.ContaView;
import com.santander.banco811.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/conta")
public class ContaController {

    @Autowired
    ContaService contaService;

    @GetMapping
    public List<ContaResponse> getAll(){
        return ContaResponse.toResponse(contaService.getAll());
    }


    @GetMapping("/agencia")
    public Page<Conta> getByAgenciaAndNumero(
            @RequestParam Integer agencia,
            @RequestParam Integer numero,
            @RequestParam(required = false, defaultValue = "0")int page,
            @RequestParam(required = false, defaultValue = "3") int size){
        return contaService.getByAgenciaAndNumero(agencia, numero, page, size);
    }

    @GetMapping("/view")
    public List<ContaView> getAllContaViewByTipoConta(
            @RequestParam TipoConta tipoConta
    ){
        return contaService.getAllViewByTipoConta(tipoConta);
    }

    @GetMapping("/datacriacao")
    public List<Conta> getAllByDataCriacao(
            @RequestParam String dataCriacao
            ) {
        return contaService.getAllByDataCriacao(dataCriacao);
    }

    @PostMapping(path = "/{id}")
    public ContaResponse create(@PathVariable Integer id, @RequestBody ContaRequest contaRequest){
        return contaService.create(contaRequest, id);
    }

    @PutMapping("/{id}")
    public Conta update(@PathVariable Integer id, @RequestBody ContaRequest contaRequest){
        return contaService.update(contaRequest, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        contaService.delete(id);
    }

    @GetMapping("/{id}")
    public Conta getById(@PathVariable Integer id){
        return contaService.getById(id);
    }
}
