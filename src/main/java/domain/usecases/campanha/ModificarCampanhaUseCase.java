package domain.usecases.campanha;

import domain.entities.campanha.Campanha;
import domain.usecases.utils.Exceptions.EntityNotFoundException;
import domain.usecases.utils.Notification;
import domain.usecases.utils.Validator;

public class ModificarCampanhaUseCase {

    private CampanhaDAO campanhaDAO;

    public ModificarCampanhaUseCase(CampanhaDAO campanhaDAO) {
        this.campanhaDAO = campanhaDAO;
    }

    public boolean update(Campanha campanha){
        Validator<Campanha> validator = new CampanhaValidator();
        Notification notification = validator.validate(campanha);

        if(notification.hasErros())
            throw new IllegalArgumentException(notification.errorMessage());

        String codCampanha = campanha.getCodigo();
        if(campanhaDAO.findByCodigo(codCampanha).isEmpty())
            throw new EntityNotFoundException("Não há nenhuma campanha com este código");

        return campanhaDAO.update(campanha);
    }
}
