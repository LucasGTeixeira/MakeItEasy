package view.controller.gerenciamento.campanha;

import application.main.Main;
import domain.entities.campanha.Campanha;
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

public class MenuCampanhaController {
    @FXML
    private Button buttonInserir;
    @FXML
    private Button buttonAlterar;
    @FXML
    private Button buttonRemover;
    @FXML
    private Button buttonVoltar;
    @FXML
    private TableView<Campanha> tbvCampanha;
    @FXML
    private TableColumn<Campanha, String> clnCodigo;
    @FXML
    private TableColumn<Campanha, String> clnEdicao;
    @FXML
    private TableColumn<Campanha, String> clnNome;

    private final List<Campanha> campanhaList = new ArrayList<>();

    @FXML
    void initialize() {
        configurarColunas();
        campanhaList.addAll(Main.listarCampanhasUseCase.findAll());
        setButtonsClickListener();
        tbvCampanha.getItems().addAll(campanhaList);
        tbvCampanha.refresh();
    }

    private void configurarColunas() {
        clnCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        clnEdicao.setCellValueFactory(new PropertyValueFactory<>("edicao"));
        clnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
    }

    private void setButtonsClickListener() {
        setActionListener(buttonInserir, Tela.UPDATE_OR_INSERIR_CAMPANHA);
        setActionListener(buttonAlterar, Tela.UPDATE_OR_INSERIR_CAMPANHA);
        setActionListener(buttonVoltar, Tela.INITIAL);
        setActionListener(buttonRemover, Tela.REMOVER_CAMPANHA);
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
