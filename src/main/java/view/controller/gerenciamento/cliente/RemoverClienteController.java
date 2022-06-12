package view.controller.gerenciamento.cliente;

import view.enums.Tela;
import view.utils.UILoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class RemoverClienteController {

    @FXML
    private Button buttonCancelar;

    @FXML
    void initialize(){
        setButtonsClickListener();
    }

    private void setButtonsClickListener(){
        setActionListener(buttonCancelar);
    }
    private void setActionListener(Button button){
        button.setOnAction(actionEvent -> {
            try {
                UILoader.substituirTela(Tela.MENU_CLIENTE.getNomeTela());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
