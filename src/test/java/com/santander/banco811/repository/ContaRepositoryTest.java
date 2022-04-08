package com.santander.banco811.repository;

import com.santander.banco811.model.Conta;
import com.santander.banco811.model.TipoConta;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Profile("test")
public class ContaRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ContaRepository contaRepository;

    @Test
    public void salvar_uma_nova_conta_com_sucesso() {
        Conta conta = new Conta();
        conta.setNumero(1);
        conta.setAgencia(2);
        conta.setTipoConta(TipoConta.PF);
        conta.setSaldo(BigDecimal.valueOf(800.00));

        conta = contaRepository.save(conta);

        Assertions.assertNotNull(conta.getNumero());
        Assertions.assertNotNull(conta.getAgencia());
        Assertions.assertNotNull(conta.getTipoConta());
        Assertions.assertNotNull(conta.getSaldo());
    }

    @Test
    public void trazer_conta_por_data_cricao() {
        Conta conta = new Conta();
        conta.setNumero(1);
        conta.setAgencia(2);
        conta.setTipoConta(TipoConta.PF);
        conta.setSaldo(BigDecimal.valueOf(800.00));

        conta = entityManager.persist(conta);

        var contaEncontrada = contaRepository.findAllByDataCriacao(
                conta.getDataCriacao());

        assertEquals(2, contaEncontrada.size());
        assertTrue(contaEncontrada.stream()
                .map(Conta::getId)
                .allMatch(id -> Arrays.asList(1, 2).contains(id)));
    }
}
