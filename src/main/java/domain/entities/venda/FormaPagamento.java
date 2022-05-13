package domain.entities.venda;

public enum FormaPagamento {
    CREDITO("Cartão de crédito"),
    DEBITO("Cartão de débito"),
    BOLETO_BANCARIO("Boleto Bancário"),
    PIX("PIX");

    final String text;
    FormaPagamento(String s) {
        this.text = s;
    }
}
