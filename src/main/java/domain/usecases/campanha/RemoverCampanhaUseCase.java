package domain.usecases.campanha;

import domain.entities.campanha.Campanha;
import domain.usecases.utils.Exceptions.EntityNotFoundException;

public class RemoverCampanhaUseCase {
    private final CampanhaDAO campanhaDAO;

    public RemoverCampanhaUseCase(CampanhaDAO campanhaDAO) {
        this.campanhaDAO = campanhaDAO;
    }

    public boolean delete(Campanha campanha){
        if(campanha == null)
            throw new IllegalArgumentException("Campanha não pode ser nula");

        String codCampanha = campanha.getCodigo();
        boolean codCampanhaNotFound = campanhaDAO.findByCodigo(codCampanha).isEmpty();
        if(codCampanhaNotFound)
            throw new EntityNotFoundException("Código da campanha não encontrado no sistema");
        return campanhaDAO.delete(campanha);
    }

    public boolean delete(Integer id){
        if(id == null)
            throw new IllegalArgumentException("Id não pode ser nulo");

        boolean idCampanhaNotFound = campanhaDAO.findOne(id).isEmpty();
        if(idCampanhaNotFound)
            throw new EntityNotFoundException("Id não encontrado");

        return campanhaDAO.deleteByKey(id);
    }
}
