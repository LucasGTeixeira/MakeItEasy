package domain.usecases.empresa;

import domain.entities.empresa.Empresa;
import domain.usecases.campanha.CampanhaDAO;
import domain.usecases.utils.Exceptions.EmpresaRelatedToCampanhaException;
import domain.usecases.utils.Exceptions.EntityNotFoundException;


public class RemoverEmpresaUseCase {

    private final EmpresaDAO empresaDAO;
    private final CampanhaDAO campanhaDAO;

    public RemoverEmpresaUseCase(EmpresaDAO empresaDAO, CampanhaDAO campanhaDAO) {
        this.empresaDAO = empresaDAO;
        this.campanhaDAO = campanhaDAO;
    }

    public boolean isEmpresaInCampanha(Empresa empresa){
        return campanhaDAO.findByCnpj(empresa.getCnpj()).isPresent();
    }

    public boolean delete(Empresa empresa){
        if(empresa == null)
            throw new IllegalArgumentException("Empresa não pode ser nula");

        String empresaCnpj = empresa.getCnpj();
        boolean empresaCnpjNotFound = empresaDAO.findByCnpj(empresaCnpj).isEmpty();
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

        boolean idNotFound = empresaDAO.findOne(id).isEmpty();
        if(idNotFound)
            throw new EntityNotFoundException("Não há nenhuma empresa com esse id no sistema");

        boolean isEmpresaRelatedToCampanha = isEmpresaInCampanha(empresaDAO.findOne(id).get());
        if (isEmpresaRelatedToCampanha)
            throw new EmpresaRelatedToCampanhaException("remoção inválida, pois esta empresa está ligada com uma ou mais campanhas");

        return empresaDAO.deleteByKey(id);
    }
}
