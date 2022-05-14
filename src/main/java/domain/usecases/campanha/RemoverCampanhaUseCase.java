package domain.usecases.campanha;

import domain.entities.campanha.Campanha;
import domain.usecases.utils.Exceptions.EntityNotFoundException;

public class RemoverCampanhaUseCase {
    private CampanhaDAO campanhaDAO;

    public RemoverCampanhaUseCase(CampanhaDAO campanhaDAO) {
        this.campanhaDAO = campanhaDAO;
    }

    public boolean delete(Campanha campanha){
        if(campanha == null || campanhaDAO.findByCodigo(campanha.getCodigo()).isEmpty())
            throw new EntityNotFoundException("Campanha nula ou não encontrada");
        return campanhaDAO.delete(campanha);
    }

    public boolean delete(Integer id){
        if(id == null || campanhaDAO.findOne(id).isEmpty())
            throw new EntityNotFoundException("Id nulo ou não encontrado");
        return campanhaDAO.deleteByKey(id);
    }
}
