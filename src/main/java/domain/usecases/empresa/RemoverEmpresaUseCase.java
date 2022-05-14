package domain.usecases.empresa;

import domain.entities.campanha.Campanha;
import domain.entities.empresa.Empresa;
import domain.usecases.campanha.CampanhaDAO;
import domain.usecases.utils.Exceptions.EmpresaRelatedToCampanhaException;
import domain.usecases.utils.Exceptions.EntityNotFoundException;

import java.util.Objects;
import java.util.Optional;

public class RemoverEmpresaUseCase {

    private EmpresaDAO empresaDAO;
    private CampanhaDAO campanhaDAO;

    public RemoverEmpresaUseCase(EmpresaDAO empresaDAO, CampanhaDAO campanhaDAO) {
        this.empresaDAO = empresaDAO;
        this.campanhaDAO = campanhaDAO;
    }

    public boolean isEmpresaInCampanha(Empresa empresa){
        return campanhaDAO.findByCnpj(empresa.getCnpj()).isPresent();
    }

    public boolean delete(Empresa empresa){
        if(empresa == null || empresaDAO.findByCnpj(empresa.getCnpj()).isEmpty() || isEmpresaInCampanha(empresa))
            throw new EntityNotFoundException("Empresa nula ou não encontrada");
        return empresaDAO.delete(empresa);
    }

    public boolean delete(Integer id){
        boolean idNotFound = empresaDAO.findOne(id).isEmpty();
        if(id == null || idNotFound)
            throw new EntityNotFoundException("id nulo ou não encontrado");
        boolean isEmpresaRelatedToCampanha = isEmpresaInCampanha(empresaDAO.findOne(id).get());
        if (isEmpresaRelatedToCampanha)
            throw new EmpresaRelatedToCampanhaException("Há campanhas relacionadas com esta empresa");
        return empresaDAO.deleteByKey(id);
    }
}
