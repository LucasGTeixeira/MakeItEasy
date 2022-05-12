package domain.entities.campanha;

import domain.entities.empresa.Empresa;
import domain.entities.produto.Produto;

import java.util.List;

public class Campanha {
    private Integer codigio;
    private Empresa empresa;
    private List<Produto> produtos;
    private CategoriaProdutos categoria;

    public Campanha(Integer codigio, Empresa empresa, List<Produto> produtos, CategoriaProdutos categoria) {
        this.codigio = codigio;
        this.empresa = empresa;
        this.produtos = produtos;
        this.categoria = categoria;
    }

    public CategoriaProdutos getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaProdutos categoria) {
        this.categoria = categoria;
    }

    public Integer getCodigio() {
        return codigio;
    }

    public void setCodigio(Integer codigio) {
        this.codigio = codigio;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    @Override
    public String toString() {
        return "Campanha{" +
                "codigio=" + codigio +
                ", empresa=" + empresa +
                ", produtos=" + produtos +
                '}';
    }
}
