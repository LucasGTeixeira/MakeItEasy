package domain.usecases.produto;

import domain.entities.produto.Produto;
import domain.usecases.utils.DAO;

import java.util.Optional;

public interface ProdutoDAO extends DAO<Produto, Integer> {
    Optional<Produto> findByCodProduto(Integer cod);

    Optional<Produto> findProdutoByCodCampanha(String codCampanha);
}
