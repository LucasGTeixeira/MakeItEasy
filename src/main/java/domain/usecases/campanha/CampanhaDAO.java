package domain.usecases.campanha;

import domain.entities.campanha.Campanha;
import domain.usecases.utils.DAO;

import java.util.Optional;

public interface CampanhaDAO extends DAO<Campanha, Integer> {
    Optional<Campanha> findByCodigo(String codigo);

    Optional<Campanha> findByCnpj(String cnpj);
}

