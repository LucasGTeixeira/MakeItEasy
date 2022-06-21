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
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class TestesEntities {

    private static AdicionarProdutoUseCase adicionarProdutoUseCase;
    private static ListarProdutosUseCase listarProdutosUseCase;
    private static ModificarProdutoUseCase modificarProdutoUseCase;
    private static RemoverProdutoUseCase removerProdutoUseCase;

    private static AdicionarVendaUseCase adicionarVendaUseCase;
    private static ListarVendasUseCase listarVendasUseCase;
    private static ModificarVendaUseCase modificarVendaUseCase;

    private static AdicionarClienteUseCase adicionarClienteUseCase;
    private static ListarClientesUseCase listarClientesUseCase;
    private static ModificarClienteUseCase modificarClienteUseCase;
    private static RemoverClienteUseCase removerClienteUseCase;

    private static AdicionarCampanhaUseCase adicionarCampanhaUseCase;
    private static ListarCampanhasUseCase listarCampanhasUseCase;
    private static ModificarCampanhaUseCase modificarCampanhaUseCase;
    private static RemoverCampanhaUseCase removerCampanhaUseCase;

    private static AdicinarEmpresaUseCase adicinarEmpresaUseCase;
    private static ListarEmpresasUseCase listarEmpresasUseCase;
    private static ModificarEmpresaUseCase modificarEmpresaUseCase;
    private static RemoverEmpresaUseCase removerEmpresaUseCase;

    public static EmitirRelatorioVenda emitirRelatorioVenda;
    public static EmitirRelatorioCliente emitirRelatorioCliente;
    public static EmitirRelatorioProdutos emitirRelatorioProdutos;
    public static EmitirRelatorioEmpresa emitirRelatorioEmpresa;
    public static EmitirRelatorioCampanhas emitirRelatorioCampanhas;

    public static void main(String[] args) {
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
        Venda venda2 = new Venda("15645789544", 111,  FormaPagamento.BOLETO_BANCARIO, StatusVenda.ENVIADO,2);
        Venda venda3 = new Venda("22648889544", 333,  FormaPagamento.PIX, StatusVenda.ENVIADO, 3);
        Venda venda4 = new Venda("48415548755", 555,  FormaPagamento.CREDITO, StatusVenda.NAO_ENVIADO,1);
        Venda venda5 = new Venda(4,"48415548755", 555,  FormaPagamento.CREDITO, StatusVenda.NAO_ENVIADO,1, 920.0);

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

        //LISTAS COM SUCESSO
        System.out.println("\nLISTA DE TODAS AS EMPRESAS");
        listarEmpresasUseCase.findAll().forEach(System.out::println);

        System.out.println("\nLISTA DE TODAS AS CAMPANHAS");
        listarCampanhasUseCase.findAll().forEach(System.out::println);

        System.out.println("\nLISTA DE TODOS OS CLIENTES");
        listarClientesUseCase.findAll().forEach(System.out::println);

        System.out.println("\nLISTA DE TODOS OS PRODUTOS");
        listarProdutosUseCase.findAll().forEach(System.out::println);

        System.out.println("\nLISTA DE TODOS OS VENDAS");
        listarVendasUseCase.findAll().forEach(System.out::println);

        System.out.println("\nLISTA DE TODAS OS VENDAS");
        listarVendasUseCase.findAll().forEach(System.out::println);

        System.out.println("\nLISTA DE TODAS OS VENDAS NÃO ENVIADAS");
        listarVendasUseCase.findVendaByStatus(StatusVenda.NAO_ENVIADO).forEach(System.out::println);

        System.out.println("\nLISTA DE TODAS OS VENDAS ENVIADAS");
        listarVendasUseCase.findVendaByStatus(StatusVenda.ENVIADO).forEach(System.out::println);


        //---------------- PESQUISAS COM SUCESSO ----------------
        System.out.println("\nENCONTRAR EMPRESA COM CNPJ '65489'");
        Optional<Empresa> empresaBuscada = listarEmpresasUseCase.findByCnpj("65489"); //encontrar por cnpj
        empresaBuscada.ifPresent(System.out::println);

        System.out.println("\nENCONTRAR CAMPANHA COM CODIGO '666666'");
        Optional<Campanha> campanhaBuscada = listarCampanhasUseCase.findByCodigo("666666"); //encontrar por código
        campanhaBuscada.ifPresent(System.out::println);

        System.out.println("\nENCONTRAR CLIENTE COM CPF = 15645789544");
        Optional<Cliente> clienteBuscado = listarClientesUseCase.findByCpf("15645789544");
        clienteBuscado.ifPresent(System.out::println);

        System.out.println("\nENCONTRAR PRODUTO COM CODIGO = 111");
        Optional<Produto> produtoBuscado = listarProdutosUseCase.findByCodProduto(111);
        produtoBuscado.ifPresent(System.out::println);

        System.out.println("\nENCONTRAR VENDA DE CODIGO = 2");
        Optional<Venda> vendaBuscado = listarVendasUseCase.findOne(2);
        vendaBuscado.ifPresent(System.out::println);

        //---------------- TESTE QUE GERA UM EmpresaRelatedToCampanhaException ----------------
//        removerEmpresaUseCase.delete(1);

        // ---------------- TESTES DE EXCLUSÃO COM SUCESSO ---------------------
        System.out.println("\n EXCLUINDO EMPRESA: 2");
        removerEmpresaUseCase.delete(2);
        listarEmpresasUseCase.findAll().forEach(System.out::println);

//        System.out.println("\n EXCLUINDO CAMPANHA: 2");
//        removerCampanhaUseCase.delete(2);
//        listarCampanhasUseCase.findAll().forEach(System.out::println);

        System.out.println("\n EXCLUINDO CLIENTE: 1");
        removerClienteUseCase.delete(1);
        listarClientesUseCase.findAll().forEach(System.out::println);

        System.out.println("\n EXCLUINDO PRODUTO: 2");
        removerProdutoUseCase.delete(2);
        listarProdutosUseCase.findAll().forEach(System.out::println);

        // --------------- MODIFICAÇÕES COM SUCESSO ----------------------
        modificarEmpresaUseCase.update(empresa5);
        System.out.println("\n Modificando razão social");
        Optional<Empresa> empresaModificada = listarEmpresasUseCase.findByCnpj("65489");
        empresaModificada.ifPresent(System.out::println);


        modificarCampanhaUseCase.update(campanha3);
        System.out.println("\n Modificando campanha com codigo 451611");
        Optional<Campanha> campanhaOptional = listarCampanhasUseCase.findByCodigo("451611");
        campanhaOptional.ifPresent(System.out::println);


        System.out.println("\n MODIFICANDO CLIENTE 2 COM OS DADOS DE CLIENTE 3");
        modificarClienteUseCase.update(cliente3);
        Optional<Cliente> clienteOptional = listarClientesUseCase.findByCpf("15645789544");
        clienteOptional.ifPresent(System.out::println);

        System.out.println("\n MODIFICANDO PRODUTO 4");
        modificarProdutoUseCase.update(produto6);
        Optional<Produto> produtoOptional = listarProdutosUseCase.findByCodProduto(111);
        produtoOptional.ifPresent(System.out::println);


        System.out.println("\n UPDATE VENDA 4");
        modificarVendaUseCase.updateStatus(venda5);
        Optional<Venda> vendaEnviada = listarVendasUseCase.findOne(4);
        vendaEnviada.ifPresent(System.out::println);

        System.out.println("\n UPDATE VENDA 4");
        modificarVendaUseCase.updateStatus(venda5);
        Optional<Venda> vendaFaturada = listarVendasUseCase.findOne(4);
        vendaFaturada.ifPresent(System.out::println);

        System.out.println("\nLISTA DE TODAS OS VENDAS FATURADAS");
        listarVendasUseCase.findVendaByStatus(StatusVenda.FATURADO).forEach(System.out::println);

        List<Venda> listaVendasFaturadas = listarVendasUseCase.findVendaByStatus(StatusVenda.FATURADO);
        List<Venda> listaVendasNaoEnviadas = listarVendasUseCase.findVendaByStatus(StatusVenda.NAO_ENVIADO);
        List<Venda> listaVendasEnviadas = listarVendasUseCase.findVendaByStatus(StatusVenda.ENVIADO);

        emitirRelatorioVenda.gerarRelatorio(listaVendasEnviadas);
        emitirRelatorioCliente.gerarRelatorio();
        emitirRelatorioProdutos.gerarRelatorio();
        emitirRelatorioEmpresa.gerarRelatorio();
        emitirRelatorioCampanhas.gerarRelatorio();

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
}