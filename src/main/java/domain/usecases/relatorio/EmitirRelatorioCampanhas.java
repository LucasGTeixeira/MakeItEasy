package domain.usecases.relatorio;

import domain.entities.campanha.Campanha;
import domain.usecases.campanha.CampanhaDAO;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

public class EmitirRelatorioCampanhas implements EmitirRelatorio<Campanha>{

    @Override
    public String listToString(List<Campanha> listCampanha){
        return listCampanha.stream().map(Campanha::toRelatorio)
                .collect(Collectors.joining("\n"));
    }

    @Override
    public void gerarRelatorio(List<Campanha> listCampanha){
        String campanhasString = listToString(listCampanha);
        try (PrintWriter out = new PrintWriter("relatorioCampanhas.csv")) {
            out.println("id, codigo, nome, edicao, dataLancamento, dataExpiracao, cnpjEmpresa\n" + campanhasString);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Arquivo 'relatorioCampanhas.csv' não encontrado");
        }
    }
}
