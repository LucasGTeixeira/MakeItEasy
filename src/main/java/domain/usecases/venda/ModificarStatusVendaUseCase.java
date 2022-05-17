package domain.usecases.venda;

import domain.entities.venda.Venda;
import domain.usecases.cliente.ClienteDAO;
import domain.usecases.produto.ProdutoDAO;
import domain.usecases.utils.Exceptions.EntityNotFoundException;
import domain.usecases.utils.Notification;
import domain.usecases.utils.Validator;

public class ModificarStatusVendaUseCase { //TODO usado para modificar o status
    private VendaDAO vendaDAO;

    public ModificarStatusVendaUseCase(VendaDAO vendaDAO) {
        this.vendaDAO = vendaDAO;
    }

    public boolean update (Venda venda){
        Validator<Venda> validator = new VendaValidator();
        Notification notification = new Notification();

        if(notification.hasErros())
            throw new IllegalArgumentException(notification.errorMessage());

        return update(venda);
    }
}
