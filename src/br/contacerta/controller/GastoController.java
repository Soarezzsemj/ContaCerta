package br.contacerta.controller;
/**
 * CRUD PRINCIPAL - GASTO  (rotina principal do sistema)
 *
 * É o coração do ContaCerta: registrar quanto foi gasto, quando, onde e como.
 * Diferente dos CRUDs simples, um gasto RELACIONA dados de outras tabelas:
 *
 *     CATEGORIA ─┐
 *     CONTA ─────┼──> GASTO (valor, data, descrição)
 *     FORMA PAG ─┤
 *     ESTABELEC. ┘
 *
 * Caminho de uma ação:
 *   TelaGasto (view) -> GastoController -> GastoDAO (dao) -> banco
 *
 * O que este controller faz:
 *   - Recebe os dados da tela. Categoria, conta, forma de pagamento e
 *     estabelecimento vêm de JComboBox preenchidos com os cadastros simples.
 *   - Valida antes de gravar:
 *       * valor maior que zero;
 *       * data preenchida e válida (não aceitar data no futuro, se o grupo quiser);
 *       * categoria, conta, forma de pagamento e estabelecimento selecionados.
 *   - Chama o GastoDAO para executar o SQL.
 *   - Devolve o resultado (ou a mensagem de erro) para a tela mostrar.
 *
 * Métodos a implementar (CRUD completo):
 *   - inserir(...)   -> C: registrar novo gasto
 *   - listar()       -> R: listar gastos usando JOIN (ver abaixo)
 *   - alterar(...)   -> U: editar um gasto existente
 *   - excluir(id)    -> D: excluir um gasto
 *
 * Métodos de apoio para a tela (preencher os JComboBox):
 *   - listarCategorias(), listarContas(), listarFormasPagamento(),
 *     listarEstabelecimentos() -> chamam os DAOs dos CRUDs simples.
 *
 * CONSULTA COM JOIN (requisito do trabalho):
 *   A listagem NÃO deve mostrar só os códigos (id_categoria = 3, id_conta = 1...).
 *   O GastoDAO deve fazer um SELECT com INNER JOIN nas 4 tabelas para trazer os
 *   NOMES: "Alimentação", "Nubank", "Pix", "Big Box".
 *
 * NÃO coloque SQL aqui (isso fica no DAO) nem código de tela (isso fica na view).
 */


public class GastoController {
    
}
