package domain.usecases.cliente;

import domain.entities.cliente.Cliente;
import domain.usecases.utils.Notification;
import domain.usecases.utils.Validator;

public class ClienteValidator extends Validator<Cliente> {
    @Override
    public Notification validate(Cliente cliente) {
        Notification notification = new Notification();

        if(cliente == null){
            notification.addError("Objeto Cliente está nulo");
            return notification;
        }
        if(nullOrEmpty(cliente.getCpf()))
            notification.addError("Campo CPF está vazio ou nulo");
        if(nullOrEmpty(cliente.getEmail()))
            notification.addError("Campo Email está vazio ou nulo");
        if(nullOrEmpty(cliente.getEndereco()))
            notification.addError("Campo Endereço está vazio ou nulo");
        if(nullOrEmpty(cliente.getStatus()))
            notification.addError("Campo status está nulo");
        if(nullOrEmpty(cliente.getDataNascimento()))
            notification.addError("Campo data nascimento está nulo");
        if(nullOrEmpty(cliente.getNomeCompleto()))
            notification.addError("Campo Nome está vazio ou nulo");
        if(nullOrEmpty(cliente.getTelefone()))
            notification.addError("Campo Telefone está vazio ou nulo");

        return notification;
    }
}
