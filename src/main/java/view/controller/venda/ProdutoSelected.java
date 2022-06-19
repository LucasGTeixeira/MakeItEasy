package view.controller.venda;

import java.math.BigDecimal;

public class ProdutoSelected {
    private final Integer codigo;
    private final String nome;
    private final double valor;
    private final int quantidade;

    public ProdutoSelected(Integer codigo, String nome, double valor, int quantidade) {
        this.codigo = codigo;
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public String getNome() {
        return nome;
    }

    public double getValor() {
        return valor;
    }
}
