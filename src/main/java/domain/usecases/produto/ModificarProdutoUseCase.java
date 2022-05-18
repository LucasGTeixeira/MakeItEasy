package domain.usecases.produto;

import domain.entities.produto.Produto;
import domain.usecases.utils.Exceptions.EntityNotFoundException;
import domain.usecases.utils.Notification;
import domain.usecases.utils.Validator;
//TODO validar se o valor modificado da campanha é valido
public class ModificarProdutoUseCase {
    private ProdutoDAO produtoDAO;

    public ModificarProdutoUseCase(ProdutoDAO produtoDAO) {
        this.produtoDAO = produtoDAO;
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

        return produtoDAO.update(produto);
    }
}
