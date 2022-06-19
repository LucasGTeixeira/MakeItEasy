package domain.usecases.campanha;

import domain.entities.campanha.Campanha;
import domain.usecases.utils.Exceptions.EntityNotFoundException;
import domain.usecases.utils.Notification;
import domain.usecases.utils.Validator;

public class ModificarCampanhaUseCase {
    private final ListarCampanhasUseCase listarCampanhasUseCase;
    private final CampanhaDAO campanhaDAO;

    public ModificarCampanhaUseCase(ListarCampanhasUseCase listarCampanhasUseCase, CampanhaDAO campanhaDAO) {
        this.listarCampanhasUseCase = listarCampanhasUseCase;
        this.campanhaDAO = campanhaDAO;
    }

    public boolean update(Campanha campanha){
        Validator<Campanha> validator = new CampanhaValidator();
        Notification notification = validator.validate(campanha);

        if(notification.hasErros())
            throw new IllegalArgumentException(notification.errorMessage());

        String codCampanha = campanha.getCodigo();  //VERIFICAR SE BUSCA/VERIFCA SE EXISTE PELO CODIGO OU PELO ID
        boolean codCampanhaNotFound = listarCampanhasUseCase.findByCodigo(codCampanha).isEmpty();
        if(codCampanhaNotFound)
            throw new EntityNotFoundException("Não há nenhuma campanha com este código");

        return campanhaDAO.update(campanha);
    }
}
