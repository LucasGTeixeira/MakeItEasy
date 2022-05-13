package application.repository;

import domain.entities.empresa.Empresa;
import domain.usecases.empresa.EmpresaDAO;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MockedEmpresaDAO implements EmpresaDAO {

    private static final Map<String, Empresa> fakeDb = new LinkedHashMap<>();

    @Override
    public Optional<Empresa> findByCnpj(String cnpj) {
        return Optional.empty();
    }

    @Override
    public String create(Empresa empresa) {
        return empresa.getCnpj(); // TODO (adicionar na entidade empresa um ID para datar no banco?)
    }

    @Override
    public Optional<Empresa> findOne(String cnpj) {
        return Optional.empty();
    }

    @Override
    public List<Empresa> findAll() {
        return null;
    }

    @Override
    public boolean update(Empresa empresa) {
        return false;
    }

    @Override
    public boolean deleteByKey(String cnpj) {
        return false;
    }

    @Override
    public boolean delete(Empresa empresa) {
        return false;
    }
}
