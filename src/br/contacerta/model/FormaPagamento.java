package br.contacerta.model;

public class FormaPagamento {
    private int idFormaPagamento;
    private String nome;

    public FormaPagamento() {
    }

    public FormaPagamento(int idFormaPagamento, String nome) {
        this.idFormaPagamento = idFormaPagamento;
        this.nome = nome;
    }

    public int getIdFormaPagamento() {
        return idFormaPagamento;
    }

    public void setIdFormaPagamento(int idFormaPagamento) {
        this.idFormaPagamento = idFormaPagamento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    // Usado pelo JComboBox da tela de Gasto: mostra o nome em vez do objeto
    @Override
    public String toString() {
        return nome;
    }
}
