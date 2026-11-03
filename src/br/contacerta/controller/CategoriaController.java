package br.contacerta.controller;

import br.contacerta.dao.CategoriaDAO;
import br.contacerta.model.Categoria;
import java.sql.SQLException;
import java.util.ArrayList;


public class CategoriaController {
    
    private final CategoriaDAO dao = new CategoriaDAO();
    
    public void inserir(int id, String nome, String descricao) throws Exception {      
        if(nome == null){
            nome = "";
        } else {
            nome = nome.trim();
        }
        
        if(descricao == null){
            descricao = "";
        } else {
            descricao = descricao.trim();
        }
        
        
         if (nome.isEmpty()) {
            throw new Exception("Informe o nome da categoria.");
        }
         if (nome.length() > 50) {
            throw new Exception("Passou do limite de caracteres");
        }
         if (descricao.length() > 150) {
            throw new Exception("Passou do limite de caracteres");
        }
       
      Categoria c = new Categoria(0, nome, descricao); 
        try {
            dao.inserir(c);
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1062) {
                throw new Exception("Já existe uma categoria com esse nome.");
            }
            throw new Exception("Erro ao cadastrar a categoria: " + ex.getMessage());
        }
         
        
    }
    
    
    public void alterar(int id, String nome, String descricao) throws Exception {
        
        if(nome == null){
            nome = "";
        } else {
            nome = nome.trim();
        }

        if(descricao == null){
            descricao = "";
        } else {
            descricao = descricao.trim();
        }

        if (nome.isEmpty()) {
            throw new Exception("Informe o nome da categoria.");
        }
        if (nome.length() > 50) {
            throw new Exception("Passou do limite de caracteres");
        }
        if (descricao.length() > 150) {
            throw new Exception("Passou do limite de caracteres");
}
        
       Categoria c = new Categoria(id, nome, descricao); 
        try {
            dao.alterar(c);
        } catch (SQLException ex) {
          if (ex.getErrorCode() == 1062) {
           throw new Exception("Já existe uma categoria com esse nome.");
            }
          throw new Exception("Erro ao alterar a categoria: " + ex.getMessage());
        }
    }
    
    
    public void excluir(int id) throws Exception {
    try {
        dao.excluir(id);
    } catch (SQLException ex) {
        if (ex.getErrorCode() == 1451) {
            throw new Exception("Esta categoria está sendo usada por gastos e não pode ser excluída.");
        }
        throw new Exception("Erro ao excluir a categoria: " + ex.getMessage());
    }
}
    
    public ArrayList<Categoria> listar() throws Exception {
    try {
        return dao.listar();
    } catch (SQLException ex) {
        throw new Exception("Erro ao carregar as categorias: " + ex.getMessage());
    }
}
    
    
    
    
    
}
