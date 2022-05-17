package domain.usecases.produto;

import domain.entities.produto.Produto;
import domain.usecases.utils.Exceptions.EntityNotFoundException;

public class RemoverProdutoUseCase {

    private ProdutoDAO produtoDAO;

    public RemoverProdutoUseCase(ProdutoDAO produtoDAO) {
        this.produtoDAO = produtoDAO;
    }

    public boolean delete(Produto produto){
        if(produto == null || produtoDAO.findByCodProduto(produto.getCodProduto()).isEmpty())
            throw new EntityNotFoundException("Produto nulo ou não encontrado");
        return produtoDAO.delete(produto);
    }

    public boolean delete(Integer id){
        if (id == null || produtoDAO.findByCodProduto(id).isEmpty())
            throw new EntityNotFoundException("id de produto nulo ou não encontrado");
        return produtoDAO.deleteByKey(id);
    }
}
