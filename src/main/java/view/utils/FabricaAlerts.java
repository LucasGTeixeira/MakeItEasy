package view.utils;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Optional;

public class FabricaAlerts {

    public static void criarAlertErro(Throwable e) {
        Platform.runLater(() -> {

            if (e == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Aconteceu algum problema!");
                alert.setHeaderText("Erro!");
                alert.setContentText("Caso o problema persista entre \n" +
                        "em contato com os desenvolvedores");
                alert.showAndWait();
                return;
            }
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Aconteceu algum problema!");
            alert.setHeaderText(e.getMessage());
            alert.setContentText(
                    (e.getCause() != null ? e.getCause().getMessage() : "") +
                            "\nErro! Caso o problema persista entre \n" +
                            "em contato com os desenvolvedores");
            alert.showAndWait();
        });
    }

    public static void criarAlertGenerico(String titulo, String textoCabecalho, String conteudo, Alert.AlertType tipoAlerta) {
        Alert alert = new Alert(tipoAlerta);
        alert.setTitle(titulo);
        alert.setHeaderText(textoCabecalho);
        alert.setContentText(conteudo);
        alert.showAndWait();
    }

    public static void criarAlertWarning(Throwable e, String mensagem) {
        Platform.runLater(() -> {
            if (e == null) {
                criarAlertWarning(mensagem);
                return;
            }
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Aconteceu algum problema!");
            alert.setHeaderText(e.getMessage());
            alert.setContentText(
                    (e.getCause() != null ? e.getCause().getMessage() + "\n" : "") +
                            "\nCaso o problema persista entre \n" +
                            "em contato com os desenvolvedores");
            alert.showAndWait();
        });
    }

    public static void criarAlertWarning( String mensagem) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aconteceu algum problema!");
            alert.setHeaderText(mensagem);
            alert.showAndWait();
        });
    }

    public static boolean criarAlertDeConfirmacao( String titulo,  String textoCabecalho,  String conteudo) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("images/pacote.png"));
        alert.setTitle(titulo);
        alert.setHeaderText(textoCabecalho);
        try {
            alert.setContentText(conteudo);
        } catch (Throwable throwable) {
            criarAlertErro(throwable);
            throwable.printStackTrace();
        }

        Optional<ButtonType> result = alert.showAndWait();

        return result.filter(buttonType -> buttonType == ButtonType.OK).isPresent();
    }
}
