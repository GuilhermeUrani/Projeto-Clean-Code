package br.com.acme.service;

import br.com.acme.model.AssinaturaModel;
import br.com.acme.utils.CalculadoraTaxaUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class AssinaturaService {

    private static final LocalDate hoje = LocalDate.now();

    public static long calcularMesesDeAssinatura(AssinaturaModel assinatura) {

        long tempoEmMeses = ChronoUnit.MONTHS.between(assinatura.getInicio(), assinatura.getFim().orElse(hoje));
        System.out.println("O total de meses de assinatura de " + assinatura.getCliente().getNome() + " é: " + tempoEmMeses + " meses.");
        return tempoEmMeses;
    }

    public void calcularPeriodoAssinaturas(List<AssinaturaModel> assinaturas) {
        assinaturas.forEach(ass -> {
            long meses = ChronoUnit.MONTHS.between(ass.getInicio(), ass.getFim().orElse(hoje));
            System.out.println("O total de meses de assinatura de " + ass.getCliente().getNome() + " é: " + meses + " meses.");
        });
    }

    public void calcularValorPagoPorAssinatura(List<AssinaturaModel> assinaturas) {
        assinaturas.forEach(ass -> {
            long meses = ChronoUnit.MONTHS.between(ass.getInicio(), ass.getFim().orElse(hoje));
            BigDecimal valorPago = ass.getMensalidade().multiply(BigDecimal.valueOf(meses));
            System.out.println("O valor pago por " + ass.getCliente().getNome() + " até agora é de R$ " + valorPago);
        });
    }

    public BigDecimal calcularTaxaAssinatura(AssinaturaModel assinatura) {
        BigDecimal taxa = new BigDecimal(0);
        CalculadoraTaxaUtils calculadora = new CalculadoraTaxaUtils();

        switch (assinatura.getTipoAssinatura()) {
            case ANUAL:
                taxa = calculadora.calculaTaxaAnual(assinatura);
                break;
            case TRIMESTRAL:
                taxa = calculadora.calculaTaxaTrimestral(assinatura);
                break;
            case SEMESTRAL:
                taxa = calculadora.calculaTaxaSemestral(assinatura);
                break;
        }
        return taxa;
    }

}
