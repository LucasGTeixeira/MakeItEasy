package domain.usecases.venda;

import domain.entities.produto.Produto;
import domain.entities.venda.Venda;
import domain.usecases.cliente.ListarClientesUseCase;
import domain.usecases.produto.ListarProdutosUseCase;
import domain.usecases.utils.Exceptions.EntityNotFoundException;
import domain.usecases.utils.Notification;
import domain.usecases.utils.Validator;

import java.math.BigDecimal;
import java.util.Optional;

public class AdicionarVendaUseCase {
    private final VendaDAO vendaDAO;
    private final ListarClientesUseCase listarClientesUseCase;
    private final ListarProdutosUseCase listarProdutosUseCase;

    public AdicionarVendaUseCase(VendaDAO vendaDAO, ListarClientesUseCase listarClientesUseCase, ListarProdutosUseCase listarProdutosUseCase) {
        this.vendaDAO = vendaDAO;
        this.listarClientesUseCase = listarClientesUseCase;
        this.listarProdutosUseCase = listarProdutosUseCase;
    }

    public Integer insert(Venda venda){
        Validator<Venda> validator = new VendaValidator();
        Notification notification = validator.validate(venda);

        if(notification.hasErros())
            throw new IllegalArgumentException(notification.errorMessage());

        Integer codProduto = venda.getCodProduto();
        Optional<Produto> produtoOptional = listarProdutosUseCase.findByCodProduto(codProduto);
        boolean codProdutoNotFound = produtoOptional.isEmpty();
        if(codProdutoNotFound)
            throw new EntityNotFoundException("Produto indicado não encontrado");
        Double valorProduto = produtoOptional.get().getValor();
        venda.setValorTotal(valorProduto * venda.getQntProduto());


        String cpfCliente = venda.getCpfCliente();
        boolean cpfClienteNotFound = listarClientesUseCase.findByCpf(cpfCliente).isEmpty();
        if(cpfClienteNotFound)
            throw new EntityNotFoundException("Cliente indicado não encontrado");

        return vendaDAO.create(venda);
    }
}
