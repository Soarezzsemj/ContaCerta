package br.contacerta.controller;
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
    
}
