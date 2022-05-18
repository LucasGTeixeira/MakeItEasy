package domain.usecases.cliente;

import domain.entities.cliente.Cliente;

import java.util.List;
import java.util.Optional;

public class ListarClientesUseCase {
    private final ClienteDAO clienteDAO;

    public ListarClientesUseCase(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    public Optional<Cliente> findByCpf(String cpf){
        if(cpf == null)
            throw new IllegalArgumentException("Cpf não pode ser nulo");
        return clienteDAO.findByCpf(cpf);
    }

    public Optional<Cliente> findOne(Integer id){
        if(id == null)
            throw new IllegalArgumentException("id não pode ser nulo");
        return clienteDAO.findOne(id);
    }

    public List<Cliente> findAll(){
        return clienteDAO.findAll();
    }
}
