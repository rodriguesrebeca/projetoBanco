package com.santander.banco811.projections;

import com.santander.banco811.model.Conta;
import org.springframework.beans.factory.annotation.Value;

public interface TransacaoView {

    String getTipoTransacao();

    @Value("#{target.valor + ' - ' + target.numero}")
    String getValorNumero();

    Conta2View getConta();
}
