package view.controller.gerenciamento.empresa;

import application.main.Main;
import domain.entities.empresa.Empresa;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import view.enums.Tela;
import view.utils.Bundle;
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
    @FXML
    private Label labelEmpresaId;
    @FXML
    private Label labelEmpresaCnpj;
    @FXML
    private Label labelEmpresaRazaoSocial;
    @FXML
    private Label lblSelect;
    @FXML
    private GridPane gridDetails;

    private final List<Empresa> empresas = new ArrayList<>();

    @FXML
    void initialize() {
        showGrid(false);
        empresas.addAll(Main.listarEmpresasUseCase.findAll());
        configurarColunas();
        tbvEmpresa.getItems().addAll(empresas);
        setButtonsClickListener();
        tbvEmpresa.getSelectionModel().selectedItemProperty().addListener((observableValue, campanhaTableViewSelectionModel, item) -> {
            showDetails(item);
            showGrid(true);
        });
    }

    private void showGrid(boolean show) {
        lblSelect.setVisible(!show);
        lblSelect.setManaged(!show);
        gridDetails.setVisible(show);
        gridDetails.setManaged(show);
        buttonAlterar.setDisable(!show);
        buttonRemover.setDisable(!show);
    }

    private void showDetails(Empresa empresa) {
        labelEmpresaCnpj.setText(empresa.getCnpj());
        labelEmpresaId.setText(empresa.getId().toString());
        labelEmpresaRazaoSocial.setText(empresa.getRazaoSocial());
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
                Bundle bundle = new Bundle();
                bundle.setBundle("model", tbvEmpresa.getSelectionModel().selectedItemProperty().getValue());
                UILoader.substituirTela(tela.getNomeTela(), bundle);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
