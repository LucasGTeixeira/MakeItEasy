package domain.entities.venda;

public enum StatusVenda {
    ENVIADO("Enviado"),
    NAO_ENVIADO("Não enviado");

    final String text;

    StatusVenda(String text) {
        this.text = text;
    }
}
