package view.controller.gerenciamento.cliente;

import application.main.Main;
import domain.entities.cliente.Cliente;
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

    private final List<Cliente> clientes = new ArrayList<>();
    @FXML
    void initialize() {
        clientes.addAll(Main.listarClientesUseCase.findAll());
        configurarColunas();
        tbvClientes.getItems().addAll(clientes);
        setButtonsClickListener();
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
