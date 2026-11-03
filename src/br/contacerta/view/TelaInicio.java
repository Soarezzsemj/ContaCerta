package br.contacerta.view;

import br.contacerta.controller.GastoController;
import br.contacerta.model.Gasto;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class TelaInicio extends JPanel {
    private final GastoController controller = new GastoController();

    private final DefaultTableModel modelo = new DefaultTableModel(new Object[]{"Data", "Descrição", "Categoria", "Valor"}, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    private final JTable tabela = new JTable(modelo);
    private final JLabel lblPeriodo = new JLabel();
    private final JLabel lblCartaoTotal = new JLabel("-");
    private final JLabel lblCartaoQuantidade = new JLabel("-");
    private final JLabel lblCartaoPeriodo = new JLabel("-");
    private final JLabel lblTotalRodape = new JLabel();
    private final JLabel lblVazio = new JLabel("Nenhum gasto registrado nos últimos 30 dias.");

    public TelaInicio(Runnable abrirGastos) {
        setLayout(new BorderLayout(0, 14));

        // título, período e botões 
        JLabel lblTitulo = new JLabel("Resumo dos últimos 30 dias");
        Estilo.titulo(lblTitulo);
        Estilo.dica(lblPeriodo);

        JPanel textos = new JPanel(new GridLayout(2, 1, 0, 2));
        textos.setOpaque(false);
        textos.add(lblTitulo);
        textos.add(lblPeriodo);

        JButton btnNovoGasto = Estilo.criarBotao("✚ Novo gasto");
        btnNovoGasto.addActionListener(e -> abrirGastos.run());
        JButton btnAtualizar = Estilo.criarBotao("↻ Atualizar");
        btnAtualizar.addActionListener(e -> carregarDados());

        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        botoes.setOpaque(false);
        botoes.add(btnAtualizar);
        botoes.add(btnNovoGasto);

        JPanel cabecalho = new JPanel(new BorderLayout());
        cabecalho.setOpaque(false);
        cabecalho.add(textos, BorderLayout.WEST);
        cabecalho.add(botoes, BorderLayout.EAST);

        //resumo
        JPanel cartoes = new JPanel(new GridLayout(1, 3, 14, 0));
        cartoes.setOpaque(false);
        cartoes.add(Estilo.criarCartao("Total gasto no período", lblCartaoTotal));
        cartoes.add(Estilo.criarCartao("Gastos registrados", lblCartaoQuantidade));
        cartoes.add(Estilo.criarCartao("Período", lblCartaoPeriodo));

        JPanel norte = new JPanel(new BorderLayout(0, 14));
        norte.setOpaque(false);
        norte.add(cabecalho, BorderLayout.NORTH);
        norte.add(cartoes, BorderLayout.CENTER);

        //tabela 
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        Estilo.estilizarTabela(tabela);
        Estilo.alinharDireita(tabela, 3); // valor
        JScrollPane scroll = new JScrollPane(tabela);

        lblVazio.setHorizontalAlignment(SwingConstants.CENTER);
        Estilo.dica(lblVazio);
        lblVazio.setVisible(false);

        JPanel centro = new JPanel(new BorderLayout(0, 6));
        centro.setOpaque(false);
        centro.add(scroll, BorderLayout.CENTER);
        centro.add(lblVazio, BorderLayout.SOUTH);

        //rodape
        lblTotalRodape.setHorizontalAlignment(SwingConstants.RIGHT);
        lblTotalRodape.setFont(lblTotalRodape.getFont().deriveFont(Font.BOLD, 15f));
        lblTotalRodape.setForeground(Estilo.COR_PRIMARIA);
        lblTotalRodape.setBorder(BorderFactory.createEmptyBorder(4, 0, 0, 8));

        add(norte, BorderLayout.NORTH);
        add(centro, BorderLayout.CENTER);
        add(lblTotalRodape, BorderLayout.SOUTH);
    }

    public void carregarDados() {
        LocalDate inicio = controller.inicioUltimoMes();
        LocalDate hoje = LocalDate.now();
        String periodo = Estilo.formatarData(inicio) + " a " + Estilo.formatarData(hoje);
        lblPeriodo.setText("Gastos de " + periodo + ", do mais recente para o mais antigo");
        lblCartaoPeriodo.setText(periodo);

        modelo.setRowCount(0);
        try {
            ArrayList<Gasto> gastos = controller.listarUltimoMes();
            for (Gasto g : gastos) {
                modelo.addRow(new Object[]{Estilo.formatarData(g.getDataGasto()), g.getDescricao(),
                    g.getNomeCategoria(), Estilo.formatarMoeda(g.getValor())});
            }
            String total = Estilo.formatarMoeda(controller.somarValores(gastos));
            lblCartaoTotal.setText(total);
            lblCartaoQuantidade.setText(String.valueOf(gastos.size()));
            lblTotalRodape.setText("Total no período: " + total);
            lblVazio.setVisible(gastos.isEmpty());
            Estilo.ajustarColunas(tabela);
        } catch (Exception ex) {
            lblCartaoTotal.setText("-");
            lblCartaoQuantidade.setText("-");
            lblTotalRodape.setText("");
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
