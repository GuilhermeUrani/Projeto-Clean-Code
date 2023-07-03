package br.com.acme.model;

import java.time.LocalDate;
import java.util.List;

public class PagamentoModel {

    private final List<ProdutoModel> produtos;
    private final LocalDate dataCompra;
    private final ClienteModel cliente;

    public PagamentoModel(List<ProdutoModel> produtos, LocalDate dataCompra, ClienteModel cliente) {
        this.produtos = produtos;
        this.dataCompra = dataCompra;
        this.cliente = cliente;
    }

    public List<ProdutoModel> getProdutos() {
        return produtos;
    }

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public ClienteModel getCliente() {
        return cliente;
    }
}
