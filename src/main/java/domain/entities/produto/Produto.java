package domain.entities.produto;

public class Produto {
    private Integer codProduto;
    private String nome;
    private Boolean disponibilidade;

    public Produto(Integer codProduto, String nome, Boolean disponibilidade) {
        this.codProduto = codProduto;
        this.nome = nome;
        this.disponibilidade = disponibilidade;
    }

    public Integer getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(Integer codProduto) {
        this.codProduto = codProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(Boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "codProduto=" + codProduto +
                ", nome='" + nome + '\'' +
                ", disponibilidade=" + disponibilidade +
                '}';
    }
}
