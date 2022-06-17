package domain.usecases.produto;

import domain.entities.produto.Produto;
import domain.usecases.utils.Exceptions.EntityNotFoundException;

public class RemoverProdutoUseCase {

    private final ProdutoDAO produtoDAO;
    private final ListarProdutosUseCase listarProdutosUseCase;

    public RemoverProdutoUseCase(ProdutoDAO produtoDAO, ListarProdutosUseCase listarProdutosUseCase) {
        this.produtoDAO = produtoDAO;
        this.listarProdutosUseCase = listarProdutosUseCase;
    }

    public boolean delete(Produto produto){
        if(produto == null)
            throw new IllegalArgumentException("Produto não pode ser nulo");

        Integer codProduto = produto.getCodProduto();
        boolean codProdutoNotFound = listarProdutosUseCase.findByCodProduto(codProduto).isEmpty();
        if(codProdutoNotFound)
            throw new EntityNotFoundException("Codigo do produtão não pertece a nenhum produto no sistema");

        return produtoDAO.delete(produto);
    }

    public boolean delete(Integer id){
        if (id == null)
            throw new IllegalArgumentException("id de produto não pode ser nulo");

        boolean produtoIdNotFound = listarProdutosUseCase.findOne(id).isEmpty();
        if(produtoIdNotFound)
            throw new EntityNotFoundException("id não pertence a nenhum produto do sistema");

        return produtoDAO.deleteByKey(id);
    }
}
