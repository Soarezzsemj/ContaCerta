package br.contacerta.model;

import java.math.BigDecimal;

public class TotalCategoria {
    private String categoria;
    private int quantidade;
    private BigDecimal total;

    public TotalCategoria() {
    }

    public TotalCategoria(String categoria, int quantidade, BigDecimal total) {
        this.categoria = categoria;
        this.quantidade = quantidade;
        this.total = total;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
