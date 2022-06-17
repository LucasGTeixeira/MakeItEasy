package domain.usecases.cliente;

import domain.entities.cliente.Cliente;
import domain.usecases.utils.Exceptions.EntityNotFoundException;
import domain.usecases.utils.Notification;
import domain.usecases.utils.Validator;

public class ModificarClienteUseCase {

    private final ClienteDAO clienteDAO;
    private final ListarClientesUseCase listarClientesUseCase;

    public ModificarClienteUseCase(ClienteDAO clienteDAO, ListarClientesUseCase listarClientesUseCase) {
        this.clienteDAO = clienteDAO;
        this.listarClientesUseCase = listarClientesUseCase;
    }

    public boolean update(Cliente cliente){
        Validator<Cliente> validator = new ClienteValidator();
        Notification notification = validator.validate(cliente);

        if(notification.hasErros())
            throw new IllegalArgumentException(notification.errorMessage());

        String cpf = cliente.getCpf();
        boolean clienteCpfNotFound = listarClientesUseCase.findByCpf(cpf).isEmpty();
        if(clienteCpfNotFound)
            throw new EntityNotFoundException("não há nenhum cliente com esse cpf no sistema");

        return clienteDAO.update(cliente);
    }
}
