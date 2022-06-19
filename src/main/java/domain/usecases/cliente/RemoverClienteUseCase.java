package domain.usecases.cliente;

import domain.entities.cliente.Cliente;
import domain.usecases.utils.Exceptions.EntityNotFoundException;

public class RemoverClienteUseCase {
    private final ClienteDAO clienteDAO;
    private final ListarClientesUseCase listarClientesUseCase;

    public RemoverClienteUseCase(ClienteDAO clienteDAO, ListarClientesUseCase listarClientesUseCase) {
        this.clienteDAO = clienteDAO;
        this.listarClientesUseCase = listarClientesUseCase;
    }

    public boolean delete(Cliente cliente){
        if(cliente == null)
            throw new IllegalArgumentException("cliente não pode ser nulo");

        String clienteCpf = cliente.getCpf();
        boolean clienteCpfNotFound = listarClientesUseCase.findByCpf(clienteCpf).isEmpty();
        if(clienteCpfNotFound)
            throw new EntityNotFoundException("Cpf do cliente não encontrado");
        return clienteDAO.delete(cliente);
    }

    public boolean delete(Integer id){
        if (id == null)
            throw new IllegalArgumentException("id não pode ser nulo");

        boolean clienteIdNotFound = listarClientesUseCase.findOne(id).isEmpty();
        if(clienteIdNotFound){
            throw new EntityNotFoundException("Não há nenhum cliente com este id");
        }
        return clienteDAO.deleteByKey(id);
    }
}
