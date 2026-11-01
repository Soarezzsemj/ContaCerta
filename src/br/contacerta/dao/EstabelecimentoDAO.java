package br.contacerta.dao;

import br.contacerta.model.Estabelecimento;
import java.sql.SQLException;
import java.util.ArrayList;

public class EstabelecimentoDAO extends DAO {
    public void inserir(Estabelecimento e) throws SQLException {
        try {
            abrirBanco();
            String sql = "INSERT INTO estabelecimento (nome, cidade) VALUES (?, ?)";
            pst = con.prepareStatement(sql);
            pst.setString(1, e.getNome());
            pst.setString(2, e.getCidade());
            pst.executeUpdate();
        } finally {
            fecharBanco();
        }
    }

    public ArrayList<Estabelecimento> listar() throws SQLException {
        ArrayList<Estabelecimento> lista = new ArrayList<>();
        try {
            abrirBanco();
            String sql = "SELECT id_estabelecimento, nome, cidade FROM estabelecimento ORDER BY nome";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                Estabelecimento e = new Estabelecimento();
                e.setIdEstabelecimento(rs.getInt("id_estabelecimento"));
                e.setNome(rs.getString("nome"));
                e.setCidade(rs.getString("cidade"));
                lista.add(e);
            }
        } finally {
            fecharBanco();
        }
        return lista;
    }

    public void alterar(Estabelecimento e) throws SQLException {
        try {
            abrirBanco();
            String sql = "UPDATE estabelecimento SET nome = ?, cidade = ? WHERE id_estabelecimento = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, e.getNome());
            pst.setString(2, e.getCidade());
            pst.setInt(3, e.getIdEstabelecimento());
            pst.executeUpdate();
        } finally {
            fecharBanco();
        }
    }

    public void excluir(int id) throws SQLException {
        try {
            abrirBanco();
            String sql = "DELETE FROM estabelecimento WHERE id_estabelecimento = ?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
        } finally {
            fecharBanco();
        }
    }
}
