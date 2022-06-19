package domain.entities.venda;

import java.util.Arrays;

public enum FormaPagamento {
    CREDITO("Cartão de crédito"),
    DEBITO("Cartão de débito"),
    BOLETO_BANCARIO("Boleto Bancário"),
    PIX("PIX");

    final String text;
    FormaPagamento(String s) {
        this.text = s;
    }

    public static FormaPagamento toEnum(String text) {
        return Arrays.stream(FormaPagamento.values())
                .filter(c -> text.equals(c.toString()))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
