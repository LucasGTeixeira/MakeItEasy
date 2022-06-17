package domain.usecases.empresa;

import domain.entities.empresa.Empresa;
import domain.usecases.utils.Exceptions.EntityAlreadyExistsException;
import domain.usecases.utils.Notification;
import domain.usecases.utils.Validator;

public class AdicinarEmpresaUseCase {
    private final EmpresaDAO empresaDAO;
    private ListarEmpresasUseCase listarEmpresasUseCase;

    public AdicinarEmpresaUseCase(EmpresaDAO empresaDAO, ListarEmpresasUseCase listarEmpresasUseCase) {
        this.empresaDAO = empresaDAO;
        this.listarEmpresasUseCase = listarEmpresasUseCase;
    }

    public Integer insert(Empresa empresa){
        Validator<Empresa> validator = new EmpresaValidator();
        Notification notification = validator.validate(empresa);

        if(notification.hasErros())
            throw new IllegalArgumentException(notification.errorMessage());

        String cnpj = empresa.getCnpj();
        boolean empresaCnpjAlreadyExists = listarEmpresasUseCase.findByCnpj(cnpj).isPresent();
        if(empresaCnpjAlreadyExists)
            throw new EntityAlreadyExistsException("Cnpj já cadastrado no sistema");

        return empresaDAO.create(empresa);
    }
}
