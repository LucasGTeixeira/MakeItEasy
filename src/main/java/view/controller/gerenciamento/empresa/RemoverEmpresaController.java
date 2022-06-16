package view.controller.gerenciamento.empresa;

import application.main.Main;
import domain.entities.empresa.Empresa;
import domain.usecases.utils.Exceptions.EmpresaRelatedToCampanhaException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import view.enums.Tela;
import view.utils.Bundle;
import view.utils.FabricaAlerts;
import view.utils.UILoader;

import java.io.IOException;

public class RemoverEmpresaController {
    @FXML
    private Button buttonCancelar;
    @FXML
    private Button buttonConfirmar;
    @FXML
    private Label lblNomeEmpresa;


    @FXML
    void initialize() {
        Bundle bundle = UILoader.getBundle();
        Empresa entity = (Empresa) bundle.getBundle("model");
        lblNomeEmpresa.setText(entity.getRazaoSocial());
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
            UILoader.substituirTela(Tela.MENU_EMPRESA.getNomeTela());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setConfirmClickListener(Empresa entity) {
        buttonConfirmar.setOnAction(actionEvent -> {
            try {
                boolean success = Main.removerEmpresaUseCase.delete(entity.getId());
                if (success) showSuccessMessage();
                else showErrorMessage();
            } catch (EmpresaRelatedToCampanhaException e) {
                showErrorMessage(e);
            } finally {
                redirectToMenu();
            }
        });
    }

    private void showSuccessMessage() {
        FabricaAlerts.criarAlertGenerico("Sucesso", "Empresa removida com sucesso", "Você será redirecionado(a) ao menu", Alert.AlertType.INFORMATION);
    }

    private void showErrorMessage() {
        FabricaAlerts.criarAlertGenerico("Erro", "Não foi possível remover essa empresa", "Você será redirecionado(a) ao menu", Alert.AlertType.INFORMATION);
    }

    private void showErrorMessage(EmpresaRelatedToCampanhaException error) {
        FabricaAlerts.criarAlertGenerico("Erro", "Não foi possível remover essa empresa", "Erro: " + error.getMessage(), Alert.AlertType.INFORMATION);
    }

}
