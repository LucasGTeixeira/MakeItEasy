package domain.usecases.produto;

import domain.entities.produto.Produto;
import domain.usecases.utils.Exceptions.EntityNotFoundException;
import domain.usecases.utils.Exceptions.ProdutoRelatedToVendaException;
import domain.usecases.venda.ListarVendasUseCase;

public class RemoverProdutoUseCase {

    private final ProdutoDAO produtoDAO;
    private final ListarProdutosUseCase listarProdutosUseCase;
    private final ListarVendasUseCase listarVendasUseCase;

    public RemoverProdutoUseCase(ProdutoDAO produtoDAO, ListarProdutosUseCase listarProdutosUseCase, ListarVendasUseCase listarVendasUseCase) {
        this.produtoDAO = produtoDAO;
        this.listarProdutosUseCase = listarProdutosUseCase;
        this.listarVendasUseCase = listarVendasUseCase;
    }

    public boolean isProdutoInVendas(Produto produto){
        return listarVendasUseCase.findVendaByCodProduto(produto.getCodProduto()).isPresent();
    }

    public boolean delete(Produto produto){
        if(produto == null)
            throw new IllegalArgumentException("Produto não pode ser nulo");

        Integer codProduto = produto.getCodProduto();
        boolean codProdutoNotFound = listarProdutosUseCase.findByCodProduto(codProduto).isEmpty();
        if(codProdutoNotFound)
            throw new EntityNotFoundException("Codigo do produto não pertece a nenhum produto no sistema");

        if(isProdutoInVendas(produto)){
            throw new EntityNotFoundException("Remoção inválida: Produto está relacionado a uma venda do sistema");
        }
        return produtoDAO.delete(produto);
    }

    public boolean delete(Integer id){
        if (id == null)
            throw new IllegalArgumentException("id de produto não pode ser nulo");

        boolean produtoIdNotFound = listarProdutosUseCase.findOne(id).isEmpty();
        if(produtoIdNotFound)
            throw new EntityNotFoundException("id não pertence a nenhum produto do sistema");

        Produto produto = listarProdutosUseCase.findOne(id).get();
        if(isProdutoInVendas(produto))
            throw new ProdutoRelatedToVendaException("Remoção inválida: Produto está relacionado a uma venda do sistema");

        return produtoDAO.deleteByKey(id);
    }
}
