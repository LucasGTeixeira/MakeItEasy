package domain.usecases.cliente;

import domain.entities.cliente.Cliente;
import domain.usecases.utils.DAO;

import java.util.Optional;

public interface ClienteDAO extends DAO<Cliente, Integer> {
    Optional<Cliente> findByCpf(String cpf);
}
