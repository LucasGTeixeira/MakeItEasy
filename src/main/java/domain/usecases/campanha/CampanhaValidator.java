package domain.usecases.campanha;

import domain.entities.campanha.Campanha;
import domain.usecases.utils.Notification;
import domain.usecases.utils.Validator;

public class CampanhaValidator extends Validator<Campanha> {
    @Override
    public Notification validate(Campanha campanha) {
        Notification notification = new Notification();

        if(campanha == null){
            notification.addError("Objeto Campanha está nulo");
            return notification;
        }
        if(nullOrEmpty(campanha.getCodigo()))
            notification.addError("Codigo da campanha não pode estar nulo ou vazio");
        if(nullOrEmpty(campanha.getEdicao()))
            notification.addError("Edição não pode ser nula ou vazia");
        if(isNull(campanha.getDataExpiracao()))
            notification.addError("Data de expiração não pode ser nula");
        if(isNull(campanha.getDataLancamento()))
            notification.addError("Data de Lançamento não pode ser nula");
        if(nullOrEmpty(campanha.getNome()))
            notification.addError("Nome não pode ser nulo ou vazio");
        if(nullOrEmpty(campanha.getCnpjEmpresa()))
            notification.addError("Cnpj da empresa relacionada não pode ser nulo ou vazio");

        return notification;
    }
}
