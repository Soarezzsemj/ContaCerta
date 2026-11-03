package br.contacerta.dao;

import br.contacerta.model.Conta;
import java.sql.SQLException;
import java.util.ArrayList;

public class ContaDAO extends DAO {

    public void inserir(Conta c) throws SQLException {
        try {
            abrirBanco();
            String sql = "INSERT INTO conta (nome, banco, saldo_inicial) VALUES (?, ?, ?)";
            pst = con.prepareStatement(sql);
            pst.setString(1, c.getNome());
            pst.setString(2, c.getBanco()); // se for null, grava NULL no banco
            pst.setBigDecimal(3, c.getSaldoInicial());
            pst.executeUpdate();
        } finally {
            fecharBanco();
        }
    }

    public ArrayList<Conta> listar() throws SQLException {
        ArrayList<Conta> lista = new ArrayList<>();
        try {
            abrirBanco();
            String sql = "SELECT id_conta, nome, banco, saldo_inicial FROM conta ORDER BY nome";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                Conta c = new Conta();
                c.setIdConta(rs.getInt("id_conta"));
                c.setNome(rs.getString("nome"));
                c.setBanco(rs.getString("banco"));
                c.setSaldoInicial(rs.getBigDecimal("saldo_inicial"));
                lista.add(c);
            }
        } finally {
            fecharBanco();
        }
        return lista;
    }

    public void alterar(Conta c) throws SQLException {
        try {
            abrirBanco();
            String sql = "UPDATE conta SET nome = ?, banco = ?, saldo_inicial = ? WHERE id_conta = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, c.getNome());
            pst.setString(2, c.getBanco());
            pst.setBigDecimal(3, c.getSaldoInicial());
            pst.setInt(4, c.getIdConta());
            pst.executeUpdate();
        } finally {
            fecharBanco();
        }
    }

    public void excluir(int id) throws SQLException {
        try {
            abrirBanco();
            String sql = "DELETE FROM conta WHERE id_conta = ?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
        } finally {
            fecharBanco();
        }
    }
}
