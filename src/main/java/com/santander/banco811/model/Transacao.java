package com.santander.banco811.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.santander.banco811.dto.request.TransacaoRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name = "transacao")
@Entity
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "valor")
    private BigDecimal valor;

    @Column(name = "tipo_transacao")
    private String tipoTransacao;

    @Column(name = "numero")
    private Integer numero;

    @Column(name = "agencia")
    private Integer agencia;

    @Column(name = "data_criacao")
    @CreatedDate
    private LocalDateTime dataCriacao;

    @Column(name = "data_atualizacao")
    @LastModifiedDate
    private LocalDateTime dataAtualizacao;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "conta_id", referencedColumnName = "id")
    private Conta conta;

    public Transacao(Conta conta, TransacaoRequest transacaoRequest) {
        this.valor = transacaoRequest.getValor();
        this.tipoTransacao = transacaoRequest.getTipoTransacao();
        this.numero = transacaoRequest.getNumero();
        this.agencia = transacaoRequest.getAgencia();
        this.conta = conta;
    }
}
