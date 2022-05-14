package domain.usecases.empresa;

import domain.entities.campanha.Campanha;
import domain.entities.empresa.Empresa;
import domain.usecases.campanha.CampanhaDAO;
import domain.usecases.utils.Exceptions.EntityNotFoundException;

import java.util.Optional;

public class RemoverEmpresaUseCase { //TODO conferir se há campanha relacionada antes de deletar

    private EmpresaDAO empresaDAO;
    private CampanhaDAO campanhaDAO;

    public RemoverEmpresaUseCase(EmpresaDAO empresaDAO, CampanhaDAO campanhaDAO) {
        this.empresaDAO = empresaDAO;
        this.campanhaDAO = campanhaDAO;
    }

    public boolean isEmpresaInCampanha(Empresa empresa){
        return campanhaDAO.findByCnpj(empresa.getCnpj()).isEmpty();
    }

//    public boolean isEmpresaInCampanha(Integer id){
//        return true;
//    }

    public boolean delete(Empresa empresa){
        if(empresa == null || empresaDAO.findByCnpj(empresa.getCnpj()).isEmpty() || isEmpresaInCampanha(empresa))
            throw new EntityNotFoundException("Empresa nula ou não encontrada");
        return empresaDAO.delete(empresa);
    }

    public boolean delete(Integer id){ //TODO fazer verificação de empresa dentro de campanhas
        if(id == null || empresaDAO.findOne(id).isEmpty())
            throw new EntityNotFoundException("id nulo ou não encontrado");
        return empresaDAO.deleteByKey(id);
    }
}
