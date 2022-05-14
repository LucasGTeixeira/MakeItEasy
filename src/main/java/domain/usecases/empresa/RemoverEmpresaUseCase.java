package domain.usecases.empresa;

import domain.entities.empresa.Empresa;
import domain.usecases.utils.Exceptions.EntityNotFoundException;

public class RemoverEmpresaUseCase {

    private EmpresaDAO empresaDAO;

    public RemoverEmpresaUseCase(EmpresaDAO empresaDAO) {
        this.empresaDAO = empresaDAO;
    }

    public boolean delete(Empresa empresa){
        if(empresa == null || empresaDAO.findByCnpj(empresa.getCnpj()).isEmpty())
            throw new EntityNotFoundException("Empresa nula ou não encontrada");
        return empresaDAO.delete(empresa);
    }

    public boolean delete(Integer id){
        if(id == null || empresaDAO.findOne(id).isEmpty())
            throw new EntityNotFoundException("id nulo ou não encontrado");
        return empresaDAO.deleteByKey(id);
    }
}
