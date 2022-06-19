package domain.entities.campanha;

import java.time.LocalDate;

public class Campanha {
    private Integer id;
    private String codigo;
    private String nome;
    private String edicao;
    private LocalDate dataLancamento;
    private LocalDate dataExpiracao;
    private String cnpjEmpresa;

    public Campanha(Integer id, String codigo, String nome, String edicao, LocalDate dataLancamento, LocalDate dataExpiracao, String cnpjEmpresa) {
        this.id = id;
        this.codigo = codigo;
        this.nome = nome;
        this.edicao = edicao;
        this.dataLancamento = dataLancamento;
        this.dataExpiracao = dataExpiracao;
        this.cnpjEmpresa = cnpjEmpresa;
    }

    public Campanha(String codigo, String nome, String edicao, LocalDate dataLancamento, LocalDate dataExpiracao, String cnpjEmpresa) {
        this.codigo = codigo;
        this.nome = nome;
        this.edicao = edicao;
        this.dataLancamento = dataLancamento;
        this.dataExpiracao = dataExpiracao;
        this.cnpjEmpresa = cnpjEmpresa;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public String getCnpjEmpresa() {
        return cnpjEmpresa;
    }

    public void setCnpjEmpresa(String cnpjEmpresa) {
        this.cnpjEmpresa = cnpjEmpresa;
    }

    @Override
    public String toString() {
        return codigo + ", " + nome;
    }

    public String toRelatorio(){
        return id + ", " +
                codigo + ", " +
                nome + ", " +
                edicao + ", " +
                dataLancamento + ", " +
                dataExpiracao + ", " +
                cnpjEmpresa;
    }
}
