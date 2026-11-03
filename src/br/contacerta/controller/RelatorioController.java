package br.contacerta.controller;

import br.contacerta.dao.GastoDAO;
import br.contacerta.model.TotalCategoria;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * RELATÓRIO DE GASTOS  (relatório obrigatório da rotina principal)
 *
 * Gera relatórios em cima da tabela GASTO, que é o CRUD principal.
 * O trabalho exige que o relatório seja da rotina principal, então
 * não vale relatório de categoria ou de conta sozinha.
 *
 * Caminho de uma ação:
 *   TelaRelatorio (view) -> RelatorioController -> GastoDAO (dao) -> banco
 *
 * O que este controller faz:
 *   - Recebe os filtros escolhidos na tela (data inicial, data final, etc.).
 *   - Valida os filtros (ex.: data inicial não pode ser depois da final).
 *   - Chama o GastoDAO para buscar os dados já agrupados/somados.
 *   - Devolve os dados prontos para a tela mostrar numa JTable.
 *
 * Relatórios sugeridos (escolher pelo menos 1):
 *   - Total gasto por categoria em um período   <- recomendado
 *   - Gastos por período (lista com total no final)
 *   - Total gasto por conta
 *   - Total gasto por forma de pagamento
 *
 * Exemplo de resultado esperado (total por categoria, outubro/2026):
 *     Alimentação   R$ 850,00
 *     Transporte    R$ 320,00
 *     Lazer         R$ 150,00
 *     TOTAL         R$ 1.320,00
 *
 * Observação: o SQL do relatório (SUM, GROUP BY, JOIN) fica no GastoDAO.
 * Se preferirem, esse controller pode ser juntado ao GastoController,
 * mas separado fica mais fácil de dividir o trabalho no grupo.
 *
 * NÃO coloque SQL aqui (isso fica no DAO) nem código de tela (isso fica na view).
 */

public class RelatorioController {
    private final GastoDAO dao = new GastoDAO();

    // Relatório escolhido: total gasto por categoria no período (datas no formato dd/mm/aaaa)
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

    // Soma das linhas do relatório (linha "TOTAL GERAL")
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
