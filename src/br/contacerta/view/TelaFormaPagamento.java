package br.contacerta.view;

import br.contacerta.controller.FormaPagamentoController;
import br.contacerta.model.FormaPagamento;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class TelaFormaPagamento extends JFrame {
    private final FormaPagamentoController controller = new FormaPagamentoController();

    private JTextField txtNome = new JTextField(20);
    private JButton btnNovo = new JButton("Novo");
    private JButton btnSalvar = new JButton("Salvar");
    private JButton btnExcluir = new JButton("Excluir");
    private JTable tabela;
    private DefaultTableModel modelo;

    private int idSelecionado = 0;

    public TelaFormaPagamento() {
        montarTela();
        carregarTabela();
    }

    private void montarTela() {
        setTitle("ContaCerta - Formas de pagamento");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(5, 5));

        modelo = new DefaultTableModel(new Object[]{"Código", "Nome"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabela = new JTable(modelo);
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(tabela), BorderLayout.CENTER);

        JPanel painel = new JPanel(new FlowLayout());
        painel.add(new JLabel("Nome:"));
        painel.add(txtNome);
        painel.add(btnNovo);
        painel.add(btnSalvar);
        painel.add(btnExcluir);
        add(painel, BorderLayout.SOUTH);

        tabela.getSelectionModel().addListSelectionListener(e -> {
            int linha = tabela.getSelectedRow();
            if (!e.getValueIsAdjusting() && linha >= 0) {
                idSelecionado = (int) modelo.getValueAt(linha, 0);
                txtNome.setText((String) modelo.getValueAt(linha, 1));
            }
        });

        btnNovo.addActionListener(e -> limpar());
        btnSalvar.addActionListener(e -> salvar());
        btnExcluir.addActionListener(e -> excluir());

        setSize(600, 400);
        setLocationRelativeTo(null);
    }

    private void carregarTabela() {
        try {
            modelo.setRowCount(0);
            ArrayList<FormaPagamento> lista = controller.listar();
            for (FormaPagamento f : lista) {
                modelo.addRow(new Object[]{f.getIdFormaPagamento(), f.getNome()});
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpar() {
        idSelecionado = 0;
        txtNome.setText("");
        tabela.clearSelection();
        txtNome.requestFocus();
    }

    private void salvar() {
        try {
            if (idSelecionado == 0) {
                controller.inserir(txtNome.getText());
                JOptionPane.showMessageDialog(this, "Forma de pagamento cadastrada com sucesso!");
            } else {
                controller.alterar(idSelecionado, txtNome.getText());
                JOptionPane.showMessageDialog(this, "Forma de pagamento alterada com sucesso!");
            }
            carregarTabela();
            limpar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Atenção", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void excluir() {
        if (idSelecionado == 0) {
            JOptionPane.showMessageDialog(this, "Selecione uma forma de pagamento na tabela para excluir.");
            return;
        }
        int resp = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir esta forma de pagamento?",
                "Confirmar exclusão", JOptionPane.YES_NO_OPTION);
        if (resp != JOptionPane.YES_OPTION) {
            return;
        }
        try {
            controller.excluir(idSelecionado);
            JOptionPane.showMessageDialog(this, "Forma de pagamento excluída com sucesso!");
            carregarTabela();
            limpar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Não foi possível excluir", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new TelaFormaPagamento().setVisible(true));
    }
}
