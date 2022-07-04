package application.main;

import application.repository.sqlite.DAOSqlite.*;
import application.repository.sqlite.utils.TableCreator;
import domain.usecases.campanha.*;
import domain.usecases.cliente.*;
import domain.usecases.empresa.*;
import domain.usecases.produto.*;
import domain.usecases.relatorio.*;
import domain.usecases.venda.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.enums.Tela;
import view.utils.UILoader;

public class Main extends Application {

    public static AdicionarProdutoUseCase adicionarProdutoUseCase;
    public static ListarProdutosUseCase listarProdutosUseCase;
    public static ModificarProdutoUseCase modificarProdutoUseCase;
    public static RemoverProdutoUseCase removerProdutoUseCase;

    public static AdicionarVendaUseCase adicionarVendaUseCase;
    public static ListarVendasUseCase listarVendasUseCase;
    public static ModificarVendaUseCase modificarVendaUseCase;

    public static AdicionarClienteUseCase adicionarClienteUseCase;
    public static ListarClientesUseCase listarClientesUseCase;
    public static ModificarClienteUseCase modificarClienteUseCase;
    public static RemoverClienteUseCase removerClienteUseCase;

    public static AdicionarCampanhaUseCase adicionarCampanhaUseCase;
    public static ListarCampanhasUseCase listarCampanhasUseCase;
    public static ModificarCampanhaUseCase modificarCampanhaUseCase;
    public static RemoverCampanhaUseCase removerCampanhaUseCase;

    public static AdicinarEmpresaUseCase adicinarEmpresaUseCase;
    public static ListarEmpresasUseCase listarEmpresasUseCase;
    public static ModificarEmpresaUseCase modificarEmpresaUseCase;
    public static RemoverEmpresaUseCase removerEmpresaUseCase;

    public static EmitirRelatorioVenda emitirRelatorioVenda;
    public static EmitirRelatorioCliente emitirRelatorioCliente;
    public static EmitirRelatorioProdutos emitirRelatorioProdutos;
    public static EmitirRelatorioEmpresa emitirRelatorioEmpresa;
    public static EmitirRelatorioCampanhas emitirRelatorioCampanhas;

    public void inject() {
        injecaoDependencia();
        setupDatabase();
    }

    private static void setupDatabase() {
        TableCreator dbBuilder = new TableCreator();
        dbBuilder.buildDatabaseIfMissing();
    }
    private static void injecaoDependencia() {
        CampanhaDAO campanhaDAO = new SqliteCampanhaDAO();
        ProdutoDAO produtoDAO = new SqliteProdutoDAO();
        VendaDAO vendaDAO = new SqliteVendaDAO();
        EmpresaDAO empresaDAO = new SqliteEmpresaDAO();
        ClienteDAO clienteDAO = new SqliteClienteDAO();

        listarProdutosUseCase = new ListarProdutosUseCase(produtoDAO);
        listarVendasUseCase = new ListarVendasUseCase(vendaDAO);
        listarCampanhasUseCase = new ListarCampanhasUseCase(campanhaDAO);
        listarClientesUseCase = new ListarClientesUseCase(clienteDAO);
        listarEmpresasUseCase = new ListarEmpresasUseCase(empresaDAO);

        adicionarVendaUseCase = new AdicionarVendaUseCase(vendaDAO, listarClientesUseCase, listarProdutosUseCase);
        modificarVendaUseCase = new ModificarVendaUseCase(vendaDAO);

        adicionarProdutoUseCase = new AdicionarProdutoUseCase(produtoDAO, listarProdutosUseCase, listarCampanhasUseCase);
        modificarProdutoUseCase = new ModificarProdutoUseCase(produtoDAO, listarProdutosUseCase, listarCampanhasUseCase);
        removerProdutoUseCase = new RemoverProdutoUseCase(produtoDAO, listarProdutosUseCase, listarVendasUseCase);

        adicionarCampanhaUseCase = new AdicionarCampanhaUseCase(campanhaDAO, listarEmpresasUseCase, listarCampanhasUseCase);
        modificarCampanhaUseCase = new ModificarCampanhaUseCase(listarCampanhasUseCase, campanhaDAO);
        removerCampanhaUseCase = new RemoverCampanhaUseCase(listarCampanhasUseCase, campanhaDAO, listarProdutosUseCase);

        adicinarEmpresaUseCase = new AdicinarEmpresaUseCase(empresaDAO, listarEmpresasUseCase);
        modificarEmpresaUseCase = new ModificarEmpresaUseCase(empresaDAO, listarEmpresasUseCase);
        removerEmpresaUseCase = new RemoverEmpresaUseCase(empresaDAO, listarEmpresasUseCase, listarCampanhasUseCase);

        adicionarClienteUseCase = new AdicionarClienteUseCase(clienteDAO, listarClientesUseCase);
        modificarClienteUseCase = new ModificarClienteUseCase(clienteDAO, listarClientesUseCase);
        removerClienteUseCase = new RemoverClienteUseCase(clienteDAO, listarClientesUseCase, listarVendasUseCase);

        emitirRelatorioVenda = new EmitirRelatorioVenda(vendaDAO);
        emitirRelatorioCliente = new EmitirRelatorioCliente(clienteDAO);
        emitirRelatorioProdutos = new EmitirRelatorioProdutos(produtoDAO);
        emitirRelatorioEmpresa = new EmitirRelatorioEmpresa(empresaDAO);
        emitirRelatorioCampanhas = new EmitirRelatorioCampanhas(campanhaDAO);

    }

    @Override
    public void start(Stage stage) throws Exception {
        inject();
        Scene scene = new Scene(UILoader.loadFXML(Tela.INITIAL.getNomeTela()));
        UILoader.setScene(scene);

        stage.setResizable(false);
        stage.setMaxHeight(450);
        stage.setMaxWidth(650);
        stage.setMinHeight(400);
        stage.setMinWidth(600);
        stage.setScene(scene);
        stage.show();
    }
}