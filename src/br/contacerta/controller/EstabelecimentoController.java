package br.contacerta.controller;

/**
 * CRUD SIMPLES 4 - ESTABELECIMENTO
 *
 * Controla o cadastro dos lugares onde o gasto foi feito
 * (ex.: Supermercado Big Box, Posto Shell, iFood).
 *
 * Caminho de uma ação:
 *   TelaEstabelecimento (view) -> EstabelecimentoController -> EstabelecimentoDAO (dao) -> banco
 *
 * O que este controller faz:
 *   - Recebe os dados digitados na tela.
 *   - Valida antes de mandar para o banco (ex.: nome obrigatório).
 *   - Chama o EstabelecimentoDAO para executar o SQL.
 *   - Devolve o resultado (ou a mensagem de erro) para a tela mostrar.
 *
 * Métodos a implementar (CRUD completo):
 *   - inserir(...)   -> C: cadastrar novo estabelecimento
 *   - listar()       -> R: listar todos os estabelecimentos
 *   - alterar(...)   -> U: editar um estabelecimento existente
 *   - excluir(id)    -> D: excluir um estabelecimento
 *
 * Atenção: estabelecimento que já tem gasto ligado não pode ser excluído
 * (chave estrangeira). Trate esse erro e avise o usuário.
 *
 * NÃO coloque SQL aqui (isso fica no DAO) nem código de tela (isso fica na view).
 */

public class EstabelecimentoController {
    
}
