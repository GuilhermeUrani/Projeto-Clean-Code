package br.com.acme.builders;

import br.com.acme.enums.TipoAssinatura;
import br.com.acme.model.AssinaturaModel;
import br.com.acme.model.ClienteModel;

import java.math.BigDecimal;
import java.time.LocalDate;

import static java.time.Month.JANUARY;

public class AssinaturaBuilder {
    private BigDecimal mensalidade;
    private LocalDate inicio;
    private LocalDate fim;
    private ClienteModel cliente;
    private TipoAssinatura tipoAssinatura;
    private boolean isAtrasado;

    private AssinaturaBuilder() {
    }

    public static AssinaturaBuilder umaAssinatura() {
        AssinaturaBuilder builder = new AssinaturaBuilder();
        inicializarDadosPadroes(builder);
        return builder;
    }

    public static void inicializarDadosPadroes(AssinaturaBuilder builder) {
        builder.mensalidade = new BigDecimal("99.98");
        builder.inicio = LocalDate.of(2023, JANUARY, 1);
        builder.fim = LocalDate.now();
        builder.cliente = new ClienteModel("ClienteBuilder");
        builder.tipoAssinatura = TipoAssinatura.ANUAL;
        builder.isAtrasado = false;
    }

    public AssinaturaBuilder comMensalidade(BigDecimal mensalidade) {
        this.mensalidade = mensalidade;
        return this;
    }

    public AssinaturaBuilder comInicio(LocalDate inicio) {
        this.inicio = inicio;
        return this;
    }

    public AssinaturaBuilder comFim(LocalDate fim) {
        this.fim = fim;
        return this;
    }

    public AssinaturaBuilder comCliente(ClienteModel cliente) {
        this.cliente = cliente;
        return this;
    }

    public AssinaturaBuilder comTipoAssinatura(TipoAssinatura tipoAssinatura) {
        this.tipoAssinatura = tipoAssinatura;
        return this;
    }

    public AssinaturaBuilder comAtraso(boolean isAtrasado) {
        this.isAtrasado = isAtrasado;
        return this;
    }

    public AssinaturaModel agora() {
        return new AssinaturaModel(mensalidade, inicio, fim, cliente, tipoAssinatura, isAtrasado);
    }
}

