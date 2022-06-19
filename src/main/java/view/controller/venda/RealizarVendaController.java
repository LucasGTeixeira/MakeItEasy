package view.controller.venda;

import application.main.Main;
import domain.entities.campanha.Campanha;
import domain.entities.cliente.Cliente;
import domain.entities.empresa.Empresa;
import domain.entities.produto.Produto;
import domain.entities.venda.Venda;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import view.enums.Tela;
import view.utils.Bundle;
import view.utils.FabricaAlerts;
import view.utils.MascaraUtils;
import view.utils.UILoader;

import java.io.IOException;
import java.util.LinkedHashMap;

public class RealizarVendaController {
    @FXML
    private Button buttonCancelar;
    @FXML
    private Button buttonConfirmar;
    @FXML
    private Button btnSetProduto;
    @FXML
    private Button btnRemover;
    @FXML
    private TextField txtQtdProdutos;
    @FXML
    private ComboBox<Campanha> cbbCampanha;
    @FXML
    private ComboBox<Empresa> cbbEmpresa;
    @FXML
    private ComboBox<Cliente> cbbCliente;
    @FXML
    private ComboBox<Produto> cbbProduto;
    @FXML
    private TableView<ProdutoSelected> tbvProdutos;
    @FXML
    private TableColumn<ProdutoSelected, String> clnNome;
    @FXML
    private TableColumn<ProdutoSelected, String> clnQtd;
    @FXML
    private TableColumn<ProdutoSelected, String> clnValor;
    @FXML
    private Label lblTotal;

    private final LinkedHashMap<Integer, ProdutoSelected> selectedProducts = new LinkedHashMap<>();

    @FXML
    void initialize() {
        setButtonsClickListener();
        setUpMasks();
        configurarColunas();
        setFields();
        Bundle bundle = UILoader.getBundle();
        Object model = bundle.getBundle("model");
        if(model == null) return;
        Venda venda = (Venda) model;
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
        btnSetProduto.setDisable(true);
        btnRemover.setDisable(true);
        buttonConfirmar.setOnMouseClicked(mouseEvent -> setUpInsertButton());
        cbbCampanha.getItems().addAll(Main.listarCampanhasUseCase.findAll());
        cbbCliente.getItems().addAll(Main.listarClientesUseCase.findAll());
        cbbEmpresa.getItems().addAll(Main.listarEmpresasUseCase.findAll());
        cbbProduto.getItems().addAll(Main.listarProdutosUseCase.findAll());

        txtQtdProdutos.textProperty().addListener((observableValue, s, t1) -> validateEnableSetProductButton());

        cbbProduto.getSelectionModel().selectedItemProperty().addListener((observableValue, produtoSingleSelectionModel, t1) -> validateEnableSetProductButton());

        btnSetProduto.setOnMouseClicked(mouseEvent -> {
            String text = txtQtdProdutos.getText();
            Produto produto = cbbProduto.getSelectionModel().getSelectedItem();
            ProdutoSelected produtoSelected = new ProdutoSelected(produto.getCodProduto(), produto.getNome(), produto.getValor(), Integer.parseInt(text));
            selectedProducts.put(produto.getCodProduto(), produtoSelected);
            tbvProdutos.getItems().setAll(selectedProducts.values());
            setValorTotal();
        });

        btnRemover.setOnMouseClicked(mouseEvent -> {
            ProdutoSelected produtoSelected = tbvProdutos.getSelectionModel().getSelectedItem();
            if (produtoSelected == null) return;
            selectedProducts.remove(produtoSelected.getCodigo());
            tbvProdutos.getItems().setAll(selectedProducts.values());
            setValorTotal();
        });

        tbvProdutos.getSelectionModel().selectedItemProperty().addListener(observable -> {
            boolean isSomethingSelected = tbvProdutos.getSelectionModel().getSelectedItem() == null;
            btnRemover.setDisable(isSomethingSelected);
            if (!isSomethingSelected) return;
            ProdutoSelected produtoSelected = tbvProdutos.getSelectionModel().getSelectedItem();
            if (produtoSelected == null) return;
            Produto produto = null;
            for (Produto item : cbbProduto.getItems()) {
                if (!item.getCodProduto().equals(produtoSelected.getCodigo())) continue;
                produto = item;
            }
            cbbProduto.setValue(produto);
            txtQtdProdutos.setText(String.valueOf(produtoSelected.getQuantidade()));
        });
    }

    private void configurarColunas() {
        clnValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        clnQtd.setCellValueFactory(new PropertyValueFactory<>("quantidade"));
        clnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
    }

    void validateEnableSetProductButton() {
        String text = txtQtdProdutos.getText();
        if (text.isEmpty()) {
            btnSetProduto.setDisable(true);
            return;
        }
        btnSetProduto.setDisable(cbbProduto.getSelectionModel().getSelectedItem() == null);
    }

    private void setUpInsertButton() {
        if (isAnyFieldEmpty()) {
            preenchaTodosOsCampos();
            return;
        }
        try {
            //todo AdaptarUC
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

    private void setValorTotal(){
        double total = 0;
        for (ProdutoSelected selected : selectedProducts.values()) {
            total = total + (selected.getQuantidade()*selected.getValor());
        }
        lblTotal.setText(String.valueOf(total));
    }

    boolean isAnyFieldEmpty() {
        return cbbEmpresa.getSelectionModel().getSelectedItem() == null ||
                cbbCliente.getSelectionModel().getSelectedItem() == null ||
                cbbCampanha.getSelectionModel().getSelectedItem() == null ||
                selectedProducts.isEmpty();
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
