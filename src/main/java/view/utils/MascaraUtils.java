package view.utils;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.util.Date;

public class MascaraUtils {

    public static void mascaraParaData(final DatePicker datePicker) {
        datePicker.getEditor().textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty() && newValue.length() > oldValue.length()) {
                int qtdCaracteresCampo = datePicker.getEditor().getLength();
                try {
                    String ultimoCaractere = datePicker.getEditor().getText().substring(qtdCaracteresCampo - 1, qtdCaracteresCampo);
                    if(!ultimoCaractere.equals("/")){
                        Integer.parseInt(ultimoCaractere);
                    }
                    datePicker.getEditor().setText(newValue);
                } catch (NumberFormatException e) {
                    datePicker.getEditor().setText(oldValue);
                    return;
                }
                try {
                    if(qtdCaracteresCampo < 11){
                        if(qtdCaracteresCampo % 3 == 0 && qtdCaracteresCampo != 9){
                            String ultimoCaractere = datePicker.getEditor().getText().substring(qtdCaracteresCampo - 1, qtdCaracteresCampo);
                            String restanteDaData = datePicker.getEditor().getText().substring(0, qtdCaracteresCampo-1);
                            newValue = restanteDaData+"/"+ultimoCaractere;
                            datePicker.getEditor().setText(newValue);
                        }
                    }else{
                        datePicker.getEditor().setText(oldValue);
                    }
                } catch (NumberFormatException e) {
                    if (!oldValue.isEmpty()) {
                        datePicker.getEditor().setText(oldValue);
                    } else {
                        datePicker.getEditor().clear();
                    }
                }
            }
        });
    }
    public static SimpleStringProperty mascaraParaDataNaTabela(Date data){
        String[] s;
        if (data != null) {
            s = data.toString().split(" ");
            String[] split = s[0].split("-");
            String s1 = split[2] + "/" + split[1] + "/" + split[0];
            return new SimpleStringProperty(s1);
        }
        return new SimpleStringProperty("");
    }

    public static void mascaraParaHora(final TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty() && newValue.length() > oldValue.length()) {
                try {
                    Integer.parseInt(newValue.replace(":", ""));
                    if (newValue.length() == 2) {
                        textField.setText(newValue + ":");
                        Platform.runLater(textField::end);
                    } else if (newValue.length() > 5) {
                        textField.setText(oldValue);
                    }

                } catch (NumberFormatException e) {
                    if (!oldValue.isEmpty()) {
                        textField.setText(oldValue);
                    } else {
                        textField.clear();
                    }
                }
            }
        });
    }

    public static void mascaraParaNumeros( TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) return;
            if (newValue.isEmpty()) return;

            try {
                Long.parseLong(newValue);
                textField.setText(newValue);
            } catch (NumberFormatException e) {
                textField.setText(oldValue);
            }
        });
    }

    public static void mascaraParaNumerosSemZero( TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) return;
            if (newValue.isEmpty()) return;
            if (newValue.equalsIgnoreCase("0")){
                textField.setText("");
                return;
            }
            try {
                Long.parseLong(newValue);
                textField.setText(newValue);
            } catch (NumberFormatException e) {
                textField.setText(oldValue);
            }
        });
    }

    public static void mascaraParaNumerosSemZeroComLimite(TextField textField, int limite) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) return;
            if (newValue.isEmpty()) return;
            if (newValue.equalsIgnoreCase("0")){
                textField.setText("");
                return;
            }
            try {
                long valor = Long.parseLong(newValue);
                if(valor <= limite){
                    textField.setText(newValue);
                }else{
                    textField.setText(oldValue);
                }
            } catch (NumberFormatException e) {
                textField.setText(oldValue);
            }
        });
    }

}
