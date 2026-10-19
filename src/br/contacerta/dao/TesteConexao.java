package br.contacerta.dao;

/**
 *
 * @author Soarezz
 */
public class TesteConexao {
    
    public static void main(String[] args) {
        // arquivo de teste p ver se ta conectando no banco toptop
    DAO dao = new DAO();
    dao.abrirBanco();
    dao.fecharBanco();
    }
}
