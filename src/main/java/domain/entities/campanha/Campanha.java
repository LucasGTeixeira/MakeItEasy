package domain.entities.campanha;

import domain.entities.empresa.Empresa;

import java.time.LocalDate;

public class Campanha {
    private Integer codigo; //serve como ID da Campanha
    private String nome;

    private String edicao;

    private LocalDate dataLancamento;

    private LocalDate dataExpiracao;
    private Empresa empresa;

    public Campanha(Integer codigo, String nome, String edicao, LocalDate dataLancamento, LocalDate dataExpiracao, Empresa empresa) {
        this.codigo = codigo;
        this.nome = nome;
        this.edicao = edicao;
        this.dataLancamento = dataLancamento;
        this.dataExpiracao = dataExpiracao;
        this.empresa = empresa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEdicao() {
        return edicao;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public LocalDate getDataExpiracao() {
        return dataExpiracao;
    }

    public void setDataExpiracao(LocalDate dataExpiracao) {
        this.dataExpiracao = dataExpiracao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    @Override
    public String toString() {
        return "Campanha{" +
                "codigo=" + codigo +
                ", nome='" + nome + '\'' +
                ", edicao='" + edicao + '\'' +
                ", dataLancamento=" + dataLancamento +
                ", dataExpiracao=" + dataExpiracao +
                ", empresa=" + empresa +
                '}';
    }
}
