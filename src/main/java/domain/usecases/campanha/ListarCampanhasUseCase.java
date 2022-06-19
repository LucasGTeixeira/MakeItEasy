package domain.usecases.campanha;

import domain.entities.campanha.Campanha;
import domain.usecases.utils.Validator;

import java.util.List;
import java.util.Optional;

public class ListarCampanhasUseCase {
    private final CampanhaDAO campanhaDAO;

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
        if(Validator.nullOrEmpty(codigo))
            throw new IllegalArgumentException("Código não pode ser nulo");
        return campanhaDAO.findByCodigo(codigo);
    }

    public Optional<Campanha> findByCnpj(String cnpj){
        if(Validator.nullOrEmpty(cnpj))
            throw new IllegalArgumentException("Código não pode ser nulo");
        return campanhaDAO.findByCnpj(cnpj);
    }
}
