package view.controller.gerenciamento.cliente;

import application.main.Main;
import domain.entities.cliente.Cliente;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import view.enums.Tela;
import view.utils.Bundle;
import view.utils.FabricaAlerts;
import view.utils.UILoader;

import java.io.IOException;

public class RemoverClienteController {
    @FXML
    private Button buttonCancelar;
    @FXML
    private Button buttonConfirmar;
    @FXML
    private Label lblNomeCliente;


    @FXML
    void initialize() {
        Bundle bundle = UILoader.getBundle();
        Cliente entity = (Cliente) bundle.getBundle("model");
        lblNomeCliente.setText(entity.getNomeCompleto());
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
            UILoader.substituirTela(Tela.MENU_CLIENTE.getNomeTela());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setConfirmClickListener(Cliente entity) {
        buttonConfirmar.setOnAction(actionEvent -> {
            try {
                boolean success = Main.removerClienteUseCase.delete(entity.getId());
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
        FabricaAlerts.criarAlertGenerico("Sucesso", "Sucesso na remoção de cliente", "Você será redirecionado(a) ao menu", Alert.AlertType.INFORMATION);
    }

    private void showErrorMessage() {
        FabricaAlerts.criarAlertGenerico("Erro", "Não foi possível executar remoção de cliente", "Você será redirecionado(a) ao menu", Alert.AlertType.INFORMATION);
    }

    private void showErrorMessage(Exception error) {
        FabricaAlerts.criarAlertGenerico("Erro", "Não foi possível executar remoção de cliente", "Erro: " + error.getMessage(), Alert.AlertType.INFORMATION);
    }


}
