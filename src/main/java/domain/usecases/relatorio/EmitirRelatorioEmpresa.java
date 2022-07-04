package domain.usecases.relatorio;

import domain.entities.empresa.Empresa;
import domain.usecases.empresa.EmpresaDAO;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

public class EmitirRelatorioEmpresa implements EmitirRelatorio<Empresa>{

    @Override
    public String listToString(List<Empresa> empresaList){
        return empresaList.stream().map(Empresa::toRelatorio)
                .collect(Collectors.joining("\n"));
    }

    @Override
    public void gerarRelatorio(List<Empresa> empresaList){
        String empresasString = listToString(empresaList);
        try (PrintWriter out = new PrintWriter("relatorioEmpresas.csv")) {
            out.println("id, cnpj, Razão Social" + "\n" + empresasString);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Arquivo 'relatorioEmpresas.csv' não encontrado");
        }
    }
}
