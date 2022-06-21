package application.main;

import application.repository.sqlite.DAO.*;
import application.repository.sqlite.utils.TableCreator;
import domain.entities.campanha.Campanha;
import domain.entities.cliente.Cliente;
import domain.entities.cliente.ClienteStatus;
import domain.entities.empresa.Empresa;
import domain.entities.produto.CategoriaProdutos;
import domain.entities.produto.Produto;
import domain.entities.venda.FormaPagamento;
import domain.entities.venda.StatusVenda;
import domain.entities.venda.Venda;
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

import java.time.LocalDate;

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

        //DECALRANDO OBJETOS (SEM ID, POIS SERÁ AUTOINCREMENTADO)
        Empresa empresa1 = new Empresa("15486", "Texas Ltda");
        Empresa empresa2 = new Empresa("15897", "Meyer Ltda");
        Empresa empresa3 = new Empresa("65489", "Antonio Zoia Ltda");
        Empresa empresa4 = new Empresa("11111", "Celestino Ltda");
        Empresa empresa5 = new Empresa(3,"65489", "Empresa Modificada");

        Campanha campanha1 = new Campanha("451611", "beleza natural", "verão 2014",
                LocalDate.of(2014, 4, 26),
                LocalDate.of(2014, 12, 14),
                "15486");
        Campanha campanha2 = new Campanha("666666", "beleza intima", "primavera 2022",
                LocalDate.of(2022, 2, 21),
                LocalDate.of(2022, 3, 10),
                "15486");
        Campanha campanha3= new Campanha(1,"451611", "nome Modificado", "2022",
                LocalDate.of(2022, 2, 21),
                LocalDate.of(2022, 3, 10),
                "15486");
        Campanha campanha4 = new Campanha("123456", "cozinha já", "inverno 2022",
                LocalDate.of(2022, 2, 21),
                LocalDate.of(2022, 3, 10),
                "11111");

        Cliente cliente1 = new Cliente("48415548755", "Carlos Antonio da Silva",
                "997884512", "carlos@email.com", "Av. Fioravante Bertaquini",
                ClienteStatus.ATIVO, LocalDate.of(2000, 2, 2));

        Cliente cliente2 = new Cliente("15645789544", "Pedro Antonio da Silva",
                "998774411", "pedro@email.com", "Av. Fioravante Bertaquini",
                ClienteStatus.ATIVO, LocalDate.of(2000, 4, 3));

        Cliente cliente3 = new Cliente(2,"15645789533", "Nome Modificado",
                "998774411", "pedroModificado@email.com", "Av. Fioravante Bertaquini",
                ClienteStatus.ATIVO, LocalDate.of(2000, 6, 6));

        Cliente cliente4 = new Cliente("14625101827", "Vendas Beleza",
                "997554512", "beleza@email.com", "Av. Bertaquini",
                ClienteStatus.ATIVO, LocalDate.of(2000, 3, 4));

        Cliente cliente5 = new Cliente("22648889544", "Paulo Sergio",
                "992274411", "paulo@email.com", "Av. Sebastiao",
                ClienteStatus.ATIVO, LocalDate.of(2000, 8, 10));

        Produto produto1 = new Produto(111, "Pente", CategoriaProdutos.COSMETICOS,
                10.0, true, "666666");

        Produto produto2 = new Produto(222, "Base", CategoriaProdutos.COSMETICOS,
                22.0, true, "666666");

        Produto produto3 = new Produto(333, "Panela de pressão", CategoriaProdutos.COZINHA,
                200.0, true, "123456");

        Produto produto4 = new Produto(444, "Liquidificador master", CategoriaProdutos.COZINHA,
                85.0, true, "666666");

        Produto produto5 = new Produto(555, "Esteira", CategoriaProdutos.SAUDE_BEM_ESTAR,
                920.0, true, "451611");

        Produto produto6 = new Produto(4,444, "Modificado Liq master", CategoriaProdutos.COZINHA,
                85.0, true, "666666");

        //VENDAS
        Venda venda1 = new Venda("15645789544", 111,  FormaPagamento.CREDITO, StatusVenda.NAO_ENVIADO,1);
        Venda venda2 = new Venda("15645789544", 111,  FormaPagamento.BOLETO_BANCARIO, StatusVenda.NAO_ENVIADO,2);
        Venda venda3 = new Venda("22648889544", 333,  FormaPagamento.PIX, StatusVenda.ENVIADO, 3);
        Venda venda4 = new Venda("48415548755", 555,  FormaPagamento.CREDITO, StatusVenda.ENVIADO,4);
        Venda venda5 = new Venda(4,"33315548766", 555, FormaPagamento.CREDITO, StatusVenda.NAO_ENVIADO,5);

        //EMPRESAS
        Integer emp1 = adicinarEmpresaUseCase.insert(empresa1);
        Integer emp2 = adicinarEmpresaUseCase.insert(empresa2);
        Integer emp3 = adicinarEmpresaUseCase.insert(empresa3);
        Integer emp4 = adicinarEmpresaUseCase.insert(empresa4);

        //CLIENTES
        Integer cli1 = adicionarClienteUseCase.insert(cliente1);
        Integer cli2 = adicionarClienteUseCase.insert(cliente2);
        Integer cli3 = adicionarClienteUseCase.insert(cliente3);
        Integer cli4 = adicionarClienteUseCase.insert(cliente4);
        Integer cli5 = adicionarClienteUseCase.insert(cliente5);

        //CAMPANHAS
        Integer camp1 = adicionarCampanhaUseCase.insert(campanha1);
        Integer camp2 = adicionarCampanhaUseCase.insert(campanha2);
        Integer camp4 = adicionarCampanhaUseCase.insert(campanha4);

        //PRODUTOS
        Integer prod1 = adicionarProdutoUseCase.insert(produto1);
        Integer prod2 = adicionarProdutoUseCase.insert(produto2);
        Integer prod3 = adicionarProdutoUseCase.insert(produto3);
        Integer prod4 = adicionarProdutoUseCase.insert(produto4);
        Integer prod5 = adicionarProdutoUseCase.insert(produto5);

        //VENDAS
        Integer v1 = adicionarVendaUseCase.insert(venda1);
        Integer v2 = adicionarVendaUseCase.insert(venda2);
        Integer v3 = adicionarVendaUseCase.insert(venda3);
        Integer v4 = adicionarVendaUseCase.insert(venda4);
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