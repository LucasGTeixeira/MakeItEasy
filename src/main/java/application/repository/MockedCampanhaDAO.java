package application.repository;

import domain.entities.campanha.Campanha;
import domain.usecases.campanha.CampanhaDAO;

import java.util.*;

public class MockedCampanhaDAO implements CampanhaDAO {

    private static final Map<Integer, Campanha> fakeDb = new LinkedHashMap<>();
    private static int autoIncrementId;

    @Override
    public Integer create(Campanha campanha) {
        autoIncrementId++;
        campanha.setId(autoIncrementId);
        fakeDb.put(autoIncrementId, campanha);
        return autoIncrementId;
    }

    @Override
    public Optional<Campanha> findByCodigo(String codigo) {
        return fakeDb.values().stream()
                .filter(campanha -> campanha.getCodigo().equals(codigo))
                .findAny();
    }

    @Override
    public Optional<Campanha> findByCnpj(String cnpj) {
        return fakeDb.values().stream()
                .filter(campanha -> campanha.getCnpjEmpresa().equals(cnpj))
                .findAny();
    }

    @Override
    public Optional<Campanha> findOne(Integer id) {
        return fakeDb.values().stream()
                .filter(campanha -> campanha.getId().equals(id))
                .findAny();
    }

    @Override
    public List<Campanha> findAll() {
        return new ArrayList<>(fakeDb.values());
    }

    @Override
    public boolean update(Campanha campanha) {
        Integer id = campanha.getId();
        boolean idFoundOnDb = fakeDb.containsKey(id);
        if(idFoundOnDb) {
            fakeDb.replace(id, campanha);
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
    public boolean delete(Campanha campanha) {
        Integer id = campanha.getId();
        boolean idFoundOnDb = fakeDb.containsKey(id);
        if(idFoundOnDb){
            fakeDb.remove(id);
            return true;
        }
        return false;
    }
}
