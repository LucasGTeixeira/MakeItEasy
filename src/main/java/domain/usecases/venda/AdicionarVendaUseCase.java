package domain.usecases.venda;

import domain.entities.campanha.Campanha;
import domain.entities.cliente.Cliente;
import domain.entities.empresa.Empresa;
import domain.entities.venda.Venda;
import domain.usecases.campanha.CampanhaDAO;
import domain.usecases.cliente.ClienteDAO;
import domain.usecases.empresa.EmpresaDAO;
import domain.usecases.produto.ProdutoDAO;
import domain.usecases.utils.Exceptions.EntityNotFoundException;
import domain.usecases.utils.Notification;
import domain.usecases.utils.Validator;

public class AdicionarVendaUseCase {
    private ProdutoDAO produtoDAO;
    private CampanhaDAO campanhaDAO;
    private ClienteDAO clienteDAO;
    private VendaDAO vendaDAO;
    private EmpresaDAO empresaDAO;

    public AdicionarVendaUseCase(ProdutoDAO produtoDAO, CampanhaDAO campanhaDAO, ClienteDAO clienteDAO, VendaDAO vendaDAO, EmpresaDAO empresaDAO) {
        this.produtoDAO = produtoDAO;
        this.campanhaDAO = campanhaDAO;
        this.clienteDAO = clienteDAO;
        this.vendaDAO = vendaDAO;
        this.empresaDAO = empresaDAO;
    }

    public Integer insert(Venda venda){
        Validator<Venda> validator = new VendaValidator();
        Notification notification = validator.validate(venda);
        if(notification.hasErros())
            throw new IllegalArgumentException(notification.errorMessage());

        Integer codProduto = venda.getCodProduto();
        if(produtoDAO.findByCodProduto(codProduto).isEmpty())
            throw new EntityNotFoundException("Produto indicado não encontrado");

        String cpfCliente = venda.getCpfCliente();
        if(clienteDAO.findByCpf(cpfCliente).isEmpty())
            throw new EntityNotFoundException("Cliente não pertence ao sistema");

        return vendaDAO.create(venda);
    }
}
