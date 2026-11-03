package br.contacerta.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;


public class TelaPrincipal extends JFrame {

    private static final String INICIO = "Início";

    private final CardLayout cartoes = new CardLayout();
    private final JPanel conteudo = new JPanel(cartoes);
    private final TelaInicio telaInicio;
    private final Map<String, Supplier<JPanel>> telas = new LinkedHashMap<>();
    private final Map<String, JButton> botoesMenu = new LinkedHashMap<>();
    private final Map<String, Component> abertas = new LinkedHashMap<>();

    public TelaPrincipal() {
        setTitle("ContaCerta");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        telas.put("Gastos", TelaGasto::new);
        telas.put("Relatório", TelaRelatorio::new);
        telas.put("Categorias", TelaCategoria::new);
        telas.put("Formas de pagamento", TelaFormaPagamento::new);
        telas.put("Contas", TelaConta::new);
        telas.put("Estabelecimentos", TelaEstabelecimento::new);

        telaInicio = new TelaInicio(() -> mostrar("Gastos"));
        conteudo.add(comMargem(telaInicio), INICIO);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(criarMenu(), BorderLayout.WEST);
        getContentPane().add(conteudo, BorderLayout.CENTER);

        setMinimumSize(new Dimension(1200, 700));
        setSize(1280, 780);
        setLocationRelativeTo(null);

        mostrar(INICIO);
    }

    public void mostrar(String nome) {
        if (INICIO.equals(nome)) {
            telaInicio.carregarDados();
        } else {
            Component antiga = abertas.remove(nome);
            if (antiga != null) {
                conteudo.remove(antiga);
            }
            Component nova = comBarraVoltar(nome, telas.get(nome).get());
            abertas.put(nome, nova);
            conteudo.add(nova, nome);
        }
        cartoes.show(conteudo, nome);
        setTitle(INICIO.equals(nome) ? "ContaCerta" : "ContaCerta - " + nome);
        botoesMenu.forEach((n, b) -> Estilo.marcarAtivo(b, n.equals(nome)));
    }

    //menu lateral
    private JPanel criarMenu() {
        JPanel menu = new JPanel();
        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
        menu.setBackground(Estilo.COR_MENU);
        menu.setPreferredSize(new Dimension(220, 0));
        menu.setBorder(BorderFactory.createEmptyBorder(22, 0, 16, 0));

        JLabel lblNome = new JLabel("ContaCerta");
        lblNome.setFont(lblNome.getFont().deriveFont(Font.BOLD, 22f));
        lblNome.setForeground(Color.WHITE);
        JLabel lblSubtitulo = new JLabel("Controle de gastos pessoais");
        lblSubtitulo.setForeground(new Color(180, 200, 220));
        for (JLabel l : new JLabel[]{lblNome, lblSubtitulo}) {
            l.setBorder(BorderFactory.createEmptyBorder(0, 20, 2, 10));
            l.setAlignmentX(Component.LEFT_ALIGNMENT);
            menu.add(l);
        }
        menu.add(Box.createVerticalStrut(22));

        adicionarItemMenu(menu, INICIO, "⌂  Início");
        adicionarItemMenu(menu, "Gastos", "Gastos");
        adicionarItemMenu(menu, "Relatório", "Relatório");

        JLabel lblCadastros = new JLabel("CADASTROS");
        lblCadastros.setFont(lblCadastros.getFont().deriveFont(Font.BOLD, 11f));
        lblCadastros.setForeground(new Color(150, 175, 200));
        lblCadastros.setBorder(BorderFactory.createEmptyBorder(18, 20, 6, 10));
        lblCadastros.setAlignmentX(Component.LEFT_ALIGNMENT);
        menu.add(lblCadastros);

        adicionarItemMenu(menu, "Categorias", "Categorias");
        adicionarItemMenu(menu, "Formas de pagamento", "Formas de pagamento");
        adicionarItemMenu(menu, "Contas", "Contas");
        adicionarItemMenu(menu, "Estabelecimentos", "Estabelecimentos");

        menu.add(Box.createVerticalGlue());
        return menu;
    }

    private void adicionarItemMenu(JPanel menu, String nome, String texto) {
        JButton botao = Estilo.criarBotaoMenu(texto);
        botao.addActionListener(e -> mostrar(nome));
        botoesMenu.put(nome, botao);
        menu.add(botao);
    }

    //moldura das telas

    private JPanel comBarraVoltar(String nome, JPanel tela) {
        JButton btnVoltar = Estilo.criarBotao("←  Voltar ao Início");
        btnVoltar.setToolTipText("Voltar para o resumo dos últimos 30 dias");
        btnVoltar.addActionListener(e -> mostrar(INICIO));

        JLabel lblTrilha = new JLabel("Início  ›  " + nome);
        Estilo.dica(lblTrilha);
        lblTrilha.setBorder(BorderFactory.createEmptyBorder(0, 14, 0, 0));

        JPanel barra = new JPanel(new BorderLayout());
        barra.setOpaque(false);
        barra.add(btnVoltar, BorderLayout.WEST);
        barra.add(lblTrilha, BorderLayout.CENTER);
        barra.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(210, 218, 228)),
                BorderFactory.createEmptyBorder(0, 0, 10, 0)));

        JPanel painel = new JPanel(new BorderLayout(0, 6));
        painel.add(barra, BorderLayout.NORTH);
        painel.add(tela, BorderLayout.CENTER);
        return comMargem(painel);
    }

    private static JPanel comMargem(JPanel painel) {
        painel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(16, 20, 16, 20), painel.getBorder()));
        return painel;
    }

    public static void main(String[] args) {
        Estilo.aplicarTema();
        java.awt.EventQueue.invokeLater(() -> new TelaPrincipal().setVisible(true));
    }
}
