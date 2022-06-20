package view.controller;

import application.main.Main;
import domain.entities.cliente.Cliente;
import domain.entities.produto.Produto;
import domain.entities.venda.StatusVenda;
import domain.entities.venda.Venda;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import view.enums.Tela;
import view.utils.FabricaAlerts;
import view.utils.UILoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MenuInicialController {
    @FXML
    private MenuItem menuItemCadastrosCliente;
    @FXML
    private MenuItem menuItemCadastrosCampanha;
    @FXML
    private MenuItem menuItemCadastrosEmpresa;
    @FXML
    private MenuItem menuItemCadastrosProduto;

    @FXML
    private MenuItem menuItemProcessosVenda;
    @FXML
    private TableView<Venda> tbvVendas;
    @FXML
    private TableColumn<Venda, String> clnQuantidade;
    @FXML
    private TableColumn<Venda, String> clnFormaPagamento;
    @FXML
    private TableColumn<Venda, String> clnStatus;
    @FXML
    private TableColumn<Venda, String> clnProdutos;
    @FXML
    private TableColumn<Venda, String> clnCliente;
    @FXML
    private TableColumn<Venda, String> clnTotal;
    @FXML
    private RadioButton radioTodas;
    @FXML
    private RadioButton radioFaturadas;
    @FXML
    private RadioButton radioEnviadas;
    @FXML
    private RadioButton radioNaoEnviadas;
    @FXML
    private Label lblTotal;
    @FXML
    private Button buttonFiltrar;
    @FXML
    private Button buttonRelatorio;
    @FXML
    private Button buttonEnviar;
    @FXML
    private Button buttonFaturar;

    private final ToggleGroup group = new ToggleGroup();

    private final List<Venda> vendas = new ArrayList<>();

    @FXML
    void initialize() {
        buttonFaturar.setDisable(true);
        buttonEnviar.setDisable(true);
        radioTodas.setToggleGroup(group);
        radioFaturadas.setToggleGroup(group);
        radioEnviadas.setToggleGroup(group);
        radioNaoEnviadas.setToggleGroup(group);
        configurarColunas();
        setUpMenuListeners();
        List<Venda> vendasFound = Main.listarVendasUseCase.findAll();
        setVendas(vendasFound);
        tbvVendas.getSelectionModel().selectedItemProperty().addListener((observableValue, campanhaTableViewSelectionModel, item) -> {
            if(item == null){
                buttonFaturar.setDisable(true);
                buttonEnviar.setDisable(true);
                return;
            }
            if(item.getStatusVenda() == StatusVenda.NAO_ENVIADO){
                buttonFaturar.setDisable(true);
                buttonEnviar.setDisable(false);
            }else if((item.getStatusVenda() == StatusVenda.ENVIADO)){
                buttonFaturar.setDisable(false);
                buttonEnviar.setDisable(true);
            }else{
                buttonFaturar.setDisable(true);
                buttonEnviar.setDisable(true);
            }
        });

        buttonFiltrar.setOnAction(actionEvent -> filtrar());
        buttonRelatorio.setOnAction(actionEvent -> gerarRelatorio());
        buttonEnviar.setOnAction(actionEvent -> enviar());
        buttonFaturar.setOnAction(actionEvent -> faturar());
    }

    private void setVendas(List<Venda> vendasFound){
        vendas.clear();
        vendas.addAll(vendasFound);
        tbvVendas.getItems().setAll(vendas);
        tbvVendas.refresh();
        setTotalVendas();
    }

    private void filtrar() {
        RadioButton selectedRadioButton = (RadioButton) group.getSelectedToggle();
        String toogleGroupValue = selectedRadioButton.getText();
        switch (toogleGroupValue){
            case "Todas":
                List<Venda> vendasFound = Main.listarVendasUseCase.findAll();
                setVendas(vendasFound);
                break;
            case "Faturadas":
                setFilter(StatusVenda.FATURADO);
                break;
            case "Enviadas":
                setFilter(StatusVenda.ENVIADO);
                break;
            case "Não enviadas":
                setFilter(StatusVenda.NAO_ENVIADO);
                break;

        }
    }

    void setFilter(StatusVenda statusVenda){
        List<Venda> vendas = Main.listarVendasUseCase.findVendaByStatus(statusVenda);
        if(vendas == null) vendas = new ArrayList<>();
        setVendas(vendas);
        tbvVendas.getSelectionModel().selectFirst();
    }

    private void gerarRelatorio() {
        Main.emitirRelatorioVenda.gerarRelatorio(vendas);
        showSuccessMessageGerarRelatorio();
    }

    private void showSuccessMessageGerarRelatorio() {
        FabricaAlerts.criarAlertGenerico("Sucesso", "Relatório gerado", "Confira na raíz do programa", Alert.AlertType.INFORMATION);
    }

    private void enviar() {
        Venda venda = tbvVendas.getSelectionModel().getSelectedItem();
        Main.modificarVendaUseCase.updateStatus(venda);
        showSuccessMessageEnviarVenda();
        filtrar();
    }
    private void showSuccessMessageEnviarVenda() {
        FabricaAlerts.criarAlertGenerico("Sucesso", "Venda enviada", "Sua venda foi enviada com sucesso", Alert.AlertType.INFORMATION);
    }

    private void faturar() {
        Venda venda = tbvVendas.getSelectionModel().getSelectedItem();
        Main.modificarVendaUseCase.updateStatus(venda);
        showSuccessMessageFaturarVenda();
        filtrar();
    }
    private void showSuccessMessageFaturarVenda() {
        FabricaAlerts.criarAlertGenerico("Sucesso", "Venda faturada", "Sua venda foi faturada com sucesso", Alert.AlertType.INFORMATION);
    }

    private void setTotalVendas() {
        double soma = 0;
        for (Venda venda : vendas) {
            soma = soma + venda.getValorTotal();
        }
        lblTotal.setText("R$" + soma);
    }

    private void setUpMenuListeners() {
        setGerenciamentoMenuListeners();
        setVendaMenuListeners();
    }

    private void configurarColunas() {
        clnQuantidade.setCellValueFactory(vendaCell -> {
            Venda item = vendaCell.getValue();
            return new SimpleStringProperty(item.getQntProduto().toString());
        });
        clnFormaPagamento.setCellValueFactory(vendaCell -> {
            Venda item = vendaCell.getValue();
            return new SimpleStringProperty(item.getFormaPagamento().name());
        });
        clnStatus.setCellValueFactory(vendaCell -> {
            Venda item = vendaCell.getValue();
            return new SimpleStringProperty(item.getStatusVenda().name());
        });
        clnProdutos.setCellValueFactory(vendaCell -> {
            Venda item = vendaCell.getValue();
            Optional<Produto> produto = Main.listarProdutosUseCase.findByCodProduto(item.getCodProduto());
            return new SimpleStringProperty(produto.orElseThrow().getNome());
        });
        clnCliente.setCellValueFactory(vendaCell -> {
            Venda item = vendaCell.getValue();
            Optional<Cliente> cliente = Main.listarClientesUseCase.findByCpf(item.getCpfCliente());
            return new SimpleStringProperty(cliente.orElseThrow().getNomeCompleto());
        });
        clnTotal.setCellValueFactory(new PropertyValueFactory<>("valorTotal"));
    }

    private void setGerenciamentoMenuListeners() {
        setActionListener(menuItemCadastrosCliente, Tela.MENU_CLIENTE);
        setActionListener(menuItemCadastrosCampanha, Tela.MENU_CAMPANHA);
        setActionListener(menuItemCadastrosEmpresa, Tela.MENU_EMPRESA);
        setActionListener(menuItemCadastrosProduto, Tela.MENU_PRODUTO);
    }


    private void setVendaMenuListeners() {
        setActionListener(menuItemProcessosVenda, Tela.MENU_REALIZAR_VENDA);
    }

    private void setActionListener(MenuItem menuItem, Tela tela) {
        menuItem.setOnAction(actionEvent -> {
            try {
                UILoader.substituirTela(tela.getNomeTela());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
