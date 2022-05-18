package domain.usecases.venda;

import domain.entities.venda.Venda;
import domain.usecases.utils.Notification;
import domain.usecases.utils.Validator;

public class ModificarVendaUseCase { //TODO usado para modificar o status
    private final VendaDAO vendaDAO;

    public ModificarVendaUseCase(VendaDAO vendaDAO) {
        this.vendaDAO = vendaDAO;
    }

    public boolean updateStatus(Venda venda){
        Validator<Venda> validator = new VendaValidator();
        Notification notification = new Notification();

        if(notification.hasErros())
            throw new IllegalArgumentException(notification.errorMessage());

        return vendaDAO.updateStatus(venda);
    }
    public boolean update (Venda venda){
        Validator<Venda> validator = new VendaValidator();
        Notification notification = new Notification();

        if(notification.hasErros())
            throw new IllegalArgumentException(notification.errorMessage());

        return vendaDAO.update(venda);
    }
}
