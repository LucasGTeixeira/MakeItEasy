package domain.usecases.produto;

import domain.entities.produto.Produto;
import domain.usecases.campanha.CampanhaDAO;
import domain.usecases.campanha.ListarCampanhasUseCase;
import domain.usecases.utils.Exceptions.EntityAlreadyExistsException;
import domain.usecases.utils.Exceptions.EntityNotFoundException;
import domain.usecases.utils.Notification;
import domain.usecases.utils.Validator;

public class AdicionarProdutoUseCase {

    private final ProdutoDAO produtoDAO;
    private final ListarProdutosUseCase listarProdutosUseCase;
    private final ListarCampanhasUseCase listarCampanhasUseCase;

    public AdicionarProdutoUseCase(ProdutoDAO produtoDAO, ListarProdutosUseCase listarProdutosUseCase, ListarCampanhasUseCase listarCampanhasUseCase) {
        this.produtoDAO = produtoDAO;
        this.listarProdutosUseCase = listarProdutosUseCase;
        this.listarCampanhasUseCase = listarCampanhasUseCase;
    }

    public Integer insert(Produto produto){
        Validator<Produto> validator = new ProdutoValidator();
        Notification notification = validator.validate(produto);

        if(notification.hasErros())
            throw new IllegalArgumentException(notification.errorMessage());

        Integer codProduto = produto.getCodProduto();
        boolean CodProdutoAlreadyExists = listarProdutosUseCase.findByCodProduto(codProduto).isPresent();
        if(CodProdutoAlreadyExists)
            throw new EntityAlreadyExistsException("cod de produto já existe");

        String codCampanha = produto.getCodCampanha();
        boolean CampanhaDoesNotExists = listarCampanhasUseCase.findByCodigo(codCampanha).isEmpty();
        if(CampanhaDoesNotExists)
            throw new EntityNotFoundException("Não há campanhas com este código cadastradas no sistema");

        return produtoDAO.create(produto);
    }
}
