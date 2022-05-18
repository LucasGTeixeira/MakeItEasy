package domain.usecases.empresa;

import domain.entities.empresa.Empresa;
import domain.usecases.utils.Exceptions.EntityNotFoundException;
import domain.usecases.utils.Notification;
import domain.usecases.utils.Validator;

public class ModificarEmpresaUseCase {

    private final EmpresaDAO empresaDAO;

    public ModificarEmpresaUseCase(EmpresaDAO empresaDAO) {
        this.empresaDAO = empresaDAO;
    }

    public boolean update(Empresa empresa){
        Validator<Empresa> validator = new EmpresaValidator();
        Notification notification = validator.validate(empresa);

        if(notification.hasErros())
            throw new IllegalArgumentException(notification.errorMessage());

        String cnpj = empresa.getCnpj();
        boolean empresaCnpjNotFound = empresaDAO.findByCnpj(cnpj).isEmpty();
        if (empresaCnpjNotFound){
            throw new EntityNotFoundException("Não existe nenhuma empresa com esse Cnpj cadastrada no sistema");
        }

        return empresaDAO.update(empresa);
    }
}
