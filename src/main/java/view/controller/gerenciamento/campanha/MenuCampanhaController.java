package view.controller.gerenciamento.campanha;

import application.main.Main;
import domain.entities.campanha.Campanha;
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

    @FXML
    private Label labelCampanhaId;
    @FXML
    private Label labelCampanhaNome;
    @FXML
    private Label labelCampanhaCod;
    @FXML
    private Label labelCampanhaEdicao;
    @FXML
    private Label labelCampanhaDataLancamento;
    @FXML
    private Label labelCampanhaExpiracao;
    @FXML
    private Label labelCampanhaCNPJ;
    @FXML
    private Label lblSelect;
    @FXML
    private GridPane gridDetails;


    private final List<Campanha> campanhaList = new ArrayList<>();

    @FXML
    void initialize() {
        showGrid(false);
        configurarColunas();
        campanhaList.addAll(Main.listarCampanhasUseCase.findAll());
        setButtonsClickListener();
        tbvCampanha.getItems().addAll(campanhaList);
        tbvCampanha.refresh();
        tbvCampanha.getSelectionModel().selectedItemProperty().addListener((observableValue, campanhaTableViewSelectionModel, item) -> {
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

    private void showDetails(Campanha campanha){
        labelCampanhaId.setText(campanha.getId().toString());
        labelCampanhaNome.setText(campanha.getNome());
        labelCampanhaCod.setText(campanha.getCodigo());
        labelCampanhaEdicao.setText(campanha.getEdicao());
        labelCampanhaDataLancamento.setText(campanha.getDataLancamento().toString());
        labelCampanhaExpiracao.setText(campanha.getDataExpiracao().toString());
        labelCampanhaCNPJ.setText(campanha.getCnpjEmpresa());
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
