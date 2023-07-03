package br.com.acme.model;

import br.com.acme.enums.TipoAssinatura;
import br.com.acme.exceptions.ClienteAtrasadoException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public class AssinaturaModel {
    private final BigDecimal mensalidade;
    private final LocalDate inicio;
    private final Optional<LocalDate> fim;
    private final ClienteModel cliente;
    private final TipoAssinatura tipoAssinatura;

    public AssinaturaModel(BigDecimal mensalidade, LocalDate inicio, LocalDate fim, ClienteModel cliente, TipoAssinatura tipoAssinatura, boolean isAtrasado) {
        this.mensalidade = mensalidade;
        this.inicio = inicio;
        this.fim = Optional.of(fim);
        this.cliente = cliente;
        this.tipoAssinatura = tipoAssinatura;
        if(isAtrasado) throw clienteComAtrasoException();
    }

    public BigDecimal getMensalidade() {
        return mensalidade;
    }

    public LocalDate getInicio() {
        return inicio;
    }


    public Optional<LocalDate> getFim() {
        return fim;
    }

    public ClienteModel getCliente() {
        return cliente;
    }

    public TipoAssinatura getTipoAssinatura() {
        return tipoAssinatura;
    }

    public ClienteAtrasadoException clienteComAtrasoException() {
        throw new ClienteAtrasadoException("Cliente com atraso, nao pode realizar compra!");
    }

}
