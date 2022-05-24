package domain.entities.produto;

import java.math.BigDecimal;

public class Produto {
    private Integer id;
    private Integer codProduto;
    private String nome;
    private CategoriaProdutos categoria;
    private BigDecimal valor;
    private Boolean disponibilidade;
    private String codCampanha;

    public Produto(Integer id, Integer codProduto, String nome, CategoriaProdutos categoria, BigDecimal valor, Boolean disponibilidade, String codCampanha) {
        this.id = id;
        this.codProduto = codProduto;
        this.nome = nome;
        this.categoria = categoria;
        this.valor = valor;
        this.disponibilidade = disponibilidade;
        this.codCampanha = codCampanha;
    }

    public Produto(Integer codProduto, String nome, CategoriaProdutos categoria, BigDecimal valor, Boolean disponibilidade, String codCampanha) {
        this.codProduto = codProduto;
        this.nome = nome;
        this.categoria = categoria;
        this.valor = valor;
        this.disponibilidade = disponibilidade;
        this.codCampanha = codCampanha;
    }

    public String getCodCampanha() {
        return codCampanha;
    }

    public void setCodCampanha(String codCampanha) {
        this.codCampanha = codCampanha;
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
        return id + ", " +
                codProduto + ", " +
                nome + ", " +
                categoria.text + ", " +
                valor + ", " +
                disponibilidade + ", " +
                codCampanha;
    }
}
