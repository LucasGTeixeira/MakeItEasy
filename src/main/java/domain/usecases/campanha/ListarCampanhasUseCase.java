package domain.usecases.campanha;

import domain.entities.campanha.Campanha;

import java.util.List;
import java.util.Optional;

public class ListarCampanhasUseCase {
    private CampanhaDAO campanhaDAO;

    public ListarCampanhasUseCase(CampanhaDAO campanhaDAO) {
        this.campanhaDAO = campanhaDAO;
    }

    public List<Campanha> findAll(){
        return campanhaDAO.findAll();
    }

    public Optional<Campanha> findOne(Integer id){
        if (id == null)
            throw new IllegalArgumentException("Id de campanha não pode ser nulo");
        return campanhaDAO.findOne(id);
    }

    public Optional<Campanha> findByCodigo(String codigo){
        if(codigo == null)
            throw new IllegalArgumentException("Código não pode ser nulo");
        return campanhaDAO.findByCodigo(codigo);
    }
}
