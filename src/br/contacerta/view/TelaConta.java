package br.contacerta.view;

import br.contacerta.controller.ContaController;
import br.contacerta.model.Conta;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.math.BigDecimal;
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

public class TelaConta extends JFrame {
    private final ContaController controller = new ContaController();

    private JTextField txtNome = new JTextField(15);
    private JTextField txtBanco = new JTextField(12);
    private JTextField txtSaldo = new JTextField(8);
    private JButton btnNovo = new JButton("Novo");
    private JButton btnSalvar = new JButton("Salvar");
    private JButton btnExcluir = new JButton("Excluir");
    private JTable tabela;
    private DefaultTableModel modelo;

    private int idSelecionado = 0; // 0 = nova conta; diferente de 0 = editando

    public TelaConta() {
        montarTela();
        carregarTabela();
    }

    private void montarTela() {
        setTitle("ContaCerta - Contas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(5, 5));

        modelo = new DefaultTableModel(new Object[]{"Código", "Nome", "Banco", "Saldo inicial"}, 0) {
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
        painel.add(new JLabel("Banco:"));
        painel.add(txtBanco);
        painel.add(new JLabel("Saldo inicial:"));
        painel.add(txtSaldo);
        painel.add(btnNovo);
        painel.add(btnSalvar);
        painel.add(btnExcluir);
        add(painel, BorderLayout.SOUTH);

        // Ao clicar numa linha, preenche os campos para editar
        tabela.getSelectionModel().addListSelectionListener(e -> {
            int linha = tabela.getSelectedRow();
            if (!e.getValueIsAdjusting() && linha >= 0) {
                idSelecionado = (int) modelo.getValueAt(linha, 0);
                txtNome.setText((String) modelo.getValueAt(linha, 1));
                txtBanco.setText((String) modelo.getValueAt(linha, 2));
                txtSaldo.setText((String) modelo.getValueAt(linha, 3));
            }
        });

        btnNovo.addActionListener(e -> limpar());
        btnSalvar.addActionListener(e -> salvar());
        btnExcluir.addActionListener(e -> excluir());

        setSize(800, 400);
        setLocationRelativeTo(null);
    }

    // Saldo com 2 casas decimais e vírgula (ex.: 150,50)
    private String formatarSaldo(BigDecimal saldo) {
        return String.format("%.2f", saldo).replace('.', ',');
    }

    private void carregarTabela() {
        try {
            modelo.setRowCount(0);
            ArrayList<Conta> lista = controller.listar();
            for (Conta c : lista) {
                String banco = c.getBanco() == null ? "" : c.getBanco();
                modelo.addRow(new Object[]{c.getIdConta(), c.getNome(), banco, formatarSaldo(c.getSaldoInicial())});
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpar() {
        idSelecionado = 0;
        txtNome.setText("");
        txtBanco.setText("");
        txtSaldo.setText("");
        tabela.clearSelection();
        txtNome.requestFocus();
    }

    private void salvar() {
        try {
            if (idSelecionado == 0) {
                controller.inserir(txtNome.getText(), txtBanco.getText(), txtSaldo.getText());
                JOptionPane.showMessageDialog(this, "Conta cadastrada com sucesso!");
            } else {
                controller.alterar(idSelecionado, txtNome.getText(), txtBanco.getText(), txtSaldo.getText());
                JOptionPane.showMessageDialog(this, "Conta alterada com sucesso!");
            }
            carregarTabela();
            limpar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Atenção", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void excluir() {
        if (idSelecionado == 0) {
            JOptionPane.showMessageDialog(this, "Selecione uma conta na tabela para excluir.");
            return;
        }
        int resp = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir esta conta?",
                "Confirmar exclusão", JOptionPane.YES_NO_OPTION);
        if (resp != JOptionPane.YES_OPTION) {
            return;
        }
        try {
            controller.excluir(idSelecionado);
            JOptionPane.showMessageDialog(this, "Conta excluída com sucesso!");
            carregarTabela();
            limpar();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Não foi possível excluir", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new TelaConta().setVisible(true));
    }
}
