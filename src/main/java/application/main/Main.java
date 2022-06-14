package application.main;

import application.repository.*;
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

import java.math.BigDecimal;
import java.time.LocalDate;
public class Main extends Application {

    private static AdicionarProdutoUseCase adicionarProdutoUseCase;
    public static ListarProdutosUseCase listarProdutosUseCase;
    private static ModificarProdutoUseCase modificarProdutoUseCase;
    public static RemoverProdutoUseCase removerProdutoUseCase;

    private static AdicionarVendaUseCase adicionarVendaUseCase;
    public static ListarVendasUseCase listarVendasUseCase;
    private static ModificarVendaUseCase modificarVendaUseCase;
    private static RemoverVendaUseCase removerVendaUseCase;
    private static AdicionarClienteUseCase adicionarClienteUseCase;
    public static ListarClientesUseCase listarClientesUseCase;
    private static ModificarClienteUseCase modificarClienteUseCase;
    public static RemoverClienteUseCase removerClienteUseCase;
    private static AdicionarCampanhaUseCase adicionarCampanhaUseCase;
    public static ListarCampanhasUseCase listarCampanhasUseCase;
    private static ModificarCampanhaUseCase modificarCampanhaUseCase;
    public static RemoverCampanhaUseCase removerCampanhaUseCase;

    private static AdicinarEmpresaUseCase adicinarEmpresaUseCase;
    public static ListarEmpresasUseCase listarEmpresasUseCase;
    private static ModificarEmpresaUseCase modificarEmpresaUseCase;
    public static RemoverEmpresaUseCase removerEmpresaUseCase;

    public static EmitirRelatorioVenda emitirRelatorioVenda;

    public static EmitirRelatorioCliente emitirRelatorioCliente;

    public static EmitirRelatorioProdutos emitirRelatorioProdutos;

    public static EmitirRelatorioEmpresa emitirRelatorioEmpresa;

    public static EmitirRelatorioCampanhas emitirRelatorioCampanhas;


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

    public static void inject() {

        injecaoDependencia();

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
                new BigDecimal("10.0"), true, "666666");

        Produto produto2 = new Produto(222, "Base", CategoriaProdutos.COSMETICOS,
                new BigDecimal("22.0"), true, "666666");

        Produto produto3 = new Produto(333, "Panela de pressão", CategoriaProdutos.COZINHA,
                new BigDecimal("200.0"), true, "123456");

        Produto produto4 = new Produto(444, "Liquidificador master", CategoriaProdutos.COZINHA,
                new BigDecimal("85.0"), true, "666666");

        Produto produto5 = new Produto(555, "Esteira", CategoriaProdutos.SAUDE_BEM_ESTAR,
                new BigDecimal("920.0"), true, "451611");

        Produto produto6 = new Produto(4,444, "Modificado Liq master", CategoriaProdutos.COZINHA,
                new BigDecimal("85.0"), true, "666666");

        //VENDAS
        Venda venda1 = new Venda("15645789544", 111, 100.00F, FormaPagamento.CREDITO, StatusVenda.NAO_ENVIADO);
        Venda venda2 = new Venda("15645789544", 111, 200.00F, FormaPagamento.BOLETO_BANCARIO, StatusVenda.NAO_ENVIADO);
        Venda venda3 = new Venda("22648889544", 333, 600.00F, FormaPagamento.PIX, StatusVenda.ENVIADO);
        Venda venda4 = new Venda("48415548755", 555, 920.0F, FormaPagamento.CREDITO, StatusVenda.ENVIADO);
        Venda venda5 = new Venda(4,"33315548766", 555, 1500.0F, FormaPagamento.CREDITO, StatusVenda.NAO_ENVIADO);

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

        emitirRelatorioVenda.gerarRelatorio();
        emitirRelatorioCliente.gerarRelatorio();
        emitirRelatorioProdutos.gerarRelatorio();
        emitirRelatorioEmpresa.gerarRelatorio();
        emitirRelatorioCampanhas.gerarRelatorio();
        System.out.println("Injetado");
    }

    private static void injecaoDependencia() {
        CampanhaDAO campanhaDAO = new MockedCampanhaDAO();
        ProdutoDAO produtoDAO = new MockedProdutosDAO();
        VendaDAO vendaDAO = new MockedVendasDAO();
        EmpresaDAO empresaDAO = new MockedEmpresaDAO();
        ClienteDAO clienteDAO = new MockedClienteDAO();

        adicionarVendaUseCase = new AdicionarVendaUseCase(produtoDAO, clienteDAO, vendaDAO);
        listarVendasUseCase = new ListarVendasUseCase(vendaDAO);
        modificarVendaUseCase = new ModificarVendaUseCase(vendaDAO);
        removerVendaUseCase = new RemoverVendaUseCase(vendaDAO);

        adicionarProdutoUseCase = new AdicionarProdutoUseCase(produtoDAO, campanhaDAO);
        listarProdutosUseCase = new ListarProdutosUseCase(produtoDAO);
        modificarProdutoUseCase = new ModificarProdutoUseCase(produtoDAO, campanhaDAO);
        removerProdutoUseCase = new RemoverProdutoUseCase(produtoDAO);

        adicionarCampanhaUseCase = new AdicionarCampanhaUseCase(campanhaDAO, empresaDAO);
        listarCampanhasUseCase = new ListarCampanhasUseCase(campanhaDAO);
        modificarCampanhaUseCase = new ModificarCampanhaUseCase(campanhaDAO);
        removerCampanhaUseCase = new RemoverCampanhaUseCase(campanhaDAO);

        adicinarEmpresaUseCase = new AdicinarEmpresaUseCase(empresaDAO);
        listarEmpresasUseCase = new ListarEmpresasUseCase(empresaDAO);
        modificarEmpresaUseCase = new ModificarEmpresaUseCase(empresaDAO);
        removerEmpresaUseCase = new RemoverEmpresaUseCase(empresaDAO, campanhaDAO);

        adicionarClienteUseCase = new AdicionarClienteUseCase(clienteDAO);
        listarClientesUseCase = new ListarClientesUseCase(clienteDAO);
        modificarClienteUseCase = new ModificarClienteUseCase(clienteDAO);
        removerClienteUseCase = new RemoverClienteUseCase(clienteDAO);

        emitirRelatorioVenda = new EmitirRelatorioVenda(vendaDAO);
        emitirRelatorioCliente = new EmitirRelatorioCliente(clienteDAO);
        emitirRelatorioProdutos = new EmitirRelatorioProdutos(produtoDAO);
        emitirRelatorioEmpresa = new EmitirRelatorioEmpresa(empresaDAO);
        emitirRelatorioCampanhas = new EmitirRelatorioCampanhas(campanhaDAO);

    }
}