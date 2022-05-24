package domain.usecases.produto;

import domain.entities.produto.Produto;
import domain.usecases.campanha.CampanhaDAO;
import domain.usecases.utils.Exceptions.EntityNotFoundException;
import domain.usecases.utils.Notification;
import domain.usecases.utils.Validator;
public class ModificarProdutoUseCase {
    private ProdutoDAO produtoDAO;

    private CampanhaDAO campanhaDAO;

    public ModificarProdutoUseCase(ProdutoDAO produtoDAO, CampanhaDAO campanhaDAO) {
        this.produtoDAO = produtoDAO;
        this.campanhaDAO = campanhaDAO;
    }

    public boolean update(Produto produto){
        Validator<Produto> validator = new ProdutoValidator();
        Notification notification = validator.validate(produto);

        if(notification.hasErros())
            throw new IllegalArgumentException(notification.errorMessage());

        Integer codProduto = produto.getCodProduto();
        boolean codProdutoNotFound = produtoDAO.findByCodProduto(codProduto).isEmpty();
        if(codProdutoNotFound)
            throw new EntityNotFoundException("Codigo do produto não existe no sistema");

        String codCampanha = produto.getCodCampanha();
        boolean codCampanhaNotFound = campanhaDAO.findByCodigo(codCampanha).isEmpty();
        if(codCampanhaNotFound)
            throw new EntityNotFoundException("Código da campamnha não existe no sistema");

        return produtoDAO.update(produto);
    }
}
