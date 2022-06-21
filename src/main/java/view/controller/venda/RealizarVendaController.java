package view.controller.venda;

import application.main.Main;
import domain.entities.cliente.Cliente;
import domain.entities.produto.Produto;
import domain.entities.venda.FormaPagamento;
import domain.entities.venda.StatusVenda;
import domain.entities.venda.Venda;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import view.enums.Tela;
import view.utils.Bundle;
import view.utils.FabricaAlerts;
import view.utils.MascaraUtils;
import view.utils.UILoader;

import java.io.IOException;

public class RealizarVendaController {
    @FXML
    private Button buttonCancelar;
    @FXML
    private Button buttonConfirmar;
    @FXML
    private TextField txtQtdProdutos;
    @FXML
    private ComboBox<FormaPagamento> cbbPagamento;
    @FXML
    private ComboBox<StatusVenda> cbbStatus;
    @FXML
    private ComboBox<Cliente> cbbCliente;
    @FXML
    private ComboBox<Produto> cbbProduto;
    @FXML
    private Label lblTotal;

    @FXML
    void initialize() {
        setButtonsClickListener();
        setUpMasks();
        setFields();
    }

    private void setButtonsClickListener() {
        setActionListener(buttonCancelar);
    }


    private void confirm() throws IOException {
        successMessage();
        UILoader.getBundle().free();
        UILoader.substituirTela(Tela.INITIAL.getNomeTela());
    }

    private void setActionListener(Button button) {
        button.setOnAction(actionEvent -> {
            try {
                UILoader.getBundle().free();
                UILoader.substituirTela(Tela.INITIAL.getNomeTela());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void setFields() {
        buttonConfirmar.setOnMouseClicked(mouseEvent -> setUpInsertButton());
        cbbCliente.getItems().addAll(Main.listarClientesUseCase.findAll());
        cbbProduto.getItems().addAll(Main.listarProdutosUseCase.findAll());
        cbbStatus.getItems().addAll(StatusVenda.values());
        cbbPagamento.getItems().addAll(FormaPagamento.values());

        txtQtdProdutos.textProperty().addListener((observableValue, s, t1) -> validateEnableSetProductButton());

        cbbProduto.getSelectionModel().selectedItemProperty().addListener((observableValue, produtoSingleSelectionModel, t1) -> validateEnableSetProductButton());
    }

    void validateEnableSetProductButton() {
        Produto produto = cbbProduto.getSelectionModel().getSelectedItem();
        String text = txtQtdProdutos.getText();
        if (text.isEmpty()) {
            lblTotal.setText("R$0.00");
        } else {
            lblTotal.setText("R$" + produto.getValor() * Integer.parseInt(text));
        }
    }

    private void setUpInsertButton() {
        if (isAnyFieldEmpty()) {
            preenchaTodosOsCampos();
            return;
        }
        try {
            Cliente cliente = cbbCliente.getSelectionModel().getSelectedItem();
            Produto produto = cbbProduto.getSelectionModel().getSelectedItem();
            StatusVenda statusVenda = cbbStatus.getSelectionModel().getSelectedItem();
            FormaPagamento pagamento = cbbPagamento.getSelectionModel().getSelectedItem();
            String qtd = txtQtdProdutos.getText();
            Main.adicionarVendaUseCase.insert(new Venda(cliente.getCpf(), produto.getCodProduto(), pagamento, statusVenda, Integer.parseInt(qtd)));
            confirm();
        } catch (Exception e) {
            showInsertErrorMessage(e);
        }
    }

    boolean isAnyFieldEmpty() {
        return cbbCliente.getSelectionModel().getSelectedItem() == null ||
                cbbProduto.getSelectionModel().getSelectedItem() == null ||
                cbbStatus.getSelectionModel().getSelectedItem() == null ||
                cbbPagamento.getSelectionModel().getSelectedItem() == null ||
                txtQtdProdutos.getText().isEmpty();
    }

    private void setUpMasks() {
        MascaraUtils.mascaraParaNumerosSemZero(txtQtdProdutos);
    }


    private void successMessage() {
        FabricaAlerts.criarAlertGenerico("Sucesso", "Venda salva", "Você será redirecionado(a)  ao menu", Alert.AlertType.INFORMATION);
    }

    private void preenchaTodosOsCampos() {
        FabricaAlerts.criarAlertGenerico("Atenção", "Preencha todos os campos", "Todos os campos são necessários", Alert.AlertType.INFORMATION);
    }

    private void showInsertErrorMessage() {
        FabricaAlerts.criarAlertGenerico("Erro", "Não foi possível salvar essa venda", "Você será redirecionado(a) ao menu", Alert.AlertType.INFORMATION);
    }

    private void showInsertErrorMessage(Exception error) {
        FabricaAlerts.criarAlertGenerico("Erro", "Não foi possível salvar essa venda", "Erro: " + error.getMessage(), Alert.AlertType.INFORMATION);
    }
}
