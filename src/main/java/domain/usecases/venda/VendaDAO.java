package domain.usecases.venda;

import domain.entities.venda.StatusVenda;
import domain.entities.venda.Venda;
import domain.usecases.utils.DAO;

import java.util.List;
import java.util.Optional;

public interface VendaDAO extends DAO<Venda, Integer> {

    List<Venda> findVendaByStatus(StatusVenda statusVenda);

    Optional<Venda> findVendaByCpf(String cpf);

    Optional<Venda> findVendaByCodProduto(Integer codProduto);

    boolean updateStatus(Venda venda);
}
