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
    private final ProdutoDAO produtoDAO;
    private final ClienteDAO clienteDAO;
    private final VendaDAO vendaDAO;

    public AdicionarVendaUseCase(ProdutoDAO produtoDAO, ClienteDAO clienteDAO, VendaDAO vendaDAO) {
        this.produtoDAO = produtoDAO;
        this.clienteDAO = clienteDAO;
        this.vendaDAO = vendaDAO;
    }

    public Integer insert(Venda venda){
        Validator<Venda> validator = new VendaValidator();
        Notification notification = validator.validate(venda);

        if(notification.hasErros())
            throw new IllegalArgumentException(notification.errorMessage());

        Integer codProduto = venda.getCodProduto();
        boolean codProdutoNotFound = produtoDAO.findByCodProduto(codProduto).isEmpty();
        if(codProdutoNotFound)
            throw new EntityNotFoundException("Produto indicado não encontrado");

        String cpfCliente = venda.getCpfCliente();
        boolean cpfClienteNotFound = clienteDAO.findByCpf(cpfCliente).isEmpty();
        if(cpfClienteNotFound)
            throw new EntityNotFoundException("Cliente indicado não encontrado");

        return vendaDAO.create(venda);
    }
}
