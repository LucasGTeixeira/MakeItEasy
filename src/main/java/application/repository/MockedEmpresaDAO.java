package application.repository;

import domain.entities.empresa.Empresa;
import domain.usecases.empresa.EmpresaDAO;

import java.util.*;

public class MockedEmpresaDAO implements EmpresaDAO {

    private static final Map<Integer, Empresa> fakeDb = new LinkedHashMap<>();
    private static int autoIncrementId;


    @Override
    public Integer create(Empresa empresa) {
        autoIncrementId++; //incrementa o ID
        empresa.setId(autoIncrementId); //setta o id para o campo de empresa
        fakeDb.put(autoIncrementId, empresa);
        return autoIncrementId; //retorna o valor statico incrementado
    }

    @Override
    public Optional<Empresa> findByCnpj(String cnpj) {
        return fakeDb.values()
                .stream()//abstração da pesquisa
                .filter(empresa -> empresa.getCnpj().equals(cnpj))//procurar todas as empresas que tenham o cnpj iguais ao do parâmetro
                .findAny(); //retorna um Optional de Empresas
    }

    @Override
    public Optional<Empresa> findOne(Integer id) {
        return fakeDb.values().stream()//abstração
                .filter(empresa -> empresa.getId().equals(id))//filtrar por id
                .findAny();//retornar Optional de Empresa com o mesmo id
    }

    @Override
    public List<Empresa> findAll() {
        return new ArrayList<>(fakeDb.values());//retorna uma lista dos valores dentro do banco
    }

    @Override
    public boolean update(Empresa empresa) {
        Integer id = empresa.getId();
        boolean idFoundOnDb = fakeDb.containsKey(id);
        if(idFoundOnDb){
            fakeDb.replace(id, empresa);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteByKey(Integer id) {
        boolean idFoundOnDb = fakeDb.containsKey(id);
        if(idFoundOnDb){
            fakeDb.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Empresa empresa) {
        Integer id = empresa.getId();
        boolean idFoundOnDb = fakeDb.containsKey(id);
        if(idFoundOnDb){
            fakeDb.remove(id);
            return true;
        }
        return false;
    }
}
