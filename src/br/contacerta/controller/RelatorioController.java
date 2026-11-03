package br.contacerta.controller;

import br.contacerta.dao.GastoDAO;
import br.contacerta.model.TotalCategoria;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;



public class RelatorioController {
    private final GastoDAO dao = new GastoDAO();

    // Relatório escolhido total gasto por categoria no periodo com datas 
    public ArrayList<TotalCategoria> totalPorCategoria(String dataInicial, String dataFinal) throws Exception {
        LocalDate inicio = Validacao.converterData(dataInicial, "data inicial");
        LocalDate fim = Validacao.converterData(dataFinal, "data final");
        if (inicio.isAfter(fim)) {
            throw new Exception("A data inicial (" + dataInicial.trim() + ") não pode ser maior que a data final ("
                    + dataFinal.trim() + ").");
        }
        try {
            return dao.totalPorCategoria(inicio, fim);
        } catch (SQLException | RuntimeException ex) {
            throw Validacao.erroBanco(ex, "gerar o relatório");
        }
    }

    // soma das linhas do relatorio
    public TotalCategoria totalGeral(ArrayList<TotalCategoria> totais) {
        int quantidade = 0;
        BigDecimal total = BigDecimal.ZERO;
        for (TotalCategoria t : totais) {
            quantidade += t.getQuantidade();
            total = total.add(t.getTotal());
        }
        return new TotalCategoria("TOTAL GERAL", quantidade, total);
    }
}
