package br.contacerta.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Gasto {
    private int idGasto;
    private String descricao;
    private BigDecimal valor;
    private LocalDate dataGasto;
    private int idCategoria;
    private int idFormaPagamento;
    private int idConta;
    private int idEstabelecimento;

    // JOIN da listagem usados so p exibir na tela
    private String nomeCategoria;
    private String nomeFormaPagamento;
    private String nomeConta;
    private String nomeEstabelecimento;

    public Gasto() {
    }

    public Gasto(int idGasto, String descricao, BigDecimal valor, LocalDate dataGasto,
            int idCategoria, int idFormaPagamento, int idConta, int idEstabelecimento) {
        this.idGasto = idGasto;
        this.descricao = descricao;
        this.valor = valor;
        this.dataGasto = dataGasto;
        this.idCategoria = idCategoria;
        this.idFormaPagamento = idFormaPagamento;
        this.idConta = idConta;
        this.idEstabelecimento = idEstabelecimento;
    }

    public int getIdGasto() {
        return idGasto;
    }

    public void setIdGasto(int idGasto) {
        this.idGasto = idGasto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDate getDataGasto() {
        return dataGasto;
    }

    public void setDataGasto(LocalDate dataGasto) {
        this.dataGasto = dataGasto;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getIdFormaPagamento() {
        return idFormaPagamento;
    }

    public void setIdFormaPagamento(int idFormaPagamento) {
        this.idFormaPagamento = idFormaPagamento;
    }

    public int getIdConta() {
        return idConta;
    }

    public void setIdConta(int idConta) {
        this.idConta = idConta;
    }

    public int getIdEstabelecimento() {
        return idEstabelecimento;
    }

    public void setIdEstabelecimento(int idEstabelecimento) {
        this.idEstabelecimento = idEstabelecimento;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public String getNomeFormaPagamento() {
        return nomeFormaPagamento;
    }

    public void setNomeFormaPagamento(String nomeFormaPagamento) {
        this.nomeFormaPagamento = nomeFormaPagamento;
    }

    public String getNomeConta() {
        return nomeConta;
    }

    public void setNomeConta(String nomeConta) {
        this.nomeConta = nomeConta;
    }

    public String getNomeEstabelecimento() {
        return nomeEstabelecimento;
    }

    public void setNomeEstabelecimento(String nomeEstabelecimento) {
        this.nomeEstabelecimento = nomeEstabelecimento;
    }
}
