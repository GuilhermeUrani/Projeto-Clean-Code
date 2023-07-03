Projeto Loja Virtual ACME - Clean and Code
Primeiro entregável da disciplina de Clean and Code do Instituto Infnet.
Alunos: Erisson Breno, Marcus Vinicius, Diego Mischiatti e Guilherme Urani.
Classes de modelo
Produto [String nome, Path file, BigDecimal Preco]
Cliente [String Nome]
Assinatura [BigDecimal mensalidade, begin, end (Optional) Cliente] Obs: Crie 2 construtores, um com o "end" e outro sem.
Quando um usuário realiza uma nova compra um "Pagamento" é utilizado para representá-la.

Pagamento [List, dataCompra, Cliente]

Crie uma Classe com um método main para criar alguns produtos, clientes e pagamentos. Crie Pagamentos com: a data de hoje, ontem e um do mês passado.

Ordene e imprima os pagamentos pela data de compra.

Calcule e Imprima a soma dos valores de um pagamento com optional e recebendo um Double diretamente.

Calcule o Valor de todos os pagamentos da Lista de pagamentos.

Imprima a quantidade de cada Produto vendido.

Crie um Mapa de <Cliente, List , onde Cliente pode ser o nome do cliente.

Qual cliente gastou mais?

Quanto foi faturado em um determinado mês?

Crie 3 assinaturas com assinaturas de 99.98 reais, sendo 2 deles com assinaturas encerradas.

Imprima o tempo em meses de alguma assinatura ainda ativa.

Imprima o tempo de meses entre o start e end de todas assinaturas. Não utilize IFs para assinaturas sem end Time.

Calcule o valor pago em cada assinatura até o momento.

Pull Request
Após finalizar o desenvolvimento do projeto, faremos o Pull Request para o GitHub, incluindo o nome do grupo no arquivo README.