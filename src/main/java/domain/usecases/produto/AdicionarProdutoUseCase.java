package domain.usecases.produto;

import domain.entities.produto.Produto;
import domain.usecases.campanha.CampanhaDAO;
import domain.usecases.utils.Exceptions.EntityAlreadyExistsException;
import domain.usecases.utils.Exceptions.EntityNotFoundException;
import domain.usecases.utils.Notification;
import domain.usecases.utils.Validator;

public class AdicionarProdutoUseCase {
    private final ProdutoDAO produtoDAO;
    private final CampanhaDAO campanhaDAO;

    public AdicionarProdutoUseCase(ProdutoDAO produtoDAO, CampanhaDAO campanhaDAO) {
        this.produtoDAO = produtoDAO;
        this.campanhaDAO = campanhaDAO;
    }

    public Integer insert(Produto produto){
        Validator<Produto> validator = new ProdutoValidator();
        Notification notification = validator.validate(produto);

        if(notification.hasErros())
            throw new IllegalArgumentException(notification.errorMessage());

        Integer codProduto = produto.getCodProduto();
        boolean CodProdutoAlreadyExists = produtoDAO.findByCodProduto(codProduto).isPresent();
        if(CodProdutoAlreadyExists)
            throw new EntityAlreadyExistsException("cod de produto já existe");

        String codCampanha = produto.getCodCampanha();
        boolean CampanhaDoesNotExists = campanhaDAO.findByCodigo(codCampanha).isEmpty();
        if(CampanhaDoesNotExists)
            throw new EntityNotFoundException("Não há camanhas com este código cadastradas no sistema");

        return produtoDAO.create(produto);
    }
}
