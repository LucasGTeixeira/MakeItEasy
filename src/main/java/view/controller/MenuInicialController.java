package view.controller;

import application.main.Main;
import domain.entities.cliente.Cliente;
import domain.entities.produto.Produto;
import domain.entities.venda.Venda;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import view.enums.Tela;
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
    private MenuItem menuItemRelatorioCliente;
    @FXML
    private MenuItem menuItemRelatorioProduto;
    @FXML
    private MenuItem menuItemRelatorioCampanha;
    @FXML
    private MenuItem menuItemRelatorioEmpresa;

    @FXML
    private TableView<Venda> tbvVendas;
    @FXML
    private TableColumn<Venda,String> clnEmpresa;
    @FXML
    private TableColumn<Venda,String> clnCampanha;
    @FXML
    private TableColumn<Venda,String> clnProdutos;
    @FXML
    private TableColumn<Venda,String> clnCliente;
    @FXML
    private TableColumn<Venda,String> clnTotal;

    private final List<Venda> vendas = new ArrayList<>();

    @FXML
    void initialize(){
        configurarColunas();
        setUpMenuListeners();
        List<Venda> vendasFound = Main.listarVendasUseCase.findAll();
        vendas.addAll(vendasFound);
        tbvVendas.getItems().addAll(vendas);
    }

    private void setUpMenuListeners(){
        setGerenciamentoMenuListeners();
        setVendaMenuListeners();
        setRelatorioMenuListeners();
    }

    private void configurarColunas() {
        clnEmpresa.setCellValueFactory(vendaCell -> {
            Venda item = vendaCell.getValue();
            //todo recuperar pelo id
            return new SimpleStringProperty("Empresa");
        });
        clnCampanha.setCellValueFactory(vendaCell -> {
            Venda item = vendaCell.getValue();
            //todo recuperar pelo id
            return new SimpleStringProperty("Campanha");
        });
        clnProdutos.setCellValueFactory(vendaCell -> {
            Venda item = vendaCell.getValue();
            //todo recuperar + produtos
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

    private void setGerenciamentoMenuListeners(){
        setActionListener(menuItemCadastrosCliente, Tela.MENU_CLIENTE);
        setActionListener(menuItemCadastrosCampanha,Tela.MENU_CAMPANHA);
        setActionListener(menuItemCadastrosEmpresa,Tela.MENU_EMPRESA);
        setActionListener(menuItemCadastrosProduto,Tela.MENU_PRODUTO);
    }


    private void setVendaMenuListeners(){
        setActionListener(menuItemProcessosVenda,Tela.MENU_REALIZAR_VENDA);
    }

    private void setRelatorioMenuListeners(){
        setActionListener(menuItemRelatorioCliente,Tela.MENU_RELATORIO_CLIENTE);
        setActionListener(menuItemRelatorioProduto,Tela.MENU_RELATORIO_PRODUTO);
        setActionListener(menuItemRelatorioCampanha,Tela.MENU_RELATORIO_CAMPANHA);
        setActionListener(menuItemRelatorioEmpresa,Tela.MENU_RELATORIO_EMPRESA);
    }

    private void setActionListener(MenuItem menuItem, Tela tela){
        menuItem.setOnAction(actionEvent -> {
            try {
                UILoader.substituirTela(tela.getNomeTela());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
