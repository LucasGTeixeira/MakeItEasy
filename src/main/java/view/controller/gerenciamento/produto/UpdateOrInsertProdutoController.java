package view.controller.gerenciamento.produto;

import application.main.Main;
import domain.entities.campanha.Campanha;
import domain.entities.produto.CategoriaProdutos;
import domain.entities.produto.Produto;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import view.enums.Tela;
import view.utils.*;

import java.io.IOException;

public class UpdateOrInsertProdutoController {
    @FXML
    private Button buttonCancelar;
    @FXML
    private Button buttonConfirmar;
    @FXML
    private TextField txtCodigo;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtValor;
    @FXML
    private CheckBox ckDisponivel;
    @FXML
    private ComboBox<CategoriaProdutos> cbbCategoria;
    @FXML
    private ComboBox<Campanha> cbbCampanha;
    @FXML
    private Label lblTitulo;

    private int id;

    @FXML
    void initialize() {
        setButtonsClickListener();
        Bundle bundle = UILoader.getBundle();
        setUpScreen(bundle);
    }

    private void setButtonsClickListener() {
        setActionListener(buttonCancelar);
        setUpMasks();
    }


    private void confirm() throws IOException {
        successMessage();
        UILoader.getBundle().free();
        UILoader.substituirTela(Tela.MENU_PRODUTO.getNomeTela());
    }

    private void setActionListener(Button button) {
        button.setOnAction(actionEvent -> {
            try {
                UILoader.getBundle().free();
                UILoader.substituirTela(Tela.MENU_PRODUTO.getNomeTela());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }



    private void setUpScreen(Bundle bundle) {
        ScreenMode screenMode;
        Object model = bundle.getBundle("model");
        if (model != null) {
            Produto produto = (Produto) model;
            screenMode = ScreenMode.UPDATE;
            id = produto.getId();
            setFields(produto);
            buttonConfirmar.setOnMouseClicked(mouseEvent -> setUpUpdateButton());
        } else {
            screenMode = ScreenMode.INSERT;
            buttonConfirmar.setOnMouseClicked(mouseEvent -> setUpInsertButton());
            cbbCategoria.getItems().addAll(CategoriaProdutos.values());
            cbbCategoria.getSelectionModel().selectFirst();
            cbbCampanha.getItems().addAll(Main.listarCampanhasUseCase.findAll());
        }
        lblTitulo.setText(screenMode.getTitulo());
    }

    private void setFields(Produto produto) {
        txtCodigo.setText(produto.getCodProduto().toString());
        txtNome.setText(produto.getNome());
        txtValor.setText(produto.getValor().toString());
        ckDisponivel.setSelected(produto.getDisponibilidade());
        cbbCategoria.getItems().addAll(CategoriaProdutos.values());
        cbbCampanha.getItems().addAll(Main.listarCampanhasUseCase.findAll());
        cbbCampanha.getSelectionModel().select(Main.listarCampanhasUseCase.findByCodigo(produto.getCodCampanha()).orElseThrow());
        cbbCategoria.getItems().addAll(CategoriaProdutos.values());
        cbbCategoria.getSelectionModel().select(produto.getCategoria());
    }

    private void setUpUpdateButton() {
        if (isAnyFieldEmpty()) {
            preenchaTodosOsCampos();
            return;
        }
        try {
            //todo adaptar
            // boolean success = Main.modificarProdutoUseCase.update();
            boolean success = true;
            if (success) {
                confirm();
            } else {
                showUpdateErrorMessage();
            }
        } catch (Exception e) {
            showUpdateErrorMessage(e);
        }
    }

    private void setUpInsertButton() {
        if (isAnyFieldEmpty()) {
            preenchaTodosOsCampos();
            return;
        }
        try {
            //todo adaptar
            // boolean success = Main.adicinarProdutoUseCase.insert();
            boolean success = true;
            if (success) {
                confirm();
            } else {
                showInsertErrorMessage();
            }
        } catch (Exception e) {
            showInsertErrorMessage(e);
        }
    }

    boolean isAnyFieldEmpty() {
        return txtNome.getText().isEmpty() ||
                txtNome.getText().isEmpty() ||
                txtValor.getText().isEmpty() ||
                cbbCampanha.getSelectionModel().getSelectedItem() == null ||
                cbbCategoria.getSelectionModel().getSelectedItem() == null;
    }

    private void setUpMasks() {
        MascaraUtils.mascaraParaNumeros(txtCodigo);
    }

    private void successMessage() {
        FabricaAlerts.criarAlertGenerico("Sucesso", "Produto salvo com sucesso", "Você será redirecionado(a)  ao menu", Alert.AlertType.INFORMATION);
    }

    private void preenchaTodosOsCampos() {
        FabricaAlerts.criarAlertGenerico("Atenção", "Preencha todos os campos", "Todos os campos são necessários", Alert.AlertType.INFORMATION);
    }

    private void showInsertErrorMessage() {
        FabricaAlerts.criarAlertGenerico("Erro", "Não foi possível inserir esse produto", "Você será redirecionado(a) ao menu", Alert.AlertType.INFORMATION);
    }

    private void showInsertErrorMessage(Exception error) {
        FabricaAlerts.criarAlertGenerico("Erro", "Não foi possível inserir esse produto", "Erro: " + error.getMessage(), Alert.AlertType.INFORMATION);
    }

    private void showUpdateErrorMessage() {
        FabricaAlerts.criarAlertGenerico("Erro", "Não foi possível atualizar esse produto", "Você será redirecionado(a) ao menu", Alert.AlertType.INFORMATION);
    }

    private void showUpdateErrorMessage(Exception error) {
        FabricaAlerts.criarAlertGenerico("Erro", "Não foi possível atualizar esse produto", "Erro: " + error.getMessage(), Alert.AlertType.INFORMATION);
    }
}
