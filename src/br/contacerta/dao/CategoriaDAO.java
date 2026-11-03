package br.contacerta.dao;


import br.contacerta.model.Categoria;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author Soarezz
 */
public class CategoriaDAO extends DAO {
    
    public void inserir (Categoria c) throws SQLException {
        
        try{
            abrirBanco();
            String sql = "INSERT INTO categoria (nome, descricao) VALUES (?, ?)";
            pst = con.prepareStatement(sql);
            pst.setString(1, c.getNome());
            pst.setString(2, c.getDescricao());
            pst.executeUpdate();
        }finally {
            fecharBanco();
        }
    }
    
    public ArrayList<Categoria> listar() throws SQLException { 
     ArrayList<Categoria> lista = new ArrayList<>();
        try {
            abrirBanco();
            String sql = "SELECT id_categoria, nome, descricao FROM categoria ORDER BY nome";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                Categoria c = new Categoria();
                c.setIdCategoria(rs.getInt("id_categoria"));
                c.setNome(rs.getString("nome"));
                c.setDescricao(rs.getString("descricao"));
                lista.add(c);
            }
        } finally {
            fecharBanco();
        }
        return lista;
    }
    
    public void alterar(Categoria c) throws SQLException {
        try {
            abrirBanco();
            String sql = "UPDATE categoria SET nome = ?, descricao = ? WHERE id_categoria = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, c.getNome());
            pst.setString(2, c.getDescricao());
            pst.setInt(3, c.getIdCategoria());
            pst.executeUpdate();
        } finally {
            fecharBanco();
        }
    }
    
    public void excluir(int id) throws SQLException {
        try {
            abrirBanco();
            String sql = "DELETE FROM categoria WHERE id_categoria = ?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
        } finally {
            fecharBanco();
        }
    
    }
    
    
    
}
