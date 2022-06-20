package application.repository.hashMap;

import domain.entities.venda.StatusVenda;
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
        boolean idFoundOnDb = fakeDb.containsKey(id);
        if(idFoundOnDb){
            fakeDb.replace(id, venda);
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
    public boolean delete(Venda venda) {
        Integer id = venda.getId();
        boolean idFoundOnDb = fakeDb.containsKey(id);
        if(idFoundOnDb){
            fakeDb.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Venda> findVendaByStatus(StatusVenda statusVenda) {
        return null;
    }

    @Override
    public boolean updateStatus(Venda venda) {
        boolean isVendaStatusNotSent = venda.getStatusVenda() == StatusVenda.NAO_ENVIADO;
        if(isVendaStatusNotSent) {
            venda.setStatusVenda(StatusVenda.ENVIADO);
            return true;
        }
        return false;
    }
}
