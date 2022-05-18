package domain.usecases.campanha;

import domain.entities.campanha.Campanha;
import domain.usecases.empresa.EmpresaDAO;
import domain.usecases.utils.Exceptions.EntityAlreadyExistsException;
import domain.usecases.utils.Exceptions.EntityNotFoundException;
import domain.usecases.utils.Notification;
import domain.usecases.utils.Validator;

public class AdicionarCampanhaUseCase {

    private final CampanhaDAO campanhaDAO;
    private final EmpresaDAO empresaDAO;

    public AdicionarCampanhaUseCase(CampanhaDAO campanhaDAO, EmpresaDAO empresaDAO) {
        this.campanhaDAO = campanhaDAO;
        this.empresaDAO = empresaDAO;
    }

    public Integer insert(Campanha campanha){
        Validator<Campanha> validator = new CampanhaValidator();
        Notification notification = validator.validate(campanha);

        if(notification.hasErros())
            throw new IllegalArgumentException(notification.errorMessage());

        String codigoOptional = campanha.getCodigo();
        boolean codigoCampanhaAlreadyExists = campanhaDAO.findByCodigo(codigoOptional).isPresent();
        if(codigoCampanhaAlreadyExists)
            throw new EntityAlreadyExistsException("Já existe uma campanha com este código");

        String cnpjEmpresaOptional = campanha.getCnpjEmpresa();
        boolean cnpjEmpresaNotFound = empresaDAO.findByCnpj(cnpjEmpresaOptional).isEmpty();
        if(cnpjEmpresaNotFound)
            throw new EntityNotFoundException("Não há nenhuma empresa com este Cnpj");

        return campanhaDAO.create(campanha);
    }
}
