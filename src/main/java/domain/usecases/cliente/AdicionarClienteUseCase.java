package domain.usecases.cliente;

import domain.entities.cliente.Cliente;
import domain.usecases.utils.Exceptions.EntityAlreadyExistsException;
import domain.usecases.utils.Notification;
import domain.usecases.utils.Validator;

public class AdicionarClienteUseCase {

    public ClienteDAO clienteDAO;

    public AdicionarClienteUseCase(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    public Integer insert(Cliente cliente){
        Validator<Cliente> validator = new ClienteValidator();
        Notification notification = validator.validate(cliente);

        if(notification.hasErros())
            throw new IllegalArgumentException(notification.errorMessage());

        String cpf = cliente.getCpf();
        if(clienteDAO.findByCpf(cpf).isPresent())
            throw new EntityAlreadyExistsException("Cpf já cadastrado no sistema");

        return clienteDAO.create(cliente);
    }

}
