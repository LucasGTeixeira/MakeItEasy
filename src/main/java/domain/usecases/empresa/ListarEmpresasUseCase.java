package domain.usecases.empresa;

import domain.entities.empresa.Empresa;

import java.util.List;
import java.util.Optional;

public class ListarEmpresasUseCase {
    private EmpresaDAO empresaDAO;

    public ListarEmpresasUseCase(EmpresaDAO empresaDAO) {
        this.empresaDAO = empresaDAO;
    }

    public Optional<Empresa> findByCnpj(String cnpj){
        if(cnpj == null){
            throw new IllegalArgumentException("Cnpj não pode ser nulo");
        }
        return empresaDAO.findByCnpj(cnpj);
    }

    public Optional<Empresa> findOne(Integer id){
        if(id == null){
            throw new IllegalArgumentException("Id não pode ser nulo");
        }
        return empresaDAO.findOne(id);
    }

    public List<Empresa> findAll(){
        return empresaDAO.findAll();
    }

}
