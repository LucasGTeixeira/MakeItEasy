package domain.usecases.produto;

import domain.entities.produto.Produto;
import domain.usecases.utils.Notification;
import domain.usecases.utils.Validator;

public class ProdutoValidator extends Validator<Produto> {
    @Override
    public Notification validate(Produto produto) {
        Notification notification = new Notification();

        if(produto == null){
            notification.addError("Objeto Produto está nulo");
            return notification;
        }

        if(nullOrEmpty(produto.getNome()))
            notification.addError("Nome do produto não pode ser nulo ou vazio");
        if(isNull(produto.getCategoria()))
            notification.addError("Categoria do produto não pode ser nula");
        if(nullOrEmpty(produto.getCodCampanha()))
            notification.addError("Codigo da Campanha não pode ser nula ou vazio");
        if(isNullOrLesserEqualZero(produto.getCodProduto()))
            notification.addError("Codigo do produto não pode ser nulo");
        if(isNullOrLesserEqualZero(produto.getValor()))
            notification.addError("Valor produto não pode ser nulo ou menor igual a zero");
        if(isNull(produto.getDisponibilidade()))
            notification.addError("Disponibilidade da Campanha não pode ser nula");

        return notification;



    }
}
