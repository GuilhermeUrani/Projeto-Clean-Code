import br.com.acme.model.AssinaturaModel;
import br.com.acme.model.ClienteModel;
import br.com.acme.service.AssinaturaService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static br.com.acme.builders.AssinaturaBuilder.umaAssinatura;
import static br.com.acme.enums.TipoAssinatura.*;
import static java.time.Month.*;

public class Application {

    public static void main(String[] args) {

        ClienteModel breno = new ClienteModel("Breno");
        ClienteModel marcus = new ClienteModel("Marcus");
        ClienteModel diego = new ClienteModel("Diego");
        ClienteModel guilherme = new ClienteModel("Guilherme");

        AssinaturaModel assinaturaEmAberto = umaAssinatura()
                .comCliente(breno)
                .comTipoAssinatura(ANUAL)
                .agora();

        AssinaturaModel assinaturaEmAberto2 = umaAssinatura()
                .comCliente(diego)
                .comTipoAssinatura(TRIMESTRAL)
                .comMensalidade(new BigDecimal("80.00"))
                .comInicio(LocalDate.of(2021, DECEMBER, 8))
                .agora();

        AssinaturaModel assinaturaEncerrada1 = umaAssinatura()
                .comCliente(marcus)
                .comTipoAssinatura(TRIMESTRAL)
                .comInicio(LocalDate.of(2022, APRIL, 23))
                .comFim(LocalDate.of(2022, DECEMBER, 30))
                .agora();

        AssinaturaModel assinaturaEncerrada2 = umaAssinatura()
                .comCliente(guilherme)
                .comTipoAssinatura(SEMESTRAL)
                .comInicio(LocalDate.of(2020, OCTOBER, 15))
                .comFim(LocalDate.of(2023, MAY, 5))
                .agora();

        AssinaturaService assinaturaService = new AssinaturaService();

        List<AssinaturaModel> assinaturas = new ArrayList<>(List.of(assinaturaEmAberto, assinaturaEncerrada1, assinaturaEncerrada2, assinaturaEmAberto2));

        System.out.println("--------------------------Calculo das taxas--------------------------");
        assinaturas.forEach(assinatura -> {
            BigDecimal taxa = assinaturaService.calcularTaxaAssinatura(assinatura);
            System.out.println(assinatura.getCliente().getNome() + " paga mensalidade de R$" + assinatura.getMensalidade() +
                    " no plano " + assinatura.getTipoAssinatura() + " + taxa de R$ " + String.format("%.2f", taxa));
        });
    }
}
