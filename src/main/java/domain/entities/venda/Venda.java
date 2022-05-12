package domain.entities.venda;

import domain.entities.campanha.Campanha;
import domain.entities.cliente.Cliente;
import domain.entities.produto.Produto;

import java.util.List;

public class Venda {
    private Float valorTotal;
    private Boolean disponibilidade;
    private Cliente cliente;
    private Campanha campanha;
    private List<Produto> produtos;

    public Venda(Float valorTotal, Boolean disponibilidade, Cliente cliente, Campanha campanha, List<Produto> produtos) {
        this.valorTotal = valorTotal;
        this.disponibilidade = disponibilidade;
        this.cliente = cliente;
        this.campanha = campanha;
        this.produtos = produtos;
    }

    public Float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Boolean getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(Boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Campanha getCampanha() {
        return campanha;
    }

    public void setCampanha(Campanha campanha) {
        this.campanha = campanha;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    @Override
    public String toString() {
        return "Venda{" +
                "valorTotal=" + valorTotal +
                ", disponibilidade=" + disponibilidade +
                ", cliente=" + cliente +
                ", campanha=" + campanha +
                ", produtos=" + produtos +
                '}';
    }
}
