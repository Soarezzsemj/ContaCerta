package br.contacerta.controller;

import br.contacerta.dao.EstabelecimentoDAO;
import br.contacerta.model.Estabelecimento;
import java.sql.SQLException;
import java.util.ArrayList;

public class EstabelecimentoController {
    private final EstabelecimentoDAO dao = new EstabelecimentoDAO();

    private Estabelecimento montar(int id, String nome, String cidade) throws Exception {
        nome = nome == null ? "" : nome.trim();
        cidade = cidade == null ? "" : cidade.trim();

        if (nome.isEmpty()) {
            throw new Exception("Informe o nome do estabelecimento.");
        }
        if (nome.length() > 80) {
            throw new Exception("O nome pode ter no máximo 80 caracteres.");
        }
        if (cidade.length() > 60) {
            throw new Exception("A cidade pode ter no máximo 60 caracteres.");
        }

        return new Estabelecimento(id, nome, cidade.isEmpty() ? null : cidade);
    }

    public void inserir(String nome, String cidade) throws Exception {
        Estabelecimento e = montar(0, nome, cidade);
        try {
            dao.inserir(e);
        } catch (SQLException ex) {
            throw new Exception("Erro ao cadastrar o estabelecimento: " + ex.getMessage());
        }
    }

    public ArrayList<Estabelecimento> listar() throws Exception {
        try {
            return dao.listar();
        } catch (SQLException ex) {
            throw new Exception("Erro ao listar os estabelecimentos: " + ex.getMessage());
        }
    }

    public void alterar(int id, String nome, String cidade) throws Exception {
        Estabelecimento e = montar(id, nome, cidade);
        try {
            dao.alterar(e);
        } catch (SQLException ex) {
            throw new Exception("Erro ao alterar o estabelecimento: " + ex.getMessage());
        }
    }

    public void excluir(int id) throws Exception {
        try {
            dao.excluir(id);
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1451) {
                throw new Exception("Este estabelecimento está sendo usado por gastos e não pode ser excluído.");
            }
            throw new Exception("Erro ao excluir o estabelecimento: " + ex.getMessage());
        }
    }
}
