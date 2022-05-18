package application.main;

import application.repository.*;
import domain.entities.campanha.Campanha;
import domain.entities.cliente.Cliente;
import domain.entities.cliente.ClienteStatus;
import domain.entities.empresa.Empresa;
import domain.entities.produto.Produto;
import domain.entities.venda.Venda;
import domain.usecases.campanha.*;
import domain.usecases.cliente.*;
import domain.usecases.empresa.*;
import domain.usecases.produto.*;
import domain.usecases.venda.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public class Main {

    private static AdicionarProdutoUseCase adicionarProdutoUseCase;
    private static ListarProdutosUseCase listarProdutosUseCase;
    private static ModificarProdutoUseCase modificarProdutoUseCase;
    private static RemoverProdutoUseCase removerProdutoUseCase;

    private static AdicionarVendaUseCase adicionarVendaUseCase;
    private static ListarVendasUseCase listarVendasUseCase;
    private static ModificarVendaUseCase modificarVendaUseCase;
    private static RemoverVendaUseCase removerVendaUseCase;
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

    public static void main(String[] args) {

        injecaoDependencias();

        //DECALRANDO OBJETOS (SEM ID, POIS SERÁ AUTOINCREMENTADO)
        Empresa empresa1 = new Empresa("15486", "Empresa1 Ltda");
        Empresa empresa2 = new Empresa("15897", "Empresa2 Ltda");
        Empresa empresa3 = new Empresa("65489", "Empresa3 Ltda");
        Empresa empresa4 = new Empresa("11111", "Empresa4 Ltda");
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

        Cliente cliente1 = new Cliente("48415548755", "Carlos Antonio da Silva",
                "997884512", "carlos@email.com", "Av. Fioravante Bertaquini",
                ClienteStatus.ATIVO, LocalDate.of(2000, 2, 2));

        Cliente cliente2 = new Cliente("15645789544", "Pedro Antonio da Silva",
                "998774411", "pedro@email.com", "Av. Fioravante Bertaquini",
                ClienteStatus.ATIVO, LocalDate.of(2000, 2, 2));

        Cliente cliente3 = new Cliente(2,"15645789544", "Nome Modificado",
                "998774411", "pedroModificado@email.com", "Av. Fioravante Bertaquini",
                ClienteStatus.ATIVO, LocalDate.of(2000, 2, 2));


        //INSERINDO ENTIDADES NO HASHMAP
        Integer emp1 = adicinarEmpresaUseCase.insert(empresa1);
        Integer emp2 = adicinarEmpresaUseCase.insert(empresa2);
        Integer emp3 = adicinarEmpresaUseCase.insert(empresa3);
        Integer emp4 = adicinarEmpresaUseCase.insert(empresa4);

        Integer camp1 = adicionarCampanhaUseCase.insert(campanha1);
        Integer camp2 = adicionarCampanhaUseCase.insert(campanha2);

        Integer cli1 = adicionarClienteUseCase.insert(cliente1);
        Integer cli2 = adicionarClienteUseCase.insert(cliente2);

        //LISTAS COM SUCESSO
        System.out.println("\nLISTA DE TODAS AS EMPRESAS");
        listarEmpresasUseCase.findAll().forEach(System.out::println);

        System.out.println("\nLISTA DE TODAS AS CAMPANHAS");
        listarCampanhasUseCase.findAll().forEach(System.out::println);

        System.out.println("\nLISTA DE TODOS OS CLIENTES");
        listarClientesUseCase.findAll().forEach(System.out::println);
        //---------------- PESQUISAS COM SUCESSO ----------------
//        System.out.println("\nENCONTRAR EMPRESA COM CNPJ '65489'");
//        Optional<Empresa> empresaBuscada = listarEmpresasUseCase.findByCnpj("65489"); //encontrar por cnpj
//        System.out.println("\n" + empresaBuscada);
//
//        System.out.println("\nENCONTRAR CAMPANHA COM CODIGO '666666'");
//        Optional<Campanha> campanhaBuscada = listarCampanhasUseCase.findByCodigo("666666"); //encontrar por código
//        System.out.println("\n" + campanhaBuscada);
//
//        System.out.println("\nENCONTRAR CLIENTE COM CPF = 15645789544");
//        Optional<Cliente> clienteBuscado = listarClientesUseCase.findByCpf("15645789544");
//        System.out.println(clienteBuscado);

        //---------------- TESTE QUE GERA UM EmpresaRelatedToCampanhaException ----------------
        //removerEmpresaUseCase.delete(1);

        // ---------------- TESTES DE EXCLUSÃO COM SUCESSO ---------------------
//        System.out.println("\n EXCLUINDO EMPRESA 2");
//        removerEmpresaUseCase.delete(emp2); //removendo por cnpj
//        listarEmpresasUseCase.findAll().forEach(System.out::println);
//
//        System.out.println("\n EXCLUINDO CAMPANHA 2");
//        removerCampanhaUseCase.delete(2);
//        listarCampanhasUseCase.findAll().forEach(System.out::println);
//
//        System.out.println("\n EXCLUINDO CLIENTE 1");
//        removerClienteUseCase.delete(1);
//        listarClientesUseCase.findAll().forEach(System.out::println);

        // --------------- MODIFICAÇÕES COM SUCESSO ----------------------
//        modificarEmpresaUseCase.update(empresa5);
//        System.out.println("\n Modificando razão social");
//        System.out.println(listarEmpresasUseCase.findByCnpj("65489"));
//
//
//        modificarCampanhaUseCase.update(campanha3);
//        System.out.println("\n Modificando campanha com codigo 451611");
//        System.out.println(listarCampanhasUseCase.findByCodigo("451611"));

//        System.out.println("\n MODIFICANDO CLIENTE 2 COM OS DADOS DE CLIENTE 3");
//        modificarClienteUseCase.update(cliente3);
//        System.out.println(listarClientesUseCase.findByCpf("15645789544"));
    }

    private static void injecaoDependencias() {
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
        modificarVendaUseCase = new ModificarVendaUseCase(vendaDAO);
        removerVendaUseCase = new RemoverVendaUseCase(vendaDAO);

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


    }


}
