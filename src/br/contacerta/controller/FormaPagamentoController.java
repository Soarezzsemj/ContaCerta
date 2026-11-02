package br.contacerta.controller;

import br.contacerta.dao.FormaPagamentoDAO;
import br.contacerta.model.FormaPagamento;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * CRUD SIMPLES 2 - FORMA DE PAGAMENTO
 *
 * Caminho de uma ação:
 *   TelaFormaPagamento (view) -> FormaPagamentoController -> FormaPagamentoDAO (dao) -> banco
 *
 * Valida os dados vindos da tela, chama o DAO e traduz os erros do MySQL
 * (1062 = nome repetido, 1451 = forma de pagamento em uso por algum gasto).
 */
public class FormaPagamentoController {
    private final FormaPagamentoDAO dao = new FormaPagamentoDAO();

    private FormaPagamento montar(int id, String nome) throws Exception {
        nome = nome == null ? "" : nome.trim();

        if (nome.isEmpty()) {
            throw new Exception("Informe o nome da forma de pagamento.");
        }
        if (nome.length() > 30) {
            throw new Exception("O nome pode ter no máximo 30 caracteres.");
        }

        return new FormaPagamento(id, nome);
    }

    public void inserir(String nome) throws Exception {
        FormaPagamento f = montar(0, nome);
        try {
            dao.inserir(f);
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1062) {
                throw new Exception("Já existe uma forma de pagamento com esse nome.");
            }
            throw new Exception("Erro ao cadastrar a forma de pagamento: " + ex.getMessage());
        }
    }

    public ArrayList<FormaPagamento> listar() throws Exception {
        try {
            return dao.listar();
        } catch (SQLException ex) {
            throw new Exception("Erro ao listar as formas de pagamento: " + ex.getMessage());
        }
    }

    public void alterar(int id, String nome) throws Exception {
        FormaPagamento f = montar(id, nome);
        try {
            dao.alterar(f);
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1062) {
                throw new Exception("Já existe uma forma de pagamento com esse nome.");
            }
            throw new Exception("Erro ao alterar a forma de pagamento: " + ex.getMessage());
        }
    }

    public void excluir(int id) throws Exception {
        try {
            dao.excluir(id);
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1451) {
                throw new Exception("Esta forma de pagamento está sendo usada por gastos e não pode ser excluída.");
            }
            throw new Exception("Erro ao excluir a forma de pagamento: " + ex.getMessage());
        }
    }
}
