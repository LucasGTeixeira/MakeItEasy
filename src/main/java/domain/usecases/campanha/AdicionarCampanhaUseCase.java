package domain.usecases.campanha;

import domain.entities.campanha.Campanha;
import domain.usecases.empresa.ListarEmpresasUseCase;
import domain.usecases.utils.Exceptions.EntityAlreadyExistsException;
import domain.usecases.utils.Exceptions.EntityNotFoundException;
import domain.usecases.utils.Notification;
import domain.usecases.utils.Validator;

public class AdicionarCampanhaUseCase {

    private final CampanhaDAO campanhaDAO;
    private final ListarEmpresasUseCase listarEmpresasUseCase;
    private final ListarCampanhasUseCase listarCampanhasUseCase;

    public AdicionarCampanhaUseCase(CampanhaDAO campanhaDAO, ListarEmpresasUseCase listarEmpresasUseCase, ListarCampanhasUseCase listarCampanhasUseCase) {
        this.campanhaDAO = campanhaDAO;
        this.listarEmpresasUseCase = listarEmpresasUseCase;
        this.listarCampanhasUseCase = listarCampanhasUseCase;
    }

    public Integer insert(Campanha campanha){
        Validator<Campanha> validator = new CampanhaValidator();
        Notification notification = validator.validate(campanha);

        if(notification.hasErros())
            throw new IllegalArgumentException(notification.errorMessage());

        String codigoOptional = campanha.getCodigo();
        boolean codigoCampanhaAlreadyExists = listarCampanhasUseCase.findByCodigo(codigoOptional).isPresent();
        if(codigoCampanhaAlreadyExists)
            throw new EntityAlreadyExistsException("Já existe uma campanha com este código");

        String cnpjEmpresaOptional = campanha.getCnpjEmpresa();
        boolean cnpjEmpresaNotFound = listarEmpresasUseCase.findByCnpj(cnpjEmpresaOptional).isEmpty();
        if(cnpjEmpresaNotFound)
            throw new EntityNotFoundException("Não há nenhuma empresa com este Cnpj");

        return campanhaDAO.create(campanha);
    }
}
