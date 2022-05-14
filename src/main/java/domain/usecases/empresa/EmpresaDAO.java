package domain.usecases.empresa;

import domain.entities.empresa.Empresa;
import domain.usecases.utils.DAO;

import java.util.Optional;

public interface EmpresaDAO extends DAO<Empresa, Integer> {
    Optional<Empresa> findByCnpj(String cnpj);
}
