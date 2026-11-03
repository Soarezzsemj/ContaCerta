package br.contacerta.view;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

// formatacao de datas e valores para exibir nas telas
public final class Formato {

    // mesmo formato que Validacao.converterData() espera ao ler a data digitada
    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final NumberFormat FORMATO_MOEDA = NumberFormat.getCurrencyInstance(Locale.of("pt", "BR"));

    private Formato() {
    }

    public static String formatarData(LocalDate data) {
        return data == null ? "" : data.format(FORMATO_DATA);
    }

    public static String formatarMoeda(BigDecimal valor) {
        return valor == null ? "" : FORMATO_MOEDA.format(valor);
    }
}
