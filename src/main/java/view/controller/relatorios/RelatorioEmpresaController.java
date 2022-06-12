package view.controller.relatorios;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import view.enums.Tela;
import view.utils.UILoader;

import java.io.IOException;

public class RelatorioEmpresaController {


    @FXML
    private Button buttonVoltar;

    @FXML
    void initialize() {
        setButtonsClickListener();
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
}
