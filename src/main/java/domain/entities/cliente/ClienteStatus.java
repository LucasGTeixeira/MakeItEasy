package domain.entities.cliente;

import java.util.Arrays;

public enum ClienteStatus {
    ATIVO("Ativo"),
    INATIVO("Inativo");

    final String text;

    ClienteStatus(String text) {
        this.text = text;
    }

    public static ClienteStatus toEnum(String text){
        return Arrays.stream(ClienteStatus.values())
                .filter(c -> text.equals(c.toString()))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
