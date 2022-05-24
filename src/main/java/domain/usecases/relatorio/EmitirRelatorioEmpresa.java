package domain.usecases.relatorio;

import domain.entities.empresa.Empresa;
import domain.usecases.empresa.EmpresaDAO;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

public class EmitirRelatorioEmpresa {

    private final EmpresaDAO empresaDAO;

    public EmitirRelatorioEmpresa(EmpresaDAO empresaDAO) {
        this.empresaDAO = empresaDAO;
    }

    public String listEmpresasToString(){
        List<Empresa> empresaList = empresaDAO.findAll();
        return empresaList.stream().map(Empresa::toString)
                .collect(Collectors.joining("\n"));
    }

    public void gerarRelatorio(){
        String empresasString = listEmpresasToString();
        try (PrintWriter out = new PrintWriter("relatorioEmpresas.csv")) {
            out.println("id, cnpj, Razão Social" + "\n" + empresasString);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Arquivo 'relatorioEmpresas.csv' não encontrado");
        }
    }
}
