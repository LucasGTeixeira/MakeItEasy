package domain.usecases.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Notification {
    private List<Error> errors = new ArrayList<>();

    public void addError(String message){
        addError(message, null);
    }

    public void addError(String message, Exception e){
        errors.add(new Error(message, e));
    } // sobrecarga para retornar também uma exception

    public boolean isCorrect(){
        return errors.isEmpty();
    } //sem erros

    public boolean hasErros(){
        return ! isCorrect();
    }

    public String errorMessage(){
        return errors
                .stream()
                .map(e -> e.message)//troca o erro pela mensagem dele
                .collect(Collectors.joining(", "));
    }

    private static class Error{ //criada internamente pois possui uso apenas dentro desta classe
        String message;
        Exception cause;

        public Error(String message, Exception cause) {
            this.message = message;
            this.cause = cause;
        }
    }
}
