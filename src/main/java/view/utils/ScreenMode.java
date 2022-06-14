package view.utils;

public enum ScreenMode {
    INSERT("Inserir"),
    UPDATE("Atualizar");

    private final String titulo;

    ScreenMode(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }
}
