package com.santander.banco811.projections;

import com.santander.banco811.model.TipoConta;

public interface Conta2View {
    Integer getSaldo();

    TipoConta getTipoConta();
}
