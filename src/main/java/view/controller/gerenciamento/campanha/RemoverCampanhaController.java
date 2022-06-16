package view.controller.gerenciamento.campanha;

import application.main.Main;
import domain.entities.campanha.Campanha;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import view.enums.Tela;
import view.utils.Bundle;
import view.utils.FabricaAlerts;
import view.utils.UILoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class RemoverCampanhaController {

    @FXML
    private Button buttonCancelar;

    @FXML
    private Button buttonConfirmar;
    @FXML
    private Label lblNomeCampanha;

    @FXML
    void initialize() {
        Bundle bundle = UILoader.getBundle();
        Campanha entity = (Campanha) bundle.getBundle("model");
        lblNomeCampanha.setText(entity.getNome());
        setCancelClickListener();
        setConfirmClickListener(entity);
    }

    private void setCancelClickListener() {
        setActionListener(buttonCancelar);
    }

    private void setActionListener(Button button) {
        button.setOnAction(actionEvent -> redirectToMenu());
    }

    private void redirectToMenu() {
        try {
            UILoader.getBundle().free();
            UILoader.substituirTela(Tela.MENU_CAMPANHA.getNomeTela());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setConfirmClickListener(Campanha entity) {
        buttonConfirmar.setOnAction(actionEvent -> {
            try {
                boolean success = Main.removerCampanhaUseCase.delete(entity.getId());
                if (success) showSuccessMessage();
                else showErrorMessage();
            } catch (Exception e) {
                showErrorMessage(e);
            } finally {
                redirectToMenu();
            }
        });
    }

    private void showSuccessMessage() {
        FabricaAlerts.criarAlertGenerico("Sucesso", "Campanha removida com sucesso", "Você será redirecionado(a) ao menu", Alert.AlertType.INFORMATION);
    }

    private void showErrorMessage() {
        FabricaAlerts.criarAlertGenerico("Erro", "Não foi possível remover essa campanha", "Você será redirecionado(a) ao menu", Alert.AlertType.INFORMATION);
    }

    private void showErrorMessage(Exception error) {
        FabricaAlerts.criarAlertGenerico("Erro", "Não foi possível remover essa campanha", "Erro: " + error.getMessage(), Alert.AlertType.INFORMATION);
    }
}
