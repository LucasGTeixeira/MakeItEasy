package domain.entities.venda;

import java.util.Arrays;

public enum StatusVenda {
    ENVIADO("Enviado"),
    NAO_ENVIADO("Não enviado"),
    FATURADO("Faturado");

    final String text;

    StatusVenda(String text) {
        this.text = text;
    }

    public static StatusVenda toEnum(String text) {
        return Arrays.stream(StatusVenda.values())
                .filter(c -> text.equals(c.toString()))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
