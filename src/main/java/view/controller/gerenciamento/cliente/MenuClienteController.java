package view.controller.gerenciamento.cliente;

import application.main.Main;
import domain.entities.cliente.Cliente;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import view.enums.Tela;
import view.utils.UILoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MenuClienteController {
    @FXML
    private Button buttonInserir;
    @FXML
    private Button buttonAlterar;
    @FXML
    private Button buttonRemover;
    @FXML
    private Button buttonVoltar;

    @FXML
    private TableView<Cliente> tbvClientes;
    @FXML
    private TableColumn<Cliente, String> clnId;
    @FXML
    private TableColumn<Cliente, String> clnNome;
    @FXML
    private TableColumn<Cliente, String> clnCpf;

    @FXML
    private Label labelClienteId;
    @FXML
    private Label labelClienteCPF;
    @FXML
    private Label labelClienteNome;
    @FXML
    private Label labelClienteTelefone;
    @FXML
    private Label labelClienteEmail;
    @FXML
    private Label labelClienteEndereco;
    @FXML
    private Label labelClienteStatus;
    @FXML
    private Label labelClienteDataNascimento;

    @FXML
    private Label lblSelect;
    @FXML
    private GridPane gridDetails;

    private final List<Cliente> clientes = new ArrayList<>();
    @FXML
    void initialize() {
        showGrid(false);
        clientes.addAll(Main.listarClientesUseCase.findAll());
        configurarColunas();
        tbvClientes.getItems().addAll(clientes);
        setButtonsClickListener();
        tbvClientes.getSelectionModel().selectedItemProperty().addListener((observableValue, campanhaTableViewSelectionModel, item) -> {
            showDetails(item);
            showGrid(true);
        });
    }

    private void showGrid(boolean show){
        lblSelect.setVisible(!show);
        lblSelect.setManaged(!show);
        gridDetails.setVisible(show);
        gridDetails.setManaged(show);
    }

    private void showDetails(Cliente cliente){
        labelClienteId.setText(cliente.getId().toString());
        labelClienteCPF.setText(cliente.getCpf());
        labelClienteNome.setText(cliente.getNomeCompleto());
        labelClienteTelefone.setText(cliente.getTelefone());
        labelClienteEmail.setText(cliente.getEmail());
        labelClienteEndereco.setText(cliente.getEndereco());
        labelClienteStatus.setText(cliente.getStatus().toString());
        labelClienteDataNascimento.setText(cliente.getDataNascimento().toString());
    }


    private void configurarColunas() {
        clnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        clnNome.setCellValueFactory(new PropertyValueFactory<>("nomeCompleto"));
        clnCpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
    }

    private void setButtonsClickListener() {
        setActionListener(buttonInserir, Tela.UPDATE_OR_INSERIR_CLIENTE);
        setActionListener(buttonAlterar, Tela.UPDATE_OR_INSERIR_CLIENTE);
        setActionListener(buttonVoltar, Tela.INITIAL);
        setActionListener(buttonRemover, Tela.REMOVER_CLIENTE);
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
