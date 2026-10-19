package br.contacerta.controller;
/**
 * CRUD SIMPLES 3 - CONTA
 *
 * Controla o cadastro das contas de onde o dinheiro sai
 * (ex.: Carteira, Nubank, Conta do Banco do Brasil).
 *
 * Caminho de uma ação:
 *   TelaConta (view) -> ContaController -> ContaDAO (dao) -> banco
 *
 * O que este controller faz:
 *   - Recebe os dados digitados na tela.
 *   - Valida antes de mandar para o banco (ex.: nome obrigatório,
 *     saldo inicial não pode ser texto).
 *   - Chama o ContaDAO para executar o SQL.
 *   - Devolve o resultado (ou a mensagem de erro) para a tela mostrar.
 *
 * Métodos a implementar (CRUD completo):
 *   - inserir(...)   -> C: cadastrar nova conta
 *   - listar()       -> R: listar todas as contas
 *   - alterar(...)   -> U: editar uma conta existente
 *   - excluir(id)    -> D: excluir uma conta
 *
 * Atenção: conta que já tem gasto ligado não pode ser excluída
 * (chave estrangeira). Trate esse erro e avise o usuário.
 *
 * NÃO coloque SQL aqui (isso fica no DAO) nem código de tela (isso fica na view).
 */

public class ContaController {
    
}
