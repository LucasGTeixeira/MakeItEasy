package domain.usecases.campanha;

import domain.entities.campanha.Campanha;
import domain.usecases.empresa.EmpresaDAO;
import domain.usecases.utils.Exceptions.EntityAlreadyExistsException;
import domain.usecases.utils.Exceptions.EntityNotFoundException;
import domain.usecases.utils.Notification;
import domain.usecases.utils.Validator;

public class AdicionarCampanhaUseCase {

    private CampanhaDAO campanhaDAO;
    private EmpresaDAO empresaDAO;

    public AdicionarCampanhaUseCase(CampanhaDAO campanhaDAO, EmpresaDAO empresaDAO) {
        this.campanhaDAO = campanhaDAO;
        this.empresaDAO = empresaDAO;
    }

    public Integer insert(Campanha campanha){
        Validator<Campanha> validator = new CampanhaValidator();
        Notification notification = validator.validate(campanha);

        if(notification.hasErros())
            throw new IllegalArgumentException(notification.errorMessage());

        if(campanhaDAO.findByCodigo(campanha.getCodigo()).isPresent())
            throw new EntityAlreadyExistsException("Já existe uma campanha com este código");

        if(empresaDAO.findByCnpj(campanha.getCnpjEmpresa()).isEmpty())
            throw new EntityNotFoundException("Não há nenhuma empresa com este Cnpj");

        return campanhaDAO.create(campanha);
    }
}
