package view.controller.relatorios;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import view.enums.Tela;
import view.utils.MascaraUtils;
import view.utils.UILoader;

import java.io.IOException;

public class RelatorioClienteController {


    @FXML
    private Button buttonVoltar;
    @FXML
    private DatePicker dtNascimento;

    @FXML
    void initialize() {
        setButtonsClickListener();
        setUpMasks();
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
    private void setUpMasks() {
        MascaraUtils.mascaraParaData(dtNascimento);
    }
}
