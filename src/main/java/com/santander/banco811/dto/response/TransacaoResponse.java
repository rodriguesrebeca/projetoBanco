package com.santander.banco811.dto.response;

import com.santander.banco811.model.Transacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
public class TransacaoResponse {

    private BigDecimal valor;
    private String tipoTransacao;
    private Integer numero;
    private Integer agencia;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;

    public TransacaoResponse(Transacao transacao){
        this.valor = transacao.getValor();
        this.tipoTransacao = transacao.getTipoTransacao();
        this.numero = transacao.getNumero();
        this.agencia = transacao.getAgencia();
        this.dataCriacao = transacao.getDataCriacao();
        this.dataAtualizacao = transacao.getDataCriacao();
    }

    public static List<TransacaoResponse> toResponse(List<Transacao> transacoes){
        return transacoes.stream().map(TransacaoResponse::new).collect(Collectors.toList());
    }
}
