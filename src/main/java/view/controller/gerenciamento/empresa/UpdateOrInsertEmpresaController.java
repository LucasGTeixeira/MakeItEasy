package view.controller.gerenciamento.empresa;

import domain.entities.empresa.Empresa;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import view.enums.Tela;
import view.utils.Bundle;
import view.utils.FabricaAlerts;
import view.utils.ScreenMode;
import view.utils.UILoader;

import java.io.IOException;

public class UpdateOrInsertEmpresaController {
    @FXML
    private Button buttonCancelar;
    @FXML
    private Button buttonConfirmar;
    @FXML
    private Label lblTitulo;
    @FXML
    private TextField txtEmpresaCnpj;
    @FXML
    private TextField txtEmpresaRazaoSocial;

    private int id;

    @FXML
    void initialize() {
        setButtonsClickListener();
    }

    private void setButtonsClickListener() {
        Bundle bundle = UILoader.getBundle();
        setUpScreen(bundle);
        setActionListener(buttonCancelar);
    }

    private void setUpScreen(Bundle bundle) {
        ScreenMode screenMode;
        if (bundle != null) {
            Empresa empresa = (Empresa) bundle.getBundle("model");;
            screenMode = ScreenMode.UPDATE;

            id = empresa.getId();
            txtEmpresaCnpj.setText(empresa.getCnpj());
            txtEmpresaRazaoSocial.setText(empresa.getRazaoSocial());
            setUpUpdateButton();
        } else {
            screenMode = ScreenMode.INSERT;
            setUpInsertButton();
        }
        lblTitulo.setText(screenMode.getTitulo());
    }

    private void setUpUpdateButton() {
        buttonConfirmar.setOnMouseClicked(mouseEvent -> {
            String cnpj = txtEmpresaCnpj.getText();
            String razao = txtEmpresaRazaoSocial.getText();
            if (cnpj.isEmpty() || razao.isEmpty()) {
                preenchaTodosOsCampos();
                return;
            }
            try {
                //todo adaptar
                // boolean success = Main.modificarEmpresaUseCase.update(id,cnpj,razao);
                boolean success = true;
                if (success) {
                    confirm();
                } else {
                    showUpdateErrorMessage();
                }
            } catch (Exception e) {
                showUpdateErrorMessage(e);
            }
        });
    }

    private void setUpInsertButton() {
        buttonConfirmar.setOnMouseClicked(mouseEvent -> {
            String cnpj = txtEmpresaCnpj.getText();
            String razao = txtEmpresaRazaoSocial.getText();
            if (cnpj.isEmpty() || razao.isEmpty()) {
                preenchaTodosOsCampos();
                return;
            }
            try {
                //todo adaptar
                // boolean success = Main.adicinarEmpresaUseCase.update(cnpj,razao);
                boolean success = true;
                if (success) {
                    confirm();
                } else {
                    showInsertErrorMessage();
                }
            } catch (Exception e) {
                showInsertErrorMessage(e);
            }
        });
    }


    private void confirm() {
        FabricaAlerts.criarAlertGenerico("Sucesso", "Empresa salva com sucesso", "Você será redirecionado(a) ao menu", Alert.AlertType.INFORMATION);
        try {
            UILoader.substituirTela(Tela.MENU_EMPRESA.getNomeTela());
            UILoader.freeBundle();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setActionListener(Button button) {
        button.setOnAction(actionEvent -> {
            try {
                UILoader.substituirTela(Tela.MENU_EMPRESA.getNomeTela());
                UILoader.freeBundle();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void preenchaTodosOsCampos() {
        FabricaAlerts.criarAlertGenerico("Atenção", "Preencha todos os campos", "Todos os campos são necessários", Alert.AlertType.INFORMATION);
    }

    private void showInsertErrorMessage() {
        FabricaAlerts.criarAlertGenerico("Erro", "Não foi possível inserir essa empresa", "Você será redirecionado(a) ao menu", Alert.AlertType.INFORMATION);
    }

    private void showInsertErrorMessage(Exception error) {
        FabricaAlerts.criarAlertGenerico("Erro", "Não foi possível inserir essa empresa", "Erro: " + error.getMessage(), Alert.AlertType.INFORMATION);
    }

    private void showUpdateErrorMessage() {
        FabricaAlerts.criarAlertGenerico("Erro", "Não foi possível atualizar essa empresa", "Você será redirecionado(a) ao menu", Alert.AlertType.INFORMATION);
    }

    private void showUpdateErrorMessage(Exception error) {
        FabricaAlerts.criarAlertGenerico("Erro", "Não foi possível atualizar essa empresa", "Erro: " + error.getMessage(), Alert.AlertType.INFORMATION);
    }

}
