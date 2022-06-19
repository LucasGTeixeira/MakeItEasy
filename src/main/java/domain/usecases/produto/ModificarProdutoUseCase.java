package domain.usecases.produto;

import domain.entities.produto.Produto;
import domain.usecases.campanha.CampanhaDAO;
import domain.usecases.campanha.ListarCampanhasUseCase;
import domain.usecases.utils.Exceptions.EntityNotFoundException;
import domain.usecases.utils.Notification;
import domain.usecases.utils.Validator;
public class ModificarProdutoUseCase {

    private final ProdutoDAO produtoDAO;
    private final ListarProdutosUseCase listarProdutosUseCase;
    private final ListarCampanhasUseCase listarCampanhasUseCase;

    public ModificarProdutoUseCase(ProdutoDAO produtoDAO, ListarProdutosUseCase listarProdutosUseCase, ListarCampanhasUseCase listarCampanhasUseCase) {
        this.produtoDAO = produtoDAO;
        this.listarProdutosUseCase = listarProdutosUseCase;
        this.listarCampanhasUseCase = listarCampanhasUseCase;
    }

    public boolean update(Produto produto){
        Validator<Produto> validator = new ProdutoValidator();
        Notification notification = validator.validate(produto);

        if(notification.hasErros())
            throw new IllegalArgumentException(notification.errorMessage());

        Integer codProduto = produto.getCodProduto();
        boolean codProdutoNotFound = listarProdutosUseCase.findByCodProduto(codProduto).isEmpty();
        if(codProdutoNotFound)
            throw new EntityNotFoundException("Codigo do produto não existe no sistema");

        String codCampanha = produto.getCodCampanha();
        boolean codCampanhaNotFound = listarCampanhasUseCase.findByCodigo(codCampanha).isEmpty();
        if(codCampanhaNotFound)
            throw new EntityNotFoundException("Código da campamnha não existe no sistema");

        return produtoDAO.update(produto);
    }
}
