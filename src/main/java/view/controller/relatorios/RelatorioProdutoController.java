package view.controller.relatorios;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import view.enums.Tela;
import view.utils.MascaraUtils;
import view.utils.UILoader;

import java.io.IOException;

public class RelatorioProdutoController {


    @FXML
    private Button buttonVoltar;
    @FXML
    private TextField txtCodigo;

    @FXML
    void initialize() {
        setButtonsClickListener();setUpMasks();
    }

    private void setButtonsClickListener() {
        setActionListener(buttonVoltar);
    }

    private void setActionListener(Button button) {
        button.setOnAction(actionEvent -> {
            try {
                UILoader.substituirTela(Tela.INITIAL.getNomeTela());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
    private void setUpMasks(){
        MascaraUtils.mascaraParaNumeros(txtCodigo);
    }
}
