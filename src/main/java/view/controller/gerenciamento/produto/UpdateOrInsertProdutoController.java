package view.controller.gerenciamento.produto;

import view.enums.Tela;
import view.utils.FabricaAlerts;
import view.utils.MascaraUtils;
import view.utils.UILoader;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class UpdateOrInsertProdutoController {
    @FXML
    private Button buttonCancelar;
    @FXML
    private Button buttonConfirmar;
    @FXML
    private TextField txtCodigo;

    @FXML
    void initialize(){
        setButtonsClickListener();
    }

    private void setButtonsClickListener(){
       setActionListener(buttonCancelar);
       setUpMasks();
        buttonConfirmar.setOnMouseClicked(mouseEvent -> confirm());
    }


    private void confirm(){
        FabricaAlerts.criarAlertGenerico("Sucesso","Produto salvo com sucesso","Você será redirecionado(a)  ao menu", Alert.AlertType.INFORMATION);
        try {
            UILoader.substituirTela(Tela.MENU_PRODUTO.getNomeTela());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setActionListener(Button button){
        button.setOnAction(actionEvent -> {
            try {
                UILoader.substituirTela(Tela.MENU_PRODUTO.getNomeTela());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    private void setUpMasks(){
        MascaraUtils.mascaraParaNumeros(txtCodigo);
    }
}
