package domain.usecases.venda;

import domain.entities.venda.Venda;
import domain.usecases.utils.Exceptions.EntityNotFoundException;

public class RemoverVendaUseCase {
    private final VendaDAO vendaDAO;

    public RemoverVendaUseCase(VendaDAO vendaDAO) {
        this.vendaDAO = vendaDAO;
    }

    public boolean delete(Integer id){
        if(id == null)
            throw new IllegalArgumentException("id nulo ou não encontrado");

        boolean idVendaNotFound = vendaDAO.findOne(id).isEmpty();
        if(idVendaNotFound)
            throw new EntityNotFoundException("Não há nenhuma venda com esse Id");

        return vendaDAO.deleteByKey(id);
    }
}
