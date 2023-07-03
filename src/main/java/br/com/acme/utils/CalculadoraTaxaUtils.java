package br.com.acme.utils;

import br.com.acme.model.AssinaturaModel;

import java.math.BigDecimal;

import static br.com.acme.service.AssinaturaService.calcularMesesDeAssinatura;

public class CalculadoraTaxaUtils {

    private BigDecimal calculaTaxa(AssinaturaModel assinatura, BigDecimal percentual) {
        long tempoEmMeses = calcularMesesDeAssinatura(assinatura);
        return assinatura.getMensalidade().multiply(percentual).multiply(new BigDecimal(tempoEmMeses));
    }

    public BigDecimal calculaTaxaAnual(AssinaturaModel assinatura) {
        return calculaTaxa(assinatura, new BigDecimal("0.00"));
    }

    public BigDecimal calculaTaxaTrimestral(AssinaturaModel assinatura) {
        return calculaTaxa(assinatura, new BigDecimal("0.03"));
    }

    public BigDecimal calculaTaxaSemestral(AssinaturaModel assinatura) {
        return calculaTaxa(assinatura, new BigDecimal("0.05"));
    }
}
