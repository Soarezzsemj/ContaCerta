package br.contacerta.model;

import java.math.BigDecimal;

public class Conta {
    private int idConta;
    private String nome;
    private String banco;
    private BigDecimal saldoInicial;

    public Conta() {
    }

    public Conta(int idConta, String nome, String banco, BigDecimal saldoInicial) {
        this.idConta = idConta;
        this.nome = nome;
        this.banco = banco;
        this.saldoInicial = saldoInicial;
    }

    public int getIdConta() {
        return idConta;
    }

    public void setIdConta(int idConta) {
        this.idConta = idConta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public BigDecimal getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(BigDecimal saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    // Usado pelo JComboBox da tela de Gasto: mostra o nome em vez do objeto
    @Override
    public String toString() {
        return nome;
    }
}
