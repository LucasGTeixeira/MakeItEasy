package view.controller.gerenciamento.produto;

import application.main.Main;
import domain.entities.produto.Produto;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import view.enums.Tela;
import view.utils.Bundle;
import view.utils.FabricaAlerts;
import view.utils.UILoader;

import java.io.IOException;

public class RemoverProdutoController {
    @FXML
    private Button buttonCancelar;
    @FXML
    private Button buttonConfirmar;
    @FXML
    private Label lblNomeProduto;
    @FXML
    private Label lblCodProduto;


    @FXML
    void initialize() {
        Bundle bundle = UILoader.getBundle();
        Produto entity = (Produto) bundle.getBundle("model");
        lblNomeProduto.setText(entity.getNome());
        lblCodProduto.setText(entity.getCodProduto().toString());
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
            UILoader.substituirTela(Tela.MENU_PRODUTO.getNomeTela());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setConfirmClickListener(Produto entity) {
        buttonConfirmar.setOnAction(actionEvent -> {
            try {
                boolean success = Main.removerProdutoUseCase.delete(entity.getId());
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
        FabricaAlerts.criarAlertGenerico("Sucesso", "Sucesso na remoção do produto", "Você será redirecionado(a) ao menu", Alert.AlertType.INFORMATION);
    }

    private void showErrorMessage() {
        FabricaAlerts.criarAlertGenerico("Erro", "Não foi possível executar remoção do produto", "Você será redirecionado(a) ao menu", Alert.AlertType.INFORMATION);
    }

    private void showErrorMessage(Exception error) {
        FabricaAlerts.criarAlertGenerico("Erro", "Não foi possível executar remoção do produto", "Erro: " + error.getMessage(), Alert.AlertType.INFORMATION);
    }

}
