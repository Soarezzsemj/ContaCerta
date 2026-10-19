package br.contacerta.controller;

/**
 * CRUD SIMPLES 1 - CATEGORIA
 *
 * Controla o cadastro de categorias de gasto (ex.: Alimentação, Transporte, Lazer).
 *
 * Caminho de uma ação:
 *   TelaCategoria (view) -> CategoriaController -> CategoriaDAO (dao) -> banco
 *
 * O que este controller faz:
 *   - Recebe os dados digitados na tela.
 *   - Valida antes de mandar para o banco (ex.: nome não pode ficar vazio).
 *   - Chama o CategoriaDAO para executar o SQL.
 *   - Devolve o resultado (ou a mensagem de erro) para a tela mostrar.
 *
 * Métodos a implementar (CRUD completo):
 *   - inserir(...)   -> C: cadastrar nova categoria
 *   - listar()       -> R: listar todas as categorias
 *   - alterar(...)   -> U: editar uma categoria existente
 *   - excluir(id)    -> D: excluir uma categoria
 *
 * Atenção: categoria que já tem gasto ligado não pode ser excluída
 * (chave estrangeira). Trate esse erro e mostre uma mensagem clara na tela.
 *
 * NÃO coloque SQL aqui (isso fica no DAO) nem código de tela (isso fica na view).
 */

public class CategoriaController {
    
}
