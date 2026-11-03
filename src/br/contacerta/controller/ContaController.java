package br.contacerta.controller;

import br.contacerta.dao.ContaDAO;
import br.contacerta.model.Conta;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

public class ContaController {
    private final ContaDAO dao = new ContaDAO();

    // Valida os textos da tela e monta o objeto Conta
    private Conta montar(int id, String nome, String banco, String saldoTexto) throws Exception {
        nome = nome == null ? "" : nome.trim();
        banco = banco == null ? "" : banco.trim();
        saldoTexto = saldoTexto == null ? "" : saldoTexto.trim();

        if (nome.isEmpty()) {
            throw new Exception("Informe o nome da conta.");
        }
        if (nome.length() > 50) {
            throw new Exception("O nome pode ter no máximo 50 caracteres.");
        }
        if (banco.length() > 50) {
            throw new Exception("O banco pode ter no máximo 50 caracteres.");
        }
        if (banco.isEmpty()) {
            banco = null; // banco é opcional: vazio vira NULL
        }

        if (saldoTexto.isEmpty()) {
            throw new Exception("Informe o saldo inicial (pode ser 0).");
        }
        // aceita vírgula ou ponto: troca a vírgula por ponto
        saldoTexto = saldoTexto.replace(',', '.');
        // só números: até 8 dígitos antes e até 2 depois do ponto (DECIMAL(10,2))
        if (!saldoTexto.matches("\\d{1,8}(\\.\\d{1,2})?")) {
            throw new Exception("Saldo inicial inválido. Use apenas números, com até 2 casas decimais (ex.: 150,50).");
        }
        BigDecimal saldo = new BigDecimal(saldoTexto);

        return new Conta(id, nome, banco, saldo);
    }

    public void inserir(String nome, String banco, String saldo) throws Exception {
        Conta c = montar(0, nome, banco, saldo);
        try {
            dao.inserir(c);
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1062) {
                throw new Exception("Já existe uma conta com esse nome.");
            }
            throw new Exception("Erro ao cadastrar a conta: " + ex.getMessage());
        }
    }

    public ArrayList<Conta> listar() throws Exception {
        try {
            return dao.listar();
        } catch (SQLException ex) {
            throw new Exception("Erro ao listar as contas: " + ex.getMessage());
        }
    }

    public void alterar(int id, String nome, String banco, String saldo) throws Exception {
        Conta c = montar(id, nome, banco, saldo);
        try {
            dao.alterar(c);
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1062) {
                throw new Exception("Já existe uma conta com esse nome.");
            }
            throw new Exception("Erro ao alterar a conta: " + ex.getMessage());
        }
    }

    public void excluir(int id) throws Exception {
        try {
            dao.excluir(id);
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1451) {
                throw new Exception("Esta conta está sendo usada por gastos e não pode ser excluída.");
            }
            throw new Exception("Erro ao excluir a conta: " + ex.getMessage());
        }
    }
}
