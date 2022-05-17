package domain.usecases.venda;

import domain.entities.venda.Venda;
import domain.usecases.utils.Exceptions.EntityNotFoundException;

public class RemoverVendaUseCase {
    private VendaDAO vendaDAO;

    public RemoverVendaUseCase(VendaDAO vendaDAO) {
        this.vendaDAO = vendaDAO;
    }

    public boolean delete(Venda venda){
        if(venda == null)
            throw new IllegalArgumentException("Objeto venda não pode ser nulo");
        return vendaDAO.delete(venda);
    }

    public boolean delete(Integer id){
        if(id == null || vendaDAO.findOne(id).isEmpty())
            throw new EntityNotFoundException("id nulo ou não encontrado");
        return vendaDAO.deleteByKey(id);
    }
}
