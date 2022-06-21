package domain.usecases.campanha;

import domain.entities.campanha.Campanha;
import domain.usecases.produto.ListarProdutosUseCase;
import domain.usecases.utils.Exceptions.CampanhaRelatedToProdutoException;
import domain.usecases.utils.Exceptions.EntityNotFoundException;

public class RemoverCampanhaUseCase {
    private final ListarCampanhasUseCase listarCampanhasUseCase;
    private final CampanhaDAO campanhaDAO;
    private final ListarProdutosUseCase listarProdutosUseCase;

    public RemoverCampanhaUseCase(ListarCampanhasUseCase listarCampanhasUseCase, CampanhaDAO campanhaDAO, ListarProdutosUseCase listarProdutosUseCase) {
        this.listarCampanhasUseCase = listarCampanhasUseCase;
        this.campanhaDAO = campanhaDAO;
        this.listarProdutosUseCase = listarProdutosUseCase;
    }

    public boolean isCampanhaRelatedToProduto(Campanha campanha){
        return listarProdutosUseCase.findProdutoByCodCampanha(campanha.getCodigo()).isPresent();
    }

    public boolean delete(Campanha campanha){
        if(campanha == null)
            throw new IllegalArgumentException("Campanha não pode ser nula");

        String codCampanha = campanha.getCodigo();
        boolean codCampanhaNotFound = listarCampanhasUseCase.findByCodigo(codCampanha).isEmpty();
        if(codCampanhaNotFound)
            throw new EntityNotFoundException("Código da campanha não encontrado no sistema");

        if(isCampanhaRelatedToProduto(campanha))
            throw new EntityNotFoundException("Remoção inválida: Há produtor relacionado a essa campanha");
        return campanhaDAO.delete(campanha);
    }

    public boolean delete(Integer id){
        if(id == null)
            throw new IllegalArgumentException("Id não pode ser nulo");

        boolean idCampanhaNotFound = listarCampanhasUseCase.findOne(id).isEmpty();
        if(idCampanhaNotFound)
            throw new EntityNotFoundException("Id não encontrado");

        Campanha campanha = listarCampanhasUseCase.findOne(id).get();
        if(isCampanhaRelatedToProduto(campanha))
            throw new CampanhaRelatedToProdutoException("Remoção inválida: Há produtor relacionado a essa campanha");


        return campanhaDAO.deleteByKey(id);
    }
}
