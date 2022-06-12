package view.controller.gerenciamento.cliente;

import view.enums.Tela;
import view.utils.FabricaAlerts;
import view.utils.MascaraUtils;
import view.utils.UILoader;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;

import java.io.IOException;

public class UpdateOrInsertClienteController {
    @FXML
    private Button buttonCancelar;
    @FXML
    private Button buttonConfirmar;
    @FXML
    private DatePicker dtNascimento;

    @FXML
    void initialize() {
        setButtonsClickListener();
        setUpMasks();
    }

    private void setButtonsClickListener() {
        setActionListener(buttonCancelar);
        buttonConfirmar.setOnMouseClicked(mouseEvent -> confirm());
    }

    private void confirm() {
        FabricaAlerts.criarAlertGenerico("Sucesso", "Cliente salvo com sucesso", "Você será redirecionado(a)  ao menu", Alert.AlertType.INFORMATION);
        try {
            UILoader.substituirTela(Tela.MENU_CLIENTE.getNomeTela());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setActionListener(Button button) {
        button.setOnAction(actionEvent -> {
            try {
                UILoader.substituirTela(Tela.MENU_CLIENTE.getNomeTela());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void setUpMasks(){
        MascaraUtils.mascaraParaData(dtNascimento);
    }
}
