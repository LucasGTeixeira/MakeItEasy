package view.controller.gerenciamento.campanha;

import application.main.Main;
import domain.entities.campanha.Campanha;
import domain.entities.empresa.Empresa;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import view.enums.Tela;
import view.utils.*;

import java.io.IOException;
import java.time.LocalDate;

public class UpdateOrInsertCampanhaController {
    @FXML
    private Button buttonCancelar;
    @FXML
    private Button buttonConfirmar;
    @FXML
    private DatePicker dtLancamento;
    @FXML
    private DatePicker dtExpiracao;
    @FXML
    private Label lblTitulo;
    @FXML
    private TextField txtCod;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtEdicao;
    @FXML
    private ComboBox<Empresa> cbbEmpresa;

    private int id;

    @FXML
    void initialize() {
        Bundle bundle = UILoader.getBundle();
        setUpScreen(bundle);
        setButtonsClickListener();
        setUpMasks();
    }

    private void setActionListener(Button button) {
        button.setOnAction(actionEvent -> {
            try {
                UILoader.getBundle().free();
                UILoader.substituirTela(Tela.MENU_CAMPANHA.getNomeTela());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void setButtonsClickListener() {
        setActionListener(buttonCancelar);
    }

    private void setUpMasks() {
        MascaraUtils.mascaraParaData(dtLancamento);
        MascaraUtils.mascaraParaData(dtExpiracao);
    }

    private void setUpScreen(Bundle bundle) {
        ScreenMode screenMode;
        Object model = bundle.getBundle("model");
        if (model != null) {
            Campanha campanha = (Campanha) model;
            screenMode = ScreenMode.UPDATE;
            id = campanha.getId();
            setFields(campanha);
            buttonConfirmar.setOnMouseClicked(mouseEvent -> setUpUpdateButton());
        } else {
            screenMode = ScreenMode.INSERT;
            buttonConfirmar.setOnMouseClicked(mouseEvent -> setUpInsertButton());
            cbbEmpresa.getItems().addAll(Main.listarEmpresasUseCase.findAll());
        }
        lblTitulo.setText(screenMode.getTitulo());
    }

    private void setFields(Campanha campanha) {
        txtCod.setDisable(true);
        txtCod.setText(campanha.getCodigo());
        txtEdicao.setText(campanha.getEdicao());
        txtNome.setText(campanha.getNome());
        dtLancamento.setValue(campanha.getDataLancamento());
        dtExpiracao.setValue(campanha.getDataExpiracao());
        cbbEmpresa.getItems().addAll(Main.listarEmpresasUseCase.findAll());
        cbbEmpresa.getSelectionModel().select(Main.listarEmpresasUseCase.findByCnpj(campanha.getCnpjEmpresa()).orElseThrow());
    }

    private void setUpUpdateButton() {
        if (isAnyFieldEmpty()) {
            preenchaTodosOsCampos();
            return;
        }

        String codigo = txtCod.getText();
        String nome = txtNome.getText();
        String edicao = txtEdicao.getText();
        LocalDate dataLancamento = dtLancamento.getValue();
        LocalDate dataExpiracao = dtExpiracao.getValue();
        String cnpjEmpresa = cbbEmpresa.getValue().getCnpj();
        try {
            //todo AdaptarUC
            boolean success = Main.modificarCampanhaUseCase.update(new Campanha(id, codigo, nome, edicao, dataLancamento, dataExpiracao, cnpjEmpresa));
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
        String codigo = txtCod.getText();
        String nome = txtNome.getText();
        String edicao = txtEdicao.getText();
        LocalDate dataLancamento = dtLancamento.getValue();
        LocalDate dataExpiracao = dtExpiracao.getValue();
        String cnpjEmpresa = cbbEmpresa.getValue().getCnpj();

        try {
            //todo AdaptarUC
            Main.adicionarCampanhaUseCase.insert(new Campanha(codigo, nome, edicao, dataLancamento, dataExpiracao, cnpjEmpresa));
            confirm();
        } catch (Exception e) {
            showInsertErrorMessage(e);
        }
    }

    boolean isAnyFieldEmpty() {
        return txtNome.getText().isEmpty() ||
                txtEdicao.getText().isEmpty() ||
                txtCod.getText().isEmpty() ||
                dtLancamento.getEditor().getText().isEmpty() ||
                dtExpiracao.getEditor().getText().isEmpty() ||
                cbbEmpresa.getSelectionModel().getSelectedItem() == null;
    }

    private void confirm() throws IOException {
        successMessage();
        UILoader.getBundle().free();
        UILoader.substituirTela(Tela.MENU_CAMPANHA.getNomeTela());
    }

    private void successMessage() {
        FabricaAlerts.criarAlertGenerico("Sucesso", "Campanha salva com sucesso", "Você será redirecionado(a) ao menu", Alert.AlertType.INFORMATION);
    }

    private void preenchaTodosOsCampos() {
        FabricaAlerts.criarAlertGenerico("Atenção", "Preencha todos os campos", "Todos os campos são necessários", Alert.AlertType.INFORMATION);
    }

    private void showInsertErrorMessage(Exception error) {
        FabricaAlerts.criarAlertGenerico("Erro", "Não foi possível inserir essa campanha", "Erro: " + error.getMessage(), Alert.AlertType.INFORMATION);
    }

    private void showUpdateErrorMessage() {
        FabricaAlerts.criarAlertGenerico("Erro", "Não foi possível atualizar essa campanha", "Você será redirecionado(a) ao menu", Alert.AlertType.INFORMATION);
    }

    private void showUpdateErrorMessage(Exception error) {
        FabricaAlerts.criarAlertGenerico("Erro", "Não foi possível atualizar essa campanha", "Erro: " + error.getMessage(), Alert.AlertType.INFORMATION);
    }

}
