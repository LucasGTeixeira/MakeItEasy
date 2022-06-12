package view.controller.gerenciamento.empresa;

import application.main.Main;
import domain.entities.empresa.Empresa;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import view.enums.Tela;
import view.utils.UILoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MenuEmpresaController {
    @FXML
    private Button buttonInserir;
    @FXML
    private Button buttonAlterar;
    @FXML
    private Button buttonRemover;
    @FXML
    private Button buttonVoltar;
    @FXML
    private TableView<Empresa> tbvEmpresa;
    @FXML
    private TableColumn<Empresa, String> clnId;
    @FXML
    private TableColumn<Empresa, String> clnCnpj;
    @FXML
    private TableColumn<Empresa, String> clnRazao;

    private final List<Empresa> empresas = new ArrayList<>();

    @FXML
    void initialize() {
        empresas.addAll(Main.listarEmpresasUseCase.findAll());
        configurarColunas();
        tbvEmpresa.getItems().addAll(empresas);
        setButtonsClickListener();
    }


    private void configurarColunas() {
        clnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        clnCnpj.setCellValueFactory(new PropertyValueFactory<>("cnpj"));
        clnRazao.setCellValueFactory(new PropertyValueFactory<>("razaoSocial"));
    }

    private void setButtonsClickListener() {
        setActionListener(buttonInserir, Tela.UPDATE_OR_INSERIR_EMPRESA);
        setActionListener(buttonAlterar, Tela.UPDATE_OR_INSERIR_EMPRESA);
        setActionListener(buttonVoltar, Tela.INITIAL);
        setActionListener(buttonRemover, Tela.REMOVER_EMPRESA);
    }

    private void setActionListener(Button button, Tela tela) {
        button.setOnAction(actionEvent -> {
            try {
                UILoader.substituirTela(tela.getNomeTela());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
