package br.contacerta.model;

public class Estabelecimento {
    private int idEstabelecimento;
    private String nome;
    private String cidade;

    public Estabelecimento() {
    }

    public Estabelecimento(int idEstabelecimento, String nome, String cidade) {
        this.idEstabelecimento = idEstabelecimento;
        this.nome = nome;
        this.cidade = cidade;
    }

    public int getIdEstabelecimento() {
        return idEstabelecimento;
    }

    public void setIdEstabelecimento(int idEstabelecimento) {
        this.idEstabelecimento = idEstabelecimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    // mostra o nome em vez do objeto
    @Override
    public String toString() {
        return nome;
    }
}
