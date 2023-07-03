package br.com.acme.model;

import java.math.BigDecimal;
import java.nio.file.Path;

public class ProdutoModel {
    private final String nome;
    private final Path file;
    private final BigDecimal preco;

    public ProdutoModel(String nome, Path file, BigDecimal preco) {
        this.nome = nome;
        this.file = file;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }
}
