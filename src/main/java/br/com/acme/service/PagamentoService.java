package br.com.acme.service;

import br.com.acme.model.ClienteModel;
import br.com.acme.model.PagamentoModel;
import br.com.acme.model.ProdutoModel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PagamentoService {

    public void ordernarPelaDataDaCompra(List<PagamentoModel> pagamentos){
        pagamentos.sort(Comparator.comparing(PagamentoModel::getDataCompra));
        System.out.println("Pagamentos ordenados pela data de compra:");
        pagamentos.forEach(pag -> System.out.println(pag.getCliente().getNome() + " comprou em: " + pag.getDataCompra()));
    }

    public void calcularSomaPagamento(PagamentoModel pagamento){
        Optional<BigDecimal> optSoma = pagamento.getProdutos().stream()
                .map(ProdutoModel::getPreco)
                .reduce(BigDecimal::add);

        double valor = optSoma.orElse(BigDecimal.ZERO).doubleValue();
        System.out.println("O valor da soma do pagamento é de R$ " + valor);
    }

    public void calcularPagamentos(List<PagamentoModel> pagamentos){
        double somaPagamentos = pagamentos.stream().mapToDouble(prod ->
                prod.getProdutos().stream().mapToDouble(preco ->
                        preco.getPreco().doubleValue()).sum()).sum();

        System.out.println("A soma de todos os pagamentos é: R$" + somaPagamentos);
    }

    public void criarMapaClienteProduto(List<PagamentoModel> pagamentos) {
        Map<ClienteModel, List<List<ProdutoModel>>> clienteModelListMap = pagamentos.stream().collect(Collectors.groupingBy(PagamentoModel::getCliente,
                Collectors.mapping(PagamentoModel::getProdutos, Collectors.toList())));

        Map<ClienteModel, List<ProdutoModel>> clienteParaProduto = clienteModelListMap.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().stream().flatMap(List::stream)
                        .collect(Collectors.toList())));
    }

    public void calcularMaiorPagamentoPorCliente(List<PagamentoModel> pagamentos){

        Function<PagamentoModel, BigDecimal> reducingFunction = p -> p.getProdutos().stream()
                .map(ProdutoModel::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Map<ClienteModel, BigDecimal> topClientes = pagamentos.stream()
                .collect(Collectors.groupingBy(PagamentoModel::getCliente,
                        Collectors.reducing(BigDecimal.ZERO, reducingFunction, BigDecimal::add)));

        topClientes.entrySet().stream()
                .sorted(Map.Entry.comparingByValue()).forEach(cliente ->
                System.out.println(cliente.getKey().getNome() + " gastou: R$ " + cliente.getValue()));
    }

    public void calcularFaturamentoPorMes(List<PagamentoModel> pagamentos){
        Function<PagamentoModel, BigDecimal> reducingFunction = p -> p.getProdutos().stream()
                .map(ProdutoModel::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Month mesCorrente = LocalDate.now().getMonth();

        BigDecimal faturadoNoMesAtual = pagamentos.stream()
                .filter(mes -> mes.getDataCompra().getMonth() == mesCorrente)
                .map(reducingFunction)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Foi faturado R$ " + faturadoNoMesAtual + " em " + mesCorrente.getDisplayName(TextStyle.FULL, Locale.ENGLISH));
    }


}
