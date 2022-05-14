package domain.usecases.empresa;

import domain.entities.empresa.Empresa;
import domain.usecases.utils.Exceptions.EntityAlreadyExistsException;
import domain.usecases.utils.Notification;
import domain.usecases.utils.Validator;

public class AdicinarEmpresaUseCase {

    private EmpresaDAO empresaDAO;

    public AdicinarEmpresaUseCase(EmpresaDAO empresaDAO) {
        this.empresaDAO = empresaDAO;
    }

    public Integer insert(Empresa empresa){
        Validator<Empresa> validator = new EmpresaValidator();
        Notification notification = validator.validate(empresa);

        if(notification.hasErros())
            throw new IllegalArgumentException(notification.errorMessage());

        String cnpj = empresa.getCnpj();
        if(empresaDAO.findByCnpj(cnpj).isPresent())
            throw new EntityAlreadyExistsException("Cnpj já cadastrado no sistema");

        return empresaDAO.create(empresa);
    }
}
