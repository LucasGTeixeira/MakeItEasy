package domain.usecases.venda;

import domain.entities.venda.StatusVenda;
import domain.entities.venda.Venda;
import domain.usecases.utils.DAO;

import java.util.List;

public interface VendaDAO extends DAO<Venda, Integer> {

    List<Venda> findVendaByStatus(StatusVenda statusVenda);

    boolean updateStatus(Venda venda);
}
