package view.controller;

import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import view.enums.Tela;
import view.utils.UILoader;

import java.io.IOException;

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
    private MenuItem menuItemRelatorioVenda;
    @FXML
    private MenuItem menuItemRelatorioCliente;
    @FXML
    private MenuItem menuItemRelatorioProduto;
    @FXML
    private MenuItem menuItemRelatorioCampanha;
    @FXML
    private MenuItem menuItemRelatorioEmpresa;

    @FXML
    void initialize(){
        setUpMenuListeners();
    }

    private void setUpMenuListeners(){
        setGerenciamentoMenuListeners();
        setVendaMenuListeners();
        setRelatorioMenuListeners();
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
