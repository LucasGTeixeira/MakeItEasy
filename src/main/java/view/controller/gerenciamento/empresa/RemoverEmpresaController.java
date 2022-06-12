package view.controller.gerenciamento.empresa;

import domain.entities.empresa.Empresa;
import view.enums.Tela;
import view.utils.Bundle;
import view.utils.UILoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class RemoverEmpresaController {
    @FXML
    private Button buttonCancelar;

    @FXML
    void initialize(){
        Bundle bundle = UILoader.getBundle();
        Empresa empresa = (Empresa) bundle.getBundle("model");
        System.out.println(empresa.getRazaoSocial());
        setButtonsClickListener();
    }

    private void setButtonsClickListener(){
        setActionListener(buttonCancelar);
    }
    private void setActionListener(Button button){
        button.setOnAction(actionEvent -> {
            try {
                UILoader.substituirTela(Tela.MENU_EMPRESA.getNomeTela());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
