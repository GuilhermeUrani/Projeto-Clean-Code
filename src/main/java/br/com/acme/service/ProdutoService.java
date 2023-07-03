package br.com.acme.service;

import br.com.acme.model.PagamentoModel;
import br.com.acme.model.ProdutoModel;

import java.util.List;
import java.util.stream.Collectors;

public class ProdutoService {

    public void getQuantidadeProdutosVendidos(List<PagamentoModel> pagamentos){
        System.out.println("Produtos vendidos: Quantidade");
        pagamentos.stream()
                .flatMap(pag -> pag.getProdutos().stream())
                .collect(Collectors.groupingBy(ProdutoModel::getNome, Collectors.counting()))
                .forEach((nome, qtd) -> System.out.println(nome + ": " + qtd));
    }
}
