package br.contacerta.controller;

import br.contacerta.dao.CategoriaDAO;
import br.contacerta.dao.ContaDAO;
import br.contacerta.dao.EstabelecimentoDAO;
import br.contacerta.dao.FormaPagamentoDAO;
import br.contacerta.dao.GastoDAO;
import br.contacerta.model.Categoria;
import br.contacerta.model.Conta;
import br.contacerta.model.Estabelecimento;
import br.contacerta.model.FormaPagamento;
import br.contacerta.model.Gasto;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class GastoController {
    private final GastoDAO dao = new GastoDAO();


    private Gasto montar(int id, String descricao, String valorTexto, String dataTexto, Categoria categoria,
            FormaPagamento formaPagamento, Conta conta, Estabelecimento estabelecimento) throws Exception {
        descricao = descricao == null ? "" : descricao.trim();
        valorTexto = valorTexto == null ? "" : valorTexto.trim();

        if (descricao.isEmpty()) {
            throw new Exception("Informe a descrição do gasto.");
        }
        if (descricao.length() > 150) {
            throw new Exception("A descrição pode ter no máximo 150 caracteres.");
        }

        if (valorTexto.isEmpty()) {
            throw new Exception("Informe o valor do gasto.");
        }
        // aceita virgula ou ponto
        valorTexto = valorTexto.replace(',', '.');
   
        if (!valorTexto.matches("\\d{1,8}(\\.\\d{1,2})?")) {
            throw new Exception("Valor inválido. Use apenas números, com até 2 casas decimais (ex.: 45,90).");
        }
        BigDecimal valor = new BigDecimal(valorTexto);
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new Exception("O valor do gasto deve ser maior que zero.");
        }

        LocalDate data = Validacao.converterData(dataTexto, "data do gasto");

        if (categoria == null) {
            throw new Exception("Selecione a categoria.");
        }
        if (formaPagamento == null) {
            throw new Exception("Selecione a forma de pagamento.");
        }
        if (conta == null) {
            throw new Exception("Selecione a conta.");
        }
        if (estabelecimento == null) {
            throw new Exception("Selecione o estabelecimento.");
        }

        return new Gasto(id, descricao, valor, data, categoria.getIdCategoria(),
                formaPagamento.getIdFormaPagamento(), conta.getIdConta(), estabelecimento.getIdEstabelecimento());
    }

    public void inserir(String descricao, String valor, String data, Categoria categoria,
            FormaPagamento formaPagamento, Conta conta, Estabelecimento estabelecimento) throws Exception {
        Gasto g = montar(0, descricao, valor, data, categoria, formaPagamento, conta, estabelecimento);
        try {
            dao.inserir(g);
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1452) {
                throw new Exception("A categoria, forma de pagamento, conta ou estabelecimento escolhido não existe mais. Clique em Novo para recarregar as listas.");
            }
            throw Validacao.erroBanco(ex, "cadastrar o gasto");
        } catch (RuntimeException ex) {
            throw Validacao.erroBanco(ex, "cadastrar o gasto");
        }
    }

    public ArrayList<Gasto> listar() throws Exception {
        try {
            return dao.listar();
        } catch (SQLException | RuntimeException ex) {
            throw Validacao.erroBanco(ex, "listar os gastos");
        }
    }

    public void alterar(int id, String descricao, String valor, String data, Categoria categoria,
            FormaPagamento formaPagamento, Conta conta, Estabelecimento estabelecimento) throws Exception {
        Gasto g = montar(id, descricao, valor, data, categoria, formaPagamento, conta, estabelecimento);
        try {
            dao.alterar(g);
        } catch (SQLException ex) {
            if (ex.getErrorCode() == 1452) {
                throw new Exception("A categoria, forma de pagamento, conta ou estabelecimento escolhido não existe mais. Clique em Novo para recarregar as listas.");
            }
            throw Validacao.erroBanco(ex, "alterar o gasto");
        } catch (RuntimeException ex) {
            throw Validacao.erroBanco(ex, "alterar o gasto");
        }
    }

    public void excluir(int id) throws Exception {
        try {
            dao.excluir(id);
        } catch (SQLException | RuntimeException ex) {
            throw Validacao.erroBanco(ex, "excluir o gasto");
        }
    }

    public ArrayList<Categoria> listarCategorias() throws Exception {
        try {
            return new CategoriaDAO().listar();
        } catch (SQLException | RuntimeException ex) {
            throw Validacao.erroBanco(ex, "carregar as categorias");
        }
    }

    public ArrayList<FormaPagamento> listarFormasPagamento() throws Exception {
        try {
            return new FormaPagamentoDAO().listar();
        } catch (SQLException | RuntimeException ex) {
            throw Validacao.erroBanco(ex, "carregar as formas de pagamento");
        }
    }

    public ArrayList<Conta> listarContas() throws Exception {
        try {
            return new ContaDAO().listar();
        } catch (SQLException | RuntimeException ex) {
            throw Validacao.erroBanco(ex, "carregar as contas");
        }
    }

    public ArrayList<Estabelecimento> listarEstabelecimentos() throws Exception {
        try {
            return new EstabelecimentoDAO().listar();
        } catch (SQLException | RuntimeException ex) {
            throw Validacao.erroBanco(ex, "carregar os estabelecimentos");
        }
    }
}
