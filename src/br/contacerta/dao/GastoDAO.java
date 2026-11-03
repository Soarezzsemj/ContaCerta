package br.contacerta.dao;

import br.contacerta.model.Gasto;
import br.contacerta.model.TotalCategoria;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class GastoDAO extends DAO {

    public void inserir(Gasto g) throws SQLException {
        try {
            abrirBanco();
            String sql = "INSERT INTO gasto (descricao, valor, data_gasto, id_categoria, id_forma_pagamento, id_conta, id_estabelecimento) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";
            pst = con.prepareStatement(sql);
            pst.setString(1, g.getDescricao());
            pst.setBigDecimal(2, g.getValor());
            pst.setDate(3, Date.valueOf(g.getDataGasto()));
            pst.setInt(4, g.getIdCategoria());
            pst.setInt(5, g.getIdFormaPagamento());
            pst.setInt(6, g.getIdConta());
            pst.setInt(7, g.getIdEstabelecimento());
            pst.executeUpdate();
        } finally {
            fecharBanco();
        }
    }

    // SELECT com JOIN
    private static final String SELECT_COM_NOMES = "SELECT g.id_gasto, g.data_gasto, g.descricao, g.valor, "
            + "g.id_categoria, g.id_forma_pagamento, g.id_conta, g.id_estabelecimento, "
            + "c.nome AS categoria, f.nome AS forma_pagamento, ct.nome AS conta, e.nome AS estabelecimento "
            + "FROM gasto g "
            + "INNER JOIN categoria       c  ON c.id_categoria       = g.id_categoria "
            + "INNER JOIN forma_pagamento f  ON f.id_forma_pagamento = g.id_forma_pagamento "
            + "INNER JOIN conta           ct ON ct.id_conta          = g.id_conta "
            + "INNER JOIN estabelecimento e  ON e.id_estabelecimento = g.id_estabelecimento ";

    private static final String ORDEM = "ORDER BY g.data_gasto DESC, g.id_gasto DESC";

    // Listagem completa (tela de Gastos)
    public ArrayList<Gasto> listar() throws SQLException {
        try {
            abrirBanco();
            pst = con.prepareStatement(SELECT_COM_NOMES + ORDEM);
            rs = pst.executeQuery();
            return lerGastos();
        } finally {
            fecharBanco();
        }
    }

    // Converte as linhas do ResultSet (SELECT_COM_NOMES) em objetos Gasto
    private ArrayList<Gasto> lerGastos() throws SQLException {
        ArrayList<Gasto> lista = new ArrayList<>();
        while (rs.next()) {
            Gasto g = new Gasto();
            g.setIdGasto(rs.getInt("id_gasto"));
            g.setDataGasto(rs.getDate("data_gasto").toLocalDate());
            g.setDescricao(rs.getString("descricao"));
            g.setValor(rs.getBigDecimal("valor"));
            g.setIdCategoria(rs.getInt("id_categoria"));
            g.setIdFormaPagamento(rs.getInt("id_forma_pagamento"));
            g.setIdConta(rs.getInt("id_conta"));
            g.setIdEstabelecimento(rs.getInt("id_estabelecimento"));
            g.setNomeCategoria(rs.getString("categoria"));
            g.setNomeFormaPagamento(rs.getString("forma_pagamento"));
            g.setNomeConta(rs.getString("conta"));
            g.setNomeEstabelecimento(rs.getString("estabelecimento"));
            lista.add(g);
        }
        return lista;
    }

    public void alterar(Gasto g) throws SQLException {
        try {
            abrirBanco();
            String sql = "UPDATE gasto SET descricao = ?, valor = ?, data_gasto = ?, id_categoria = ?, "
                    + "id_forma_pagamento = ?, id_conta = ?, id_estabelecimento = ? WHERE id_gasto = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, g.getDescricao());
            pst.setBigDecimal(2, g.getValor());
            pst.setDate(3, Date.valueOf(g.getDataGasto()));
            pst.setInt(4, g.getIdCategoria());
            pst.setInt(5, g.getIdFormaPagamento());
            pst.setInt(6, g.getIdConta());
            pst.setInt(7, g.getIdEstabelecimento());
            pst.setInt(8, g.getIdGasto());
            pst.executeUpdate();
        } finally {
            fecharBanco();
        }
    }

    public void excluir(int id) throws SQLException {
        try {
            abrirBanco();
            String sql = "DELETE FROM gasto WHERE id_gasto = ?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
        } finally {
            fecharBanco();
        }
    }

    // relatorio de total gasto por categoria no periodo
    public ArrayList<TotalCategoria> totalPorCategoria(LocalDate dataInicial, LocalDate dataFinal) throws SQLException {
        ArrayList<TotalCategoria> lista = new ArrayList<>();
        try {
            abrirBanco();
            String sql = "SELECT c.nome AS categoria, COUNT(*) AS quantidade, SUM(g.valor) AS total "
                    + "FROM gasto g "
                    + "INNER JOIN categoria c ON c.id_categoria = g.id_categoria "
                    + "WHERE g.data_gasto BETWEEN ? AND ? "
                    + "GROUP BY c.nome "
                    + "ORDER BY total DESC";
            pst = con.prepareStatement(sql);
            pst.setDate(1, Date.valueOf(dataInicial));
            pst.setDate(2, Date.valueOf(dataFinal));
            rs = pst.executeQuery();
            while (rs.next()) {
                lista.add(new TotalCategoria(rs.getString("categoria"), rs.getInt("quantidade"), rs.getBigDecimal("total")));
            }
        } finally {
            fecharBanco();
        }
        return lista;
    }
}
