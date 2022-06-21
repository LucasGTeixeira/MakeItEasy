package domain.usecases.cliente;

import domain.entities.cliente.Cliente;
import domain.entities.venda.Venda;
import domain.usecases.utils.Exceptions.ClienteRelatedToVendaException;
import domain.usecases.utils.Exceptions.EntityNotFoundException;
import domain.usecases.venda.ListarVendasUseCase;

public class RemoverClienteUseCase {
    private final ClienteDAO clienteDAO;
    private final ListarClientesUseCase listarClientesUseCase;
    private final ListarVendasUseCase listarVendasUseCase;

    public RemoverClienteUseCase(ClienteDAO clienteDAO, ListarClientesUseCase listarClientesUseCase, ListarVendasUseCase listarVendasUseCase) {
        this.clienteDAO = clienteDAO;
        this.listarClientesUseCase = listarClientesUseCase;
        this.listarVendasUseCase = listarVendasUseCase;
    }

    public boolean isClienteInVenda(Cliente cliente){
        return listarVendasUseCase.findVendaByCpf(cliente.getCpf()).isPresent();
    }

    public boolean delete(Cliente cliente){
        if(cliente == null)
            throw new IllegalArgumentException("cliente não pode ser nulo");

        String clienteCpf = cliente.getCpf();
        boolean clienteCpfNotFound = listarClientesUseCase.findByCpf(clienteCpf).isEmpty();
        if(clienteCpfNotFound)
            throw new EntityNotFoundException("Cpf do cliente não encontrado");

        if (isClienteInVenda(cliente)){
            throw new EntityNotFoundException("Remoção inválida, cliente relacionado com venda no sistema");
        }
        return clienteDAO.delete(cliente);
    }

    public boolean delete(Integer id){
        if (id == null)
            throw new IllegalArgumentException("id não pode ser nulo");

        boolean clienteIdNotFound = listarClientesUseCase.findOne(id).isEmpty();
        if(clienteIdNotFound)
            throw new EntityNotFoundException("Não há nenhum cliente com este id");

        Cliente cliente = listarClientesUseCase.findOne(id).get();
        if (isClienteInVenda(cliente))
            throw new ClienteRelatedToVendaException("Remoção inválida, cliente relacionado com venda no sistema");

        return clienteDAO.deleteByKey(id);
    }
}
