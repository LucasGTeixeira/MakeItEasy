package view.controller.gerenciamento.produto;

import application.main.Main;
import domain.entities.produto.Produto;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import view.enums.Tela;
import view.utils.Bundle;
import view.utils.UILoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MenuProdutoController {
    @FXML
    private Button buttonInserir;
    @FXML
    private Button buttonAlterar;
    @FXML
    private Button buttonRemover;
    @FXML
    private Button buttonVoltar;
    @FXML
    private TableView<Produto> tbvProdutos;
    @FXML
    private TableColumn<Produto, String> clnNome;
    @FXML
    private TableColumn<Produto, String> clnCategoria;
    @FXML
    private TableColumn<Produto, String> clnValor;
    @FXML
    private Label labelNome;
    @FXML
    private Label labelCategoria;
    @FXML
    private Label labelValor;
    @FXML
    private Label labelDisponibilidade;
    @FXML
    private Label labelCampanha;
    @FXML
    private Label labelCodigo;
    @FXML
    private Label lblSelect;
    @FXML
    private GridPane gridDetails;

    private final List<Produto> produtos = new ArrayList<>();

    @FXML
    void initialize() {
        showGrid(false);
        produtos.addAll(Main.listarProdutosUseCase.findAll());
        configurarColunas();
        tbvProdutos.getItems().addAll(produtos);
        setButtonsClickListener();
        tbvProdutos.getSelectionModel().selectedItemProperty().addListener((observableValue, campanhaTableViewSelectionModel, item) -> {
            showDetails(item);
            showGrid(true);
        });
    }

    private void showGrid(boolean show){
        lblSelect.setVisible(!show);
        lblSelect.setManaged(!show);
        gridDetails.setVisible(show);
        gridDetails.setManaged(show);
        buttonAlterar.setDisable(!show);
        buttonRemover.setDisable(!show);
    }

    private void showDetails(Produto produto){
        labelNome.setText(produto.getNome());
        labelCategoria.setText(produto.getCategoria().name());
        labelValor.setText(produto.getValor().toString());
        labelDisponibilidade.setText(produto.getDisponibilidade() ? "Sim" : "Não");
        labelCampanha.setText(produto.getCodCampanha());
        labelCodigo.setText(produto.getCodProduto().toString());
    }


    private void configurarColunas() {
        clnCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
        clnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        clnValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
    }

    private void setButtonsClickListener() {
        setActionListener(buttonInserir, Tela.UPDATE_OR_INSERIR_PRODUTO);
        setActionListener(buttonAlterar, Tela.UPDATE_OR_INSERIR_PRODUTO);
        setActionListener(buttonVoltar, Tela.INITIAL);
        setActionListener(buttonRemover, Tela.REMOVER_PRODUTO);
    }

    private void setActionListener(Button button, Tela tela) {
        button.setOnAction(actionEvent -> {
            try {
                Bundle bundle = new Bundle();
                bundle.setBundle("model", tbvProdutos.getSelectionModel().selectedItemProperty().getValue());
                UILoader.substituirTela(tela.getNomeTela(), bundle);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
