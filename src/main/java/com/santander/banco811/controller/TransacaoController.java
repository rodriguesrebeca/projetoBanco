package com.santander.banco811.controller;

import com.santander.banco811.dto.request.TransacaoRequest;
import com.santander.banco811.dto.response.TransacaoResponse;
import com.santander.banco811.model.TipoTransacao;
import com.santander.banco811.model.Transacao;
import com.santander.banco811.projections.TransacaoView;
import com.santander.banco811.repository.TransacaoRepository;
import com.santander.banco811.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    @Autowired
    TransacaoService transacaoService;

    @GetMapping("/all")
    public List<TransacaoResponse> getAll() {
        return TransacaoResponse.toResponse(transacaoService.getAll());
    }

    @GetMapping("/{id}")
    public Transacao getById(@PathVariable Integer id) {
        return transacaoService.getById(id);
    }

    @PostMapping("/new")
    public TransacaoResponse create(@RequestParam Integer id, @RequestBody TransacaoRequest transacaoRequest){
        return new TransacaoResponse(transacaoService.create(transacaoRequest, id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        transacaoService.delete(id);
    }

    @GetMapping("/view")
    public List<TransacaoView> getAllTransacaoByTipoTransacao(@RequestParam String tipoTransacao){
        return transacaoService.getAllByTipoTransacao(tipoTransacao);
    }

}
