package domain.usecases.cliente;

import domain.entities.cliente.Cliente;
import domain.usecases.utils.Exceptions.EntityAlreadyExistsException;
import domain.usecases.utils.Notification;
import domain.usecases.utils.Validator;

public class AdicionarClienteUseCase {

    private final ClienteDAO clienteDAO;
    private final ListarClientesUseCase listarClientesUseCase;

    public AdicionarClienteUseCase(ClienteDAO clienteDAO, ListarClientesUseCase listarClientesUseCase) {
        this.clienteDAO = clienteDAO;
        this.listarClientesUseCase = listarClientesUseCase;
    }

    public Integer insert(Cliente cliente){
        Validator<Cliente> validator = new ClienteValidator();
        Notification notification = validator.validate(cliente);

        if(notification.hasErros())
            throw new IllegalArgumentException(notification.errorMessage());

        String cpf = cliente.getCpf();
        boolean clientIdAlreadyExists = listarClientesUseCase.findByCpf(cpf).isPresent();
        if(clientIdAlreadyExists)
            throw new EntityAlreadyExistsException("Cpf já cadastrado no sistema");

        return clienteDAO.create(cliente);
    }

}
