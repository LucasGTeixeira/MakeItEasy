package domain.entities.produto;

import domain.entities.campanha.Campanha;
import domain.entities.campanha.CategoriaProdutos;

import java.math.BigDecimal;

public class Produto {
    //TODO adicionar campo ID
    private Integer codProduto;
    private String nome;
    private CategoriaProdutos categoria;
    private BigDecimal valor;
    private Boolean disponibilidade;

    private Campanha campanha;

    public Produto(Integer codProduto, String nome, CategoriaProdutos categoria, BigDecimal valor, Boolean disponibilidade, Campanha campanha) {
        this.codProduto = codProduto;
        this.nome = nome;
        this.categoria = categoria;
        this.valor = valor;
        this.disponibilidade = disponibilidade;
        this.campanha = campanha;
    }

    public Campanha getCampanha() {
        return campanha;
    }

    public void setCampanha(Campanha campanha) {
        this.campanha = campanha;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public CategoriaProdutos getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaProdutos categoria) {
        this.categoria = categoria;
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
                ", categoria=" + categoria +
                ", valor=" + valor +
                ", disponibilidade=" + disponibilidade +
                ", campanha=" + campanha +
                '}';
    }
}
