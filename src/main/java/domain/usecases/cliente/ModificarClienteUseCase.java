package domain.usecases.cliente;

import domain.entities.cliente.Cliente;
import domain.usecases.utils.Exceptions.EntityNotFoundException;
import domain.usecases.utils.Notification;
import domain.usecases.utils.Validator;

public class ModificarClienteUseCase {

    private ClienteDAO clienteDAO;

    public ModificarClienteUseCase(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    public boolean update(Cliente cliente){
        Validator<Cliente> validator = new ClienteValidator();
        Notification notification = validator.validate(cliente);

        if(notification.hasErros())
            throw new IllegalArgumentException(notification.errorMessage());

        String cpf = cliente.getCpf();
        if(clienteDAO.findByCpf(cpf).isEmpty())
            throw new EntityNotFoundException("não há nenhum cliente com esse cpf no sistema");

        return clienteDAO.update(cliente);
    }
}
