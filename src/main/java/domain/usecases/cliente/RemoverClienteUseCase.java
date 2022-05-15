package domain.usecases.cliente;

import domain.entities.cliente.Cliente;

public class RemoverClienteUseCase {
    private ClienteDAO clienteDAO;

    public RemoverClienteUseCase(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    public boolean delete(Cliente cliente){
        if(cliente == null || clienteDAO.findByCpf(cliente.getCpf()).isEmpty())
            throw new IllegalArgumentException("cliente não encontrado ou nulo");
        return clienteDAO.delete(cliente);
    }

    public boolean delete(Integer id){
        if (id == null || clienteDAO.findOne(id).isEmpty())
            throw new IllegalArgumentException("id não encontrado ou nulo");
        return clienteDAO.deleteByKey(id);
    }
}
