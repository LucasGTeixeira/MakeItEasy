package domain.entities.venda;

public class Venda {
    private Integer id;
    private String cpfCliente;
    private Integer codProduto;
    private Float valorTotal;
    private FormaPagamento formaPagamento;
    private StatusVenda statusVenda;

    public Venda(String cpfCliente, Integer codProduto, Float valorTotal, FormaPagamento formaPagamento, StatusVenda statusVenda) {
        this.cpfCliente = cpfCliente;
        this.codProduto = codProduto;
        this.valorTotal = valorTotal;
        this.formaPagamento = formaPagamento;
        this.statusVenda = statusVenda;
    }

    public Venda(Integer id, String cpfCliente, Integer codProduto, Float valorTotal, FormaPagamento formaPagamento, StatusVenda statusVenda) {
        this.id = id;
        this.cpfCliente = cpfCliente;
        this.codProduto = codProduto;
        this.valorTotal = valorTotal;
        this.formaPagamento = formaPagamento;
        this.statusVenda = statusVenda;
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

    public Float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Float valorTotal) {
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
                cpfCliente + ", " +
                codProduto + ", " +
                valorTotal + ", " +
                formaPagamento.text+ ", " +
                statusVenda.text;
    }
}
