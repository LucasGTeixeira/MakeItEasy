package application.repository;

import domain.entities.cliente.Cliente;
import domain.usecases.cliente.ClienteDAO;

import java.util.*;

public class MockedClienteDAO implements ClienteDAO {

    private static final Map<Integer, Cliente> fakeDb = new LinkedHashMap<>();
    private static int autoIncrementId;
    @Override
    public Integer create(Cliente cliente) {
        autoIncrementId++;
        cliente.setId(autoIncrementId);
        fakeDb.put(autoIncrementId, cliente);
        return autoIncrementId;
    }

    @Override
    public Optional<Cliente> findByCpf(String cpf) {
        return fakeDb.values().stream()
                .filter(cliente -> cliente.getCpf().equals(cpf))
                .findAny();
    }

    @Override
    public Optional<Cliente> findOne(Integer id) {
        return fakeDb.values().stream()
                .filter(cliente -> cliente.getId().equals(id))
                .findAny();
    }

    @Override
    public List<Cliente> findAll() {
        return new ArrayList<>(fakeDb.values());
    }

    @Override
    public boolean update(Cliente cliente) {
        Integer id = cliente.getId();
        if(fakeDb.containsKey(id)){
            fakeDb.replace(id, cliente);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteByKey(Integer id) {
        if(fakeDb.containsKey(id)){
            fakeDb.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Cliente cliente) {
        Integer id = cliente.getId();
        if(fakeDb.containsKey(id)){
            fakeDb.remove(id);
            return true;
        }
        return false;
    }
}
