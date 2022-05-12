package domain.entities.campanha;

public enum CategoriaProdutos {
    COSMETICOS("Cosméticos"),
    SAUDE_BEM_ESTAR("Saúde e Bem-estar"),
    COZINHA("Cozinha");

    final String text;

    CategoriaProdutos(String text) {
        this.text = text;
    }
}
