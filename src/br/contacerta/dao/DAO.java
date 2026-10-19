package br.contacerta.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe base de acesso ao banco.
 * As outras classes DAO (CategoriaDAO, GastoDAO) herdam dela
 * e usam con, pst e rs para executar os comandos SQL.
 */
public class DAO {

    protected Connection con;        // conexão com o banco
    protected PreparedStatement pst; // comando SQL preparado
    protected ResultSet rs;          // resultado de um SELECT

    // Dados de acesso ao banco: ajuste para o seu MySQL
    private static final String URL   = "jdbc:mysql://localhost:3307/contacerta";
    private static final String USER  = "root";
    private static final String SENHA = "root";

    public void abrirBanco() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // carrega o driver (Connector/J 8.x)
            con = DriverManager.getConnection(URL, USER, SENHA);
            System.out.println("Conectado ao banco de dados");
        } catch (ClassNotFoundException ex) {
            System.out.println("Classe não encontrada, adicione o driver nas bibliotecas.");
            throw new RuntimeException("Driver MySQL não encontrado. Adicione o .jar em Libraries.", ex);
        } catch (SQLException ex) {
            System.out.println(ex);
            throw new RuntimeException("Erro ao conectar ao banco: " + ex.getMessage(), ex);
        }
    }

    public void fecharBanco() {
        try {
            if (rs != null)  rs.close();   // fecha o resultado
            if (pst != null) pst.close();  // fecha o comando
            if (con != null) con.close();  // fecha a conexão
            System.out.println("Execução da Query fechada\n");
        } catch (SQLException ex) {
            System.out.println("Erro ao fechar o banco: " + ex.getMessage());
        }
    }
}
