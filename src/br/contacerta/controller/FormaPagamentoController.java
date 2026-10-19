package br.contacerta.controller;
/**
 * CRUD SIMPLES 2 - FORMA DE PAGAMENTO
 *
 * Controla o cadastro das formas de pagamento (ex.: Pix, Dinheiro, Cartão de crédito, Débito).
 *
 * Caminho de uma ação:
 *   TelaFormaPagamento (view) -> FormaPagamentoController -> FormaPagamentoDAO (dao) -> banco
 *
 * O que este controller faz:
 *   - Recebe os dados digitados na tela.
 *   - Valida antes de mandar para o banco (ex.: descrição não pode ficar vazia).
 *   - Chama o FormaPagamentoDAO para executar o SQL.
 *   - Devolve o resultado (ou a mensagem de erro) para a tela mostrar.
 *
 * Métodos a implementar (CRUD completo):
 *   - inserir(...)   -> C: cadastrar nova forma de pagamento
 *   - listar()       -> R: listar todas as formas de pagamento
 *   - alterar(...)   -> U: editar uma forma de pagamento existente
 *   - excluir(id)    -> D: excluir uma forma de pagamento
 *
 * Atenção: forma de pagamento que já foi usada em algum gasto não pode ser
 * excluída (chave estrangeira). Trate esse erro e avise o usuário.
 *
 * NÃO coloque SQL aqui (isso fica no DAO) nem código de tela (isso fica na view).
 */

public class FormaPagamentoController {
    
}
