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

    public boolean delete(String cnpj){
        if(cnpj == null || empresaDAO.findByCnpj(cnpj).isEmpty())
            throw new EntityNotFoundException("Cnpj nulo ou não encontrado");
        return empresaDAO.deleteByKey(cnpj);
    }
}
