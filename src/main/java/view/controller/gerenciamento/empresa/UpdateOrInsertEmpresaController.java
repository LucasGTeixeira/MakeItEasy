package view.controller.gerenciamento.empresa;

import application.main.Main;
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

    private void setActionListener(Button button) {
        button.setOnAction(actionEvent -> {
            try {
                UILoader.getBundle().free();
                UILoader.substituirTela(Tela.MENU_EMPRESA.getNomeTela());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void setButtonsClickListener() {
        Bundle bundle = UILoader.getBundle();
        setActionListener(buttonCancelar);
        setUpScreen(bundle);
    }

    private void setUpScreen(Bundle bundle) {
        ScreenMode screenMode;
        Object model = bundle.getBundle("model");
        if (model != null) {
            Empresa empresa = (Empresa) model;
            screenMode = ScreenMode.UPDATE;
            setFields(empresa);
            buttonConfirmar.setOnMouseClicked(mouseEvent -> setUpUpdateButton());
        } else {
            screenMode = ScreenMode.INSERT;
            buttonConfirmar.setOnMouseClicked(mouseEvent -> setUpInsertButton());
        }
        lblTitulo.setText(screenMode.getTitulo());
    }

    private void setFields(Empresa empresa) {
        id = empresa.getId();
        txtEmpresaCnpj.setText(empresa.getCnpj());
        txtEmpresaRazaoSocial.setText(empresa.getRazaoSocial());
    }

    private void setUpUpdateButton() {
        String cnpj = txtEmpresaCnpj.getText();
        String razao = txtEmpresaRazaoSocial.getText();
        if (cnpj.isEmpty() || razao.isEmpty()) {
            preenchaTodosOsCampos();
            return;
        }
        try {
            //todo AdaptarUC
            boolean success = Main.modificarEmpresaUseCase.update(new Empresa(id, cnpj, razao));
            if (success) {
                confirm();
            } else {
                showUpdateErrorMessage();
            }
        } catch (Exception e) {
            showUpdateErrorMessage(e);
        }
    }

    private void setUpInsertButton() {
        String cnpj = txtEmpresaCnpj.getText();
        String razao = txtEmpresaRazaoSocial.getText();
        if (cnpj.isEmpty() || razao.isEmpty()) {
            preenchaTodosOsCampos();
            return;
        }
        try {
            //todo AdaptarUC
            Main.adicinarEmpresaUseCase.insert(new Empresa(cnpj, razao));
            confirm();
        } catch (Exception e) {
            showInsertErrorMessage(e);
        }
    }


    private void confirm() throws IOException {
        successMessage();
        UILoader.getBundle().free();
        UILoader.substituirTela(Tela.MENU_EMPRESA.getNomeTela());
    }

    private void successMessage() {
        FabricaAlerts.criarAlertGenerico("Sucesso", "Empresa salva com sucesso", "Você será redirecionado(a) ao menu", Alert.AlertType.INFORMATION);
    }

    private void preenchaTodosOsCampos() {
        FabricaAlerts.criarAlertGenerico("Atenção", "Preencha todos os campos", "Todos os campos são necessários", Alert.AlertType.INFORMATION);
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
