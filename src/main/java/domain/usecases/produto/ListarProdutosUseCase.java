package domain.usecases.produto;

import domain.entities.produto.Produto;

import java.util.List;
import java.util.Optional;

public class ListarProdutosUseCase {
    private final ProdutoDAO produtoDAO;

    public ListarProdutosUseCase(ProdutoDAO produtoDAO) {
        this.produtoDAO = produtoDAO;
    }

    public Optional<Produto> findByCodProduto(Integer cod){
        if(cod == null)
            throw new IllegalArgumentException("Codigo do produto não pode ser nulo");
        return produtoDAO.findByCodProduto(cod);
    }

    public Optional<Produto> findOne(Integer id){
        if(id == null)
            throw new IllegalArgumentException("Id não pode ser nulo");
        return produtoDAO.findOne(id);
    }

    public List<Produto> findAll(){
        return produtoDAO.findAll();
    }
}
