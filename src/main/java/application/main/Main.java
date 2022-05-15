package application.main;

import application.repository.MockedCampanhaDAO;
import application.repository.MockedEmpresaDAO;
import domain.entities.campanha.Campanha;
import domain.entities.empresa.Empresa;
import domain.usecases.campanha.*;
import domain.usecases.empresa.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public class Main {
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



        //INSERINDO ENTIDADES NO HASHMAP
        Integer emp1 = adicinarEmpresaUseCase.insert(empresa1); //adicionar empresas
        Integer emp2 = adicinarEmpresaUseCase.insert(empresa2);
        Integer emp3 = adicinarEmpresaUseCase.insert(empresa3);
        Integer emp4 = adicinarEmpresaUseCase.insert(empresa4);

        Integer camp1 = adicionarCampanhaUseCase.insert(campanha1);
        Integer camp2 = adicionarCampanhaUseCase.insert(campanha2);

        System.out.println("\nLISTA DE TODAS AS EMPRESAS");
        listarEmpresasUseCase.findAll().forEach(System.out::println);
        System.out.println("\nLISTA DE TODAS AS CAMPANHAS");
        listarCampanhasUseCase.findAll().forEach(System.out::println);

        System.out.println("\nENCONTRAR EMPRESA COM CNPJ '65489'");
        Optional<Empresa> empresaBuscada = listarEmpresasUseCase.findByCnpj("65489"); //encontrar por cnpj
        System.out.println("\n" + empresaBuscada);

        System.out.println("\nENCONTRAR CAMPANHA COM CODIGO '666666'");
        Optional<Campanha> campanhaBuscada = listarCampanhasUseCase.findByCodigo("666666"); //encontrar por código
        System.out.println("\n" + campanhaBuscada);

        //TESTE QUE GERA UM EmpresaRelatedToCampanhaException
        //removerEmpresaUseCase.delete(1);

        //TESTES DE EXCLUSÃO COM SUCESSO
        System.out.println("\n Excluindo a empresa 2");
        removerEmpresaUseCase.delete(emp2); //removendo por cnpj
        listarEmpresasUseCase.findAll().forEach(System.out::println);

        System.out.println("\n Excluindo a campnha 2");
        removerCampanhaUseCase.delete(2);
        listarCampanhasUseCase.findAll().forEach(System.out::println);

        //MODIFICAR EMPRESA COM cnpj = 65489 COM OS DADOS DO OBJETO empresa5
        modificarEmpresaUseCase.update(empresa5);
        System.out.println("\n Modificando razão social");
        System.out.println(listarEmpresasUseCase.findByCnpj("65489"));

        //MODIFICAR CAMPANHA COM codigo = 66666 com os dados de campanha3
        modificarCampanhaUseCase.update(campanha3);
        System.out.println("\n Modificando campanha com codigo 451611");
        System.out.println(listarCampanhasUseCase.findByCodigo("451611"));
    }

    private static void injecaoDependencias() {
        CampanhaDAO campanhaDAO = new MockedCampanhaDAO();
        EmpresaDAO empresaDAO = new MockedEmpresaDAO();

        adicionarCampanhaUseCase = new AdicionarCampanhaUseCase(campanhaDAO, empresaDAO);
        listarCampanhasUseCase = new ListarCampanhasUseCase(campanhaDAO);
        modificarCampanhaUseCase = new ModificarCampanhaUseCase(campanhaDAO);
        removerCampanhaUseCase = new RemoverCampanhaUseCase(campanhaDAO);

        adicinarEmpresaUseCase = new AdicinarEmpresaUseCase(empresaDAO);
        listarEmpresasUseCase = new ListarEmpresasUseCase(empresaDAO);
        modificarEmpresaUseCase = new ModificarEmpresaUseCase(empresaDAO);
        removerEmpresaUseCase = new RemoverEmpresaUseCase(empresaDAO, campanhaDAO);
    }


}
