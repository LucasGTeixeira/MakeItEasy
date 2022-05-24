package domain.usecases.venda;

import domain.entities.venda.Venda;
import domain.usecases.utils.Notification;
import domain.usecases.utils.Validator;

public class VendaValidator extends Validator<Venda> {
    @Override
    public Notification validate(Venda venda) {
        Notification notification = new Notification();

        if(venda == null)
            throw new IllegalArgumentException("Objeto venda não pode ser nulo");

        if(nullOrEmpty(venda.getCpfCliente()))
            notification.addError("cpf do cliente está nulo ou vazio");
        if(isNullOrLesserEqualZero(venda.getCodProduto()))
            notification.addError("cod produto não pode ser nulo ou menor igual a zero");
        if(isNullOrLesserEqualZero(venda.getValorTotal()))
            notification.addError("valor da venda não pode ser nulo ou menor igual a zero");
        if(isNull(venda.getStatusVenda()))
            notification.addError("status da venda não pode ser nulo");
        if(isNull(venda.getFormaPagamento()))
            notification.addError("forma de pagamento não pode ser nula");
        return notification;
    }
}
