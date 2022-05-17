package application.repository;

import domain.entities.produto.Produto;
import domain.entities.venda.Venda;
import domain.usecases.venda.VendaDAO;

import java.util.*;

public class MockedVendasDAO implements VendaDAO {

    private static final Map<Integer, Venda> fakeDb = new LinkedHashMap<>();
    private static int autoIncrementId;
    @Override
    public Integer create(Venda venda) {
        autoIncrementId++;
        venda.setId(autoIncrementId);
        fakeDb.put(autoIncrementId, venda);
        return autoIncrementId;
    }

    @Override
    public Optional<Venda> findOne(Integer id) {
        return fakeDb.values().stream()
                .filter(venda -> venda.getId().equals(id))
                .findAny();
    }

    @Override
    public List<Venda> findAll() {
        return new ArrayList<>(fakeDb.values());
    }

    @Override
    public boolean update(Venda venda) {
        Integer id = venda.getId();
        if(fakeDb.containsKey(id)){
            fakeDb.replace(id, venda);
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
    public boolean delete(Venda venda) {
        Integer id = venda.getId();
        if(fakeDb.containsKey(id)){
            fakeDb.remove(id);
            return true;
        }
        return false;
    }
}
