package domain.entities.produto;

import java.util.Arrays;

public enum CategoriaProdutos {
    COSMETICOS("Cosméticos"),
    SAUDE_BEM_ESTAR("Saúde e Bem-estar"),
    COZINHA("Cozinha");

    final String text;

    CategoriaProdutos(String text) {
        this.text = text;
    }

    public static CategoriaProdutos toEnum(String text){
        return Arrays.stream(CategoriaProdutos.values())
                .filter(c -> text.equals(c.toString()))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
