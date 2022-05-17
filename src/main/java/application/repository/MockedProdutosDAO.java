package application.repository;

import domain.entities.produto.Produto;
import domain.usecases.produto.ProdutoDAO;

import java.util.*;

public class MockedProdutosDAO implements ProdutoDAO {

    private static final Map<Integer, Produto> fakeDb = new LinkedHashMap<>();
    private static int autoIncrementId;
    @Override
    public Integer create(Produto produto) {
        autoIncrementId++;
        produto.setId(autoIncrementId);
        fakeDb.put(autoIncrementId, produto);
        return autoIncrementId;
    }

    @Override
    public Optional<Produto> findByCodProduto(Integer cod) {
        return fakeDb.values().stream()
                .filter(produto -> produto.getCodProduto().equals(cod))
                .findAny();
    }

    @Override
    public Optional<Produto> findOne(Integer id) {
        return fakeDb.values().stream()
                .filter(produto -> produto.getId().equals(id))
                .findAny();
    }

    @Override
    public List<Produto> findAll() {
        return new ArrayList<>(fakeDb.values());
    }

    @Override
    public boolean update(Produto produto) {
        Integer id = produto.getId();
        if(fakeDb.containsKey(id)){
            fakeDb.replace(id, produto);
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
    public boolean delete(Produto produto) {
        Integer id = produto.getId();
        if(fakeDb.containsKey(id)) {
            fakeDb.remove(id);
            return true;
        }
        return false;
    }
}
