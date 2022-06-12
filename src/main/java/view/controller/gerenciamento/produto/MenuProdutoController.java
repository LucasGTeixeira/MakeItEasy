package view.controller.gerenciamento.produto;

import view.enums.Tela;
import view.utils.UILoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class MenuProdutoController {
    @FXML
    private Button buttonInserir;
    @FXML
    private Button buttonAlterar;
    @FXML
    private Button buttonRemover;
    @FXML
    private Button buttonVoltar;

    @FXML
    void initialize(){
       setButtonsClickListener();
    }

    private void setButtonsClickListener(){
        setActionListener(buttonInserir,Tela.UPDATE_OR_INSERIR_PRODUTO);
        setActionListener(buttonAlterar,Tela.UPDATE_OR_INSERIR_PRODUTO);
        setActionListener(buttonVoltar,Tela.INITIAL);
        setActionListener(buttonRemover,Tela.REMOVER_PRODUTO);
    }
    private void setActionListener(Button button, Tela tela){
        button.setOnAction(actionEvent -> {
            try {
                UILoader.substituirTela(tela.getNomeTela());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
