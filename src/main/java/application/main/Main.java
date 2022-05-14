package application.main;

import application.repository.MockedEmpresaDAO;
import domain.entities.empresa.Empresa;
import domain.usecases.empresa.*;

import java.util.List;
import java.util.Optional;


public class Main {
    private static AdicinarEmpresaUseCase adicinarEmpresaUseCase;
    private static ListarEmpresasUseCase listarEmpresasUseCase;
    private static ModificarEmpresaUseCase modificarEmpresaUseCase;
    private static RemoverEmpresaUseCase removerEmpresaUseCase;

    public static void main(String[] args) {

        EmpresaDAO empresaDAO = new MockedEmpresaDAO();
        adicinarEmpresaUseCase = new AdicinarEmpresaUseCase(empresaDAO);
        listarEmpresasUseCase = new ListarEmpresasUseCase(empresaDAO);
        modificarEmpresaUseCase = new ModificarEmpresaUseCase(empresaDAO);
        removerEmpresaUseCase = new RemoverEmpresaUseCase(empresaDAO);

        Empresa empresa1 = new Empresa("15486", "Empresa1 Ltda");
        Empresa empresa2 = new Empresa("15897", "Empresa2 Ltda");
        Empresa empresa3 = new Empresa("65489", "Empresa3 Ltda");
        Empresa empresa4 = new Empresa("11111", "Empresa4 Ltda");
        Empresa empresa5 = new Empresa(3,"65489", "EmpresaModificada Ltda");

        Integer emp1 = adicinarEmpresaUseCase.insert(empresa1); //adicionar empresas
        Integer emp2 = adicinarEmpresaUseCase.insert(empresa2);
        Integer emp3 = adicinarEmpresaUseCase.insert(empresa3);
        Integer emp4 = adicinarEmpresaUseCase.insert(empresa4);


        Optional<Empresa> empresaBuscada = listarEmpresasUseCase.findByCnpj("65489"); //encontrar por cnpj
        System.out.println("\n" + empresaBuscada);

        removerEmpresaUseCase.delete(1); //removendo por id
        System.out.println("\n Excluindo a empresa de ID = 1");
        listarEmpresasUseCase.findAll().forEach(System.out::println);

        removerEmpresaUseCase.delete(emp4); //removendo por cnpj
        System.out.println("\n Excluindo a empresa 2");
        listarEmpresasUseCase.findAll().forEach(System.out::println);

        modificarEmpresaUseCase.update(empresa5);
        System.out.println("\n Modificando razão social");
        System.out.println(listarEmpresasUseCase.findByCnpj("65489"));
    }


}
