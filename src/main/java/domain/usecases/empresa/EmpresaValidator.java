package domain.usecases.empresa;

import domain.entities.empresa.Empresa;
import domain.usecases.utils.Notification;
import domain.usecases.utils.Validator;

public class EmpresaValidator extends Validator<Empresa> {
    @Override
    public Notification validate(Empresa empresa) {
        Notification notification = new Notification();

        if(empresa == null){
            notification.addError("Objeto Empresa está nulo");
            return notification;
        }
        if(nullOrEmpty(empresa.getCnpj()))
            notification.addError("Cnpj da empresa não pode ser vazio");
        if(nullOrEmpty(empresa.getRazaoSocial()))
            notification.addError("Razão social da empresa não pode estar vazia");

        return notification;
    }
}
