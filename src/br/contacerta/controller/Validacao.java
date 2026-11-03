package br.contacerta.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;


final class Validacao {

    //recusa datas que não existem como por ex 31/02/2026
    private static final DateTimeFormatter FORMATO_DATA =
            DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);

    private Validacao() {
    }

    // Converte "dd/mm/aaaa" em localdate
    static LocalDate converterData(String texto, String nomeCampo) throws Exception {
        texto = texto == null ? "" : texto.trim();
        if (texto.isEmpty()) {
            throw new Exception("Informe a " + nomeCampo + ".");
        }
        try {
            return LocalDate.parse(texto, FORMATO_DATA);
        } catch (DateTimeParseException ex) {
            throw new Exception("A " + nomeCampo + " \"" + texto + "\" é inválida. Use o formato dd/mm/aaaa (ex.: 25/10/2026).");
        }
    }

    // erro tecnico que aparece no console
    static Exception erroBanco(Exception ex, String acao) {
        System.out.println("Erro técnico ao " + acao + ": " + ex);
        if (ex instanceof SQLException) {
            return new Exception("Não foi possível " + acao + ". Verifique os dados e tente novamente.");
        }
        // DAO.abrirBanco() abre um RuntimeException quando não consegue conectar
        return new Exception("Não foi possível " + acao + ": sem conexão com o banco de dados. "
                + "Verifique se o MySQL está rodando.");
    }
}
