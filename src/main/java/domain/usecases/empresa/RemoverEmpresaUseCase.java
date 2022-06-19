package domain.usecases.empresa;

import domain.entities.empresa.Empresa;
import domain.usecases.campanha.ListarCampanhasUseCase;
import domain.usecases.utils.Exceptions.EmpresaRelatedToCampanhaException;
import domain.usecases.utils.Exceptions.EntityNotFoundException;


public class RemoverEmpresaUseCase {

    private final EmpresaDAO empresaDAO;
    private final ListarEmpresasUseCase listarEmpresasUseCase;
    private final ListarCampanhasUseCase listarCampanhasUseCase;

    public RemoverEmpresaUseCase(EmpresaDAO empresaDAO, ListarEmpresasUseCase listarEmpresasUseCase, ListarCampanhasUseCase listarCampanhasUseCase) {
        this.empresaDAO = empresaDAO;
        this.listarEmpresasUseCase = listarEmpresasUseCase;
        this.listarCampanhasUseCase = listarCampanhasUseCase;
    }

    public boolean isEmpresaInCampanha(Empresa empresa){
        return listarCampanhasUseCase.findByCnpj(empresa.getCnpj()).isPresent();
    }

    public boolean delete(Empresa empresa){
        if(empresa == null)
            throw new IllegalArgumentException("Empresa não pode ser nula");

        String empresaCnpj = empresa.getCnpj();
        boolean empresaCnpjNotFound = listarEmpresasUseCase.findByCnpj(empresaCnpj).isEmpty();
        if(empresaCnpjNotFound)
            throw new EntityNotFoundException("Não há nenhuma empresa com esse cnpj no sistema");

        boolean isEmpresaRelatedToCampanha = isEmpresaInCampanha(empresa);
        if(isEmpresaRelatedToCampanha)
            throw new EmpresaRelatedToCampanhaException("remoção inválida, pois esta empresa está ligada com uma ou mais campanhas");

        return empresaDAO.delete(empresa);
    }

    public boolean delete(Integer id){
        if(id == null)
            throw new EntityNotFoundException("id nulo ou não encontrado");

        boolean idNotFound = listarEmpresasUseCase.findOne(id).isEmpty();
        if(idNotFound)
            throw new EntityNotFoundException("Não há nenhuma empresa com esse id no sistema");

        boolean isEmpresaRelatedToCampanha = isEmpresaInCampanha(listarEmpresasUseCase.findOne(id).get());
        if (isEmpresaRelatedToCampanha)
            throw new EmpresaRelatedToCampanhaException("remoção inválida, pois esta empresa está ligada com uma ou mais campanhas");

        return empresaDAO.deleteByKey(id);
    }
}
