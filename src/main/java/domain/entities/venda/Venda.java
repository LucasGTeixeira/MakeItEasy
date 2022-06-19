package domain.entities.venda;

public class Venda {
    private Integer id;
    private String cpfCliente;
    private Integer codProduto;
    private FormaPagamento formaPagamento;
    private StatusVenda statusVenda;
    private Integer qntProduto;
    private Double valorTotal;

    public Venda(Integer id, String cpfCliente, Integer codProduto, FormaPagamento formaPagamento, StatusVenda statusVenda, Integer qntProduto, Double valorTotal) {
        this.id = id;
        this.cpfCliente = cpfCliente;
        this.codProduto = codProduto;
        this.formaPagamento = formaPagamento;
        this.statusVenda = statusVenda;
        this.qntProduto = qntProduto;
        this.valorTotal = valorTotal;
    }

    public Venda(Integer id, String cpfCliente, Integer codProduto, FormaPagamento formaPagamento, StatusVenda statusVenda, Integer qntProduto) {
        this.id = id;
        this.cpfCliente = cpfCliente;
        this.codProduto = codProduto;
        this.formaPagamento = formaPagamento;
        this.statusVenda = statusVenda;
        this.qntProduto = qntProduto;
    }

    public Venda(String cpfCliente, Integer codProduto, FormaPagamento formaPagamento, StatusVenda statusVenda, Integer qntProduto) {
        this.cpfCliente = cpfCliente;
        this.codProduto = codProduto;
        this.formaPagamento = formaPagamento;
        this.statusVenda = statusVenda;
        this.qntProduto = qntProduto;
    }

    public Venda() {
    }

    public Integer getQntProduto() {
        return qntProduto;
    }

    public void setQntProduto(Integer qntProduto) {
        this.qntProduto = qntProduto;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public Integer getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(Integer codProduto) {
        this.codProduto = codProduto;
    }

    public StatusVenda getStatusVenda() {
        return statusVenda;
    }

    public void setStatusVenda(StatusVenda statusVenda) {
        this.statusVenda = statusVenda;
    }

    @Override
    public String toString() {
        return id + ", " +
                codProduto + ", " +
                cpfCliente + ", " +
                valorTotal + ", " +
                formaPagamento.text+ ", " +
                statusVenda.text;
    }
}
