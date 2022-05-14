package domain.entities.produto;

import domain.entities.campanha.Campanha;

import java.math.BigDecimal;

public class Produto {
    private Integer id;
    private Long codProduto;
    private String nome;
    private CategoriaProdutos categoria;
    private BigDecimal valor;
    private Boolean disponibilidade;

    private Campanha campanha;

    public Produto(Integer id, Long codProduto, String nome, CategoriaProdutos categoria, BigDecimal valor, Boolean disponibilidade, Campanha campanha) {
        this.id = id;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(Long codProduto) {
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
                "id=" + id +
                ", codProduto=" + codProduto +
                ", nome='" + nome + '\'' +
                ", categoria=" + categoria +
                ", valor=" + valor +
                ", disponibilidade=" + disponibilidade +
                ", campanha=" + campanha +
                '}';
    }
}
