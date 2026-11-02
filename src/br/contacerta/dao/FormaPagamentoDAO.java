package br.contacerta.dao;

import br.contacerta.model.FormaPagamento;
import java.sql.SQLException;
import java.util.ArrayList;

public class FormaPagamentoDAO extends DAO {

    public void inserir(FormaPagamento f) throws SQLException {
        try {
            abrirBanco();
            String sql = "INSERT INTO forma_pagamento (nome) VALUES (?)";
            pst = con.prepareStatement(sql);
            pst.setString(1, f.getNome());
            pst.executeUpdate();
        } finally {
            fecharBanco();
        }
    }

    public ArrayList<FormaPagamento> listar() throws SQLException {
        ArrayList<FormaPagamento> lista = new ArrayList<>();
        try {
            abrirBanco();
            String sql = "SELECT id_forma_pagamento, nome FROM forma_pagamento ORDER BY nome";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                FormaPagamento f = new FormaPagamento();
                f.setIdFormaPagamento(rs.getInt("id_forma_pagamento"));
                f.setNome(rs.getString("nome"));
                lista.add(f);
            }
        } finally {
            fecharBanco();
        }
        return lista;
    }

    public void alterar(FormaPagamento f) throws SQLException {
        try {
            abrirBanco();
            String sql = "UPDATE forma_pagamento SET nome = ? WHERE id_forma_pagamento = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, f.getNome());
            pst.setInt(2, f.getIdFormaPagamento());
            pst.executeUpdate();
        } finally {
            fecharBanco();
        }
    }

    public void excluir(int id) throws SQLException {
        try {
            abrirBanco();
            String sql = "DELETE FROM forma_pagamento WHERE id_forma_pagamento = ?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
        } finally {
            fecharBanco();
        }
    }
}
