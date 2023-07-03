import br.com.acme.model.AssinaturaModel;
import br.com.acme.model.ClienteModel;
import br.com.acme.model.PagamentoModel;
import br.com.acme.model.ProdutoModel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.time.Month.*;

public class Application {

    public static void main(String[] args) {

        // 1 Crie uma Classe com um método main para criar alguns produtos, clientes e pagamentos. [OK]
        // Crie Pagamentos com:  a data de hoje, ontem e um do mês passado.
        ClienteModel breno = new ClienteModel("Breno");
        ClienteModel joao = new ClienteModel("Joao");
        ClienteModel maria = new ClienteModel("Maria");

        ProdutoModel prod1 = new ProdutoModel("Apologize", null, new BigDecimal("98.90"));
        ProdutoModel prod2 = new ProdutoModel("Te levar daqui", null, new BigDecimal("50.00"));
        ProdutoModel prod3 = new ProdutoModel("Ana Julia", null, new BigDecimal("25.30"));

        PagamentoModel pag1 = new PagamentoModel(List.of(prod1, prod2), LocalDate.now(), breno);
        PagamentoModel pag2 = new PagamentoModel(List.of(prod1, prod2, prod3), LocalDate.now().minusDays(1), joao);
        PagamentoModel pag3 = new PagamentoModel(List.of(prod1), LocalDate.now().minusMonths(1), maria);

        // 2 - Ordene e imprima os pagamentos pela data de compra. [OK]
        System.out.println("--------------------------#2--------------------------");

        List<PagamentoModel> pagamentos = new ArrayList<>(List.of(pag1, pag2, pag3));

        pagamentos.sort(Comparator.comparing(PagamentoModel::getDataCompra));
        System.out.println("Pagamentos ordenados pela data de compra:");
        pagamentos.forEach(pag -> System.out.println(pag.getCliente().getNome() + " comprou em: " + pag.getDataCompra()));

        // 3 - Calcule e Imprima a soma dos valores de um pagamento com optional e recebendo um Double diretamente. [OK]
        System.out.println("--------------------------#3--------------------------");

        Optional<BigDecimal> optSoma = pag2.getProdutos().stream()
                .map(ProdutoModel::getPreco)
                .reduce(BigDecimal::add);

        double somaPag2 = optSoma.orElse(BigDecimal.ZERO).doubleValue();
        System.out.println("O valor da soma do pagamento 2 é de R$ " + somaPag2);

        // 4 -  Calcule o Valor de todos os pagamentos da Lista de pagamentos. [OK]
        System.out.println("--------------------------#4--------------------------");

        double somaPagamentos = pagamentos.stream().mapToDouble(prod ->
                prod.getProdutos().stream().mapToDouble(preco ->
                        preco.getPreco().doubleValue()).sum()).sum();

        System.out.println("A soma de todos os pagamentos é: R$" + somaPagamentos);

        // 5 - Imprima a quantidade de cada Produto vendido [OK]
        System.out.println("--------------------------#5--------------------------");

        System.out.println("Produtos vendidos: Quantidade");
        pagamentos.stream()
                .flatMap(pag -> pag.getProdutos().stream())
                .collect(Collectors.groupingBy(ProdutoModel::getNome, Collectors.counting()))
                .forEach((nome, qtd) -> System.out.println(nome + ": " + qtd));

        // 6 - Crie um Mapa de <Cliente, List<Produto> , onde Cliente pode ser o nome do cliente. [OK]

        Map<ClienteModel, List<List<ProdutoModel>>> clienteModelListMap = pagamentos.stream().collect(Collectors.groupingBy(PagamentoModel::getCliente,
                Collectors.mapping(PagamentoModel::getProdutos, Collectors.toList())));

        Map<ClienteModel, List<ProdutoModel>> clienteParaProduto = clienteModelListMap.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().stream().flatMap(List::stream)
                        .collect(Collectors.toList())));

        // 7 - Qual cliente gastou mais? [OK]
        System.out.println("--------------------------#7--------------------------");

        Function<PagamentoModel, BigDecimal> reducingFunction = p -> p.getProdutos().stream()
                .map(ProdutoModel::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Map<ClienteModel, BigDecimal> topClientes = pagamentos.stream()
                .collect(Collectors.groupingBy(PagamentoModel::getCliente,
                        Collectors.reducing(BigDecimal.ZERO, reducingFunction, BigDecimal::add)));

        topClientes.entrySet().stream()
                .sorted(Map.Entry.comparingByValue()).forEach(cliente ->
                        System.out.println(cliente.getKey().getNome() + " gastou: R$ " + cliente.getValue()));

        // 8 - Quanto foi faturado em um determinado mês? [OK]
        System.out.println("--------------------------#8--------------------------");
        Month mesCorrente = LocalDate.now().getMonth();

        BigDecimal faturadoNoMesAtual = pagamentos.stream()
                .filter(mes -> mes.getDataCompra().getMonth() == mesCorrente)
                .map(reducingFunction)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Foi faturado R$ " + faturadoNoMesAtual + " em " + mesCorrente.getDisplayName(TextStyle.FULL, Locale.ENGLISH));

        // 9 - Crie 3 assinaturas com assinaturas de 99.98 reais, sendo 2 deles com assinaturas encerradas. [OK]

        AssinaturaModel assinaturaEmAberto = new AssinaturaModel(new BigDecimal("99.98"), LocalDate.of(2023, JANUARY, 10), breno);
        AssinaturaModel assinaturaEncerrada1 = new AssinaturaModel(new BigDecimal("99.98"), LocalDate.of(2022, APRIL, 23), LocalDate.of(2022, DECEMBER, 30), joao);
        AssinaturaModel assinaturaEncerrada2 = new AssinaturaModel(new BigDecimal("99.98"), LocalDate.of(2020, OCTOBER, 15), LocalDate.of(2023, MAY, 5), maria);

        // 10 - Imprima o tempo em meses de alguma assinatura ainda ativa. [OK]
        System.out.println("--------------------------#10--------------------------");

        LocalDate hoje = LocalDate.now();

        long tempoEmMeses = ChronoUnit.MONTHS.between(assinaturaEmAberto.getInicio(), assinaturaEmAberto.getFim().orElse(hoje));
        System.out.println("O total de meses de assinatura de " + assinaturaEmAberto.getCliente().getNome() + " é: " + tempoEmMeses + " meses.");

        // 11 - Imprima o tempo de meses entre o start e end de todas assinaturas. Não utilize IFs para assinaturas sem end Time. [OK]
        System.out.println("--------------------------#11--------------------------");

        List<AssinaturaModel> assinaturas = new ArrayList<>(List.of(assinaturaEmAberto, assinaturaEncerrada1, assinaturaEncerrada2));

        assinaturas.forEach(ass -> {
            long meses = ChronoUnit.MONTHS.between(ass.getInicio(), ass.getFim().orElse(hoje));
            System.out.println("O total de meses de assinatura de " + ass.getCliente().getNome() + " é: " + meses + " meses.");
        });

        // 12 - Calcule o valor pago em cada assinatura até o momento. [OK]
        System.out.println("--------------------------#12--------------------------");

        assinaturas.forEach(ass -> {
            long meses = ChronoUnit.MONTHS.between(ass.getInicio(), ass.getFim().orElse(hoje));
            BigDecimal valorPago = ass.getMensalidade().multiply(BigDecimal.valueOf(meses));
            System.out.println("O valor pago por " + ass.getCliente().getNome() + " até agora é de R$ " + valorPago);
        });

    }
}
