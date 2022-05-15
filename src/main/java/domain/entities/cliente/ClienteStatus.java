package domain.entities.cliente;

public enum ClienteStatus {
    ATIVO("Ativo"),
    INATIVO("Inativo");

    final String text;

    ClienteStatus(String text) {
        this.text = text;
    }
}
