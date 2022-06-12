package view.controller.gerenciamento.produto;

import application.main.Main;
import domain.entities.produto.Produto;
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

    private final List<Produto> produtos = new ArrayList<>();

    @FXML
    void initialize() {
        produtos.addAll(Main.listarProdutosUseCase.findAll());
        configurarColunas();
        tbvProdutos.getItems().addAll(produtos);
        setButtonsClickListener();
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
                UILoader.substituirTela(tela.getNomeTela());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
