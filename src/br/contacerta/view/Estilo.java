package br.contacerta.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public final class Estilo {

    public static final Color COR_PRIMARIA = new Color(41, 82, 122);
    private static final Color COR_ZEBRA = new Color(240, 244, 250);
    private static final Color COR_LINHA_CABECALHO = new Color(28, 60, 92);
    private static final Font FONTE_PADRAO = new Font(Font.SANS_SERIF, Font.PLAIN, 13);
    public static final Color COR_MENU = new Color(30, 58, 88);
    private static final Color COR_MENU_HOVER = new Color(45, 80, 115);
    private static final Color COR_MENU_ATIVO = new Color(70, 120, 170);
    private static final Color COR_BORDA = new Color(210, 218, 228);
    public static final Dimension TAMANHO_BOTAO = new Dimension(170, 32);
    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final NumberFormat FORMATO_MOEDA = NumberFormat.getCurrencyInstance(Locale.of("pt", "BR"));

    private Estilo() {
    }

    public static void aplicarTema() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            
            System.out.println("Não foi possível aplicar o tema Nimbus: " + ex.getMessage());
        }
        UIManager.getLookAndFeelDefaults().put("defaultFont", FONTE_PADRAO);
        UIManager.put("Table.alternateRowColor", COR_ZEBRA); 
    }



    public static void titulo(JLabel rotulo) {
        rotulo.setFont(rotulo.getFont().deriveFont(Font.BOLD, 20f));
        rotulo.setForeground(COR_PRIMARIA);
    }


    public static void dica(JLabel rotulo) {
        rotulo.setForeground(Color.GRAY);
    }

    public static void estilizarTabela(JTable tabela) {
        tabela.setRowHeight(26);
        tabela.setShowGrid(false);
        tabela.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tabela.setFillsViewportHeight(true);
        tabela.setDefaultRenderer(Object.class, new RenderizadorCelula(SwingConstants.LEFT));

        JTableHeader cabecalho = tabela.getTableHeader();
        cabecalho.setReorderingAllowed(false);
        cabecalho.setDefaultRenderer(new RenderizadorCabecalho());
    }


    public static void alinharDireita(JTable tabela, int coluna) {
        tabela.getColumnModel().getColumn(coluna).setCellRenderer(new RenderizadorCelula(SwingConstants.RIGHT));
    }


    public static void negritoUltimaLinha(JTable tabela) {
        for (int c = 0; c < tabela.getColumnCount(); c++) {
            TableColumn coluna = tabela.getColumnModel().getColumn(c);
            TableCellRenderer base = coluna.getCellRenderer() != null ? coluna.getCellRenderer() : tabela.getDefaultRenderer(Object.class);
            coluna.setCellRenderer((t, valor, selecionado, foco, linha, col) -> {
                Component celula = base.getTableCellRendererComponent(t, valor, selecionado, foco, linha, col);
                if (linha == t.getRowCount() - 1) {
                    celula.setFont(t.getFont().deriveFont(Font.BOLD));
                }
                return celula;
            });
        }
    }

    
    public static void ajustarColunas(JTable tabela) {
        for (int c = 0; c < tabela.getColumnCount(); c++) {
            TableColumn coluna = tabela.getColumnModel().getColumn(c);
            Component cabecalho = tabela.getTableHeader().getDefaultRenderer()
                    .getTableCellRendererComponent(tabela, coluna.getHeaderValue(), false, false, -1, c);
            int largura = cabecalho.getPreferredSize().width;
            for (int l = 0; l < tabela.getRowCount(); l++) {
                Component celula = tabela.prepareRenderer(tabela.getCellRenderer(l, c), l, c);
                largura = Math.max(largura, celula.getPreferredSize().width);
            }
            largura += 10;
            coluna.setMinWidth(15);                 
            coluna.setMaxWidth(Integer.MAX_VALUE);
            coluna.setPreferredWidth(largura);
            if (largura <= 120) {
                coluna.setMinWidth(largura);
            }
            if ("Código".equals(coluna.getHeaderValue())) {
                coluna.setMaxWidth(largura);
            }
        }
    }

    // ===== Navegacao da TelaPrincipal=====

    // Botao do menu lateral q é fundo escuro, texto branco, destaca no hover e quando é a aba atual
    public static JButton criarBotaoMenu(String texto) {
        JButton botao = new JButton(texto) {
            @Override
            protected void paintComponent(Graphics g) {
                boolean ativo = Boolean.TRUE.equals(getClientProperty("ativo"));
                g.setColor(ativo ? COR_MENU_ATIVO : getModel().isRollover() ? COR_MENU_HOVER : COR_MENU);
                g.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }
        };
        botao.setContentAreaFilled(false);
        botao.setBorderPainted(false);
        botao.setFocusPainted(false);
        botao.setOpaque(false);
        botao.setForeground(Color.WHITE);
        botao.setFont(FONTE_PADRAO.deriveFont(14f));
        botao.setHorizontalAlignment(SwingConstants.LEFT);
        botao.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        botao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        botao.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        botao.setAlignmentX(Component.LEFT_ALIGNMENT);
        return botao;
    }

    public static void marcarAtivo(JButton botao, boolean ativo) {
        botao.putClientProperty("ativo", ativo);
        botao.setFont(botao.getFont().deriveFont(ativo ? Font.BOLD : Font.PLAIN));
        botao.repaint();
    }

    
    public static JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);
        botao.setFont(FONTE_PADRAO.deriveFont(Font.BOLD));
        botao.setPreferredSize(TAMANHO_BOTAO);
        botao.setFocusPainted(false);
        botao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return botao;
    }

    public static JPanel criarCartao(String titulo, JLabel valor) {
        JPanel cartao = new JPanel(new BorderLayout(0, 4));
        cartao.setBackground(Color.WHITE);
        cartao.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(0, 4, 0, 0, COR_PRIMARIA),
                        BorderFactory.createMatteBorder(1, 0, 1, 1, COR_BORDA)),
                BorderFactory.createEmptyBorder(10, 14, 10, 14)));
        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setForeground(Color.GRAY);
        valor.setFont(FONTE_PADRAO.deriveFont(Font.BOLD, 20f));
        valor.setForeground(COR_PRIMARIA);
        cartao.add(lblTitulo, BorderLayout.NORTH);
        cartao.add(valor, BorderLayout.CENTER);
        return cartao;
    }

    public static String formatarData(LocalDate data) {
        return data == null ? "" : data.format(FORMATO_DATA);
    }

    public static String formatarMoeda(BigDecimal valor) {
        return valor == null ? "" : FORMATO_MOEDA.format(valor);
    }

   
    private static class RenderizadorCelula extends DefaultTableCellRenderer {
        RenderizadorCelula(int alinhamento) {
            setHorizontalAlignment(alinhamento);
        }

        @Override
        public Component getTableCellRendererComponent(JTable tabela, Object valor, boolean selecionado,
                boolean foco, int linha, int coluna) {
            super.getTableCellRendererComponent(tabela, valor, selecionado, foco, linha, coluna);
            setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 8));
            return this;
        }
    }

    private static class RenderizadorCabecalho extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable tabela, Object valor, boolean selecionado,
                boolean foco, int linha, int coluna) {
            super.getTableCellRendererComponent(tabela, valor, false, false, linha, coluna);
            setOpaque(true);
            setBackground(COR_PRIMARIA);
            setForeground(Color.WHITE);
            setFont(tabela.getFont().deriveFont(Font.BOLD));
            setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(0, 0, 0, 1, COR_LINHA_CABECALHO),
                    BorderFactory.createEmptyBorder(6, 8, 6, 8)));
            return this;
        }
    }
}
