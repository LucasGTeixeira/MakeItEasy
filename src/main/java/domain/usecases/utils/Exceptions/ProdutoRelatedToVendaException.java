package domain.usecases.utils.Exceptions;

public class ProdutoRelatedToVendaException extends RuntimeException{
    public ProdutoRelatedToVendaException(String message){
        super(message);
    }
}
