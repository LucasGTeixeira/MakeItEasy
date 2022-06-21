package view.controller.gerenciamento.cliente;

import application.main.Main;
import domain.entities.cliente.Cliente;
import domain.entities.cliente.ClienteStatus;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import view.enums.Tela;
import view.utils.*;

import java.io.IOException;
import java.time.LocalDate;

public class UpdateOrInsertClienteController {
    @FXML
    private Button buttonCancelar;
    @FXML
    private Button buttonConfirmar;
    @FXML
    private DatePicker dtNascimento;
    @FXML
    private Label lblTitulo;
    @FXML
    private TextField txtCpf;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtTelefone;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtEndereco;
    @FXML
    private ComboBox<ClienteStatus> cbbStatus;

    private int id;

    @FXML
    void initialize() {
        setButtonsClickListener();
        setUpMasks();
    }

    private void setActionListener(Button button) {
        button.setOnAction(actionEvent -> {
            try {
                UILoader.getBundle().free();
                UILoader.substituirTela(Tela.MENU_CLIENTE.getNomeTela());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void setUpMasks() {
        MascaraUtils.mascaraParaData(dtNascimento);
    }

    private void setButtonsClickListener() {
        Bundle bundle = UILoader.getBundle();
        setActionListener(buttonCancelar);
        cbbStatus.getItems().addAll(ClienteStatus.values());
        cbbStatus.getSelectionModel().selectFirst();
        setUpScreen(bundle);
    }

    private void setUpScreen(Bundle bundle) {
        ScreenMode screenMode;
        Object model = bundle.getBundle("model");
        if (model != null) {
            Cliente empresa = (Cliente) model;
            screenMode = ScreenMode.UPDATE;
            setFields(empresa);
            buttonConfirmar.setOnMouseClicked(mouseEvent -> setUpUpdateButton());
        } else {
            screenMode = ScreenMode.INSERT;
            buttonConfirmar.setOnMouseClicked(mouseEvent -> setUpInsertButton());
        }
        lblTitulo.setText(screenMode.getTitulo());
    }

    private void setFields(Cliente cliente) {
        txtCpf.setDisable(true);
        id = cliente.getId();
        txtCpf.setText(cliente.getCpf());
        txtNome.setText(cliente.getNomeCompleto());
        txtTelefone.setText(cliente.getTelefone());
        txtEmail.setText(cliente.getEmail());
        txtEndereco.setText(cliente.getEndereco());
        cbbStatus.getSelectionModel().select(cliente.getStatus());
        dtNascimento.setValue(cliente.getDataNascimento());
    }

    private void setUpUpdateButton() {
        if (isAnyFieldEmpty()) {
            preenchaTodosOsCampos();
            return;
        }
        try {
            String cpf = txtCpf.getText();
            String nomeCompleto = txtNome.getText();
            String telefone = txtTelefone.getText();
            String email = txtEmail.getText();
            String endereco = txtEndereco.getText();
            ClienteStatus status = cbbStatus.getValue();
            LocalDate dataNascimento = dtNascimento.getValue();
            //todo AdaptarUC
            boolean success = Main.modificarClienteUseCase.update(new Cliente(id, cpf, nomeCompleto, telefone, email, endereco, status, dataNascimento));
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
            String cpf = txtCpf.getText();
            String nomeCompleto = txtNome.getText();
            String telefone = txtTelefone.getText();
            String email = txtEmail.getText();
            String endereco = txtEndereco.getText();
            ClienteStatus status = cbbStatus.getValue();
            LocalDate dataNascimento = dtNascimento.getValue();
            //todo AdaptarUC
            Main.adicionarClienteUseCase.insert(new Cliente(cpf, nomeCompleto, telefone, email, endereco, status, dataNascimento));
            confirm();
        } catch (Exception e) {
            showInsertErrorMessage(e);
        }
    }

    boolean isAnyFieldEmpty() {
        return txtNome.getText().isEmpty() ||
                txtCpf.getText().isEmpty() ||
                txtEmail.getText().isEmpty() ||
                txtEndereco.getText().isEmpty() ||
                cbbStatus.getSelectionModel().getSelectedItem() == null ||
                txtTelefone.getText().isEmpty() ||
                dtNascimento.getEditor().getText().isEmpty();
    }

    private void confirm() throws IOException {
        successMessage();
        UILoader.getBundle().free();
        UILoader.substituirTela(Tela.MENU_CLIENTE.getNomeTela());
    }

    private void successMessage() {
        FabricaAlerts.criarAlertGenerico("Sucesso", "Cliente salvo com sucesso", "Você será redirecionado(a) ao menu", Alert.AlertType.INFORMATION);
    }

    private void preenchaTodosOsCampos() {
        FabricaAlerts.criarAlertGenerico("Atenção", "Preencha todos os campos", "Todos os campos são necessários", Alert.AlertType.INFORMATION);
    }

    private void showInsertErrorMessage(Exception error) {
        FabricaAlerts.criarAlertGenerico("Erro", "Não foi possível inserir esse cliente", "Erro: " + error.getMessage(), Alert.AlertType.INFORMATION);
    }

    private void showUpdateErrorMessage() {
        FabricaAlerts.criarAlertGenerico("Erro", "Não foi possível atualizar esse cliente", "Você será redirecionado(a) ao menu", Alert.AlertType.INFORMATION);
    }

    private void showUpdateErrorMessage(Exception error) {
        FabricaAlerts.criarAlertGenerico("Erro", "Não foi possível atualizar esse cliente", "Erro: " + error.getMessage(), Alert.AlertType.INFORMATION);
    }

}
