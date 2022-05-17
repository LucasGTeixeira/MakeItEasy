package domain.usecases.venda;

import domain.entities.venda.Venda;

import java.util.List;
import java.util.Optional;

public class ListarVendasUseCase {
    private VendaDAO vendaDAO;

    public ListarVendasUseCase(VendaDAO vendaDAO) {
        this.vendaDAO = vendaDAO;
    }

    public Optional<Venda> findOne(Integer id){
        if(id == null)
            throw new IllegalArgumentException("id não pode ser nulo");
        return vendaDAO.findOne(id);
    }

    public List<Venda> findAll(){
        return vendaDAO.findAll();
    }
}
