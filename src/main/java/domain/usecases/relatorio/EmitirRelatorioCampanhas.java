package domain.usecases.relatorio;

import domain.entities.campanha.Campanha;
import domain.entities.cliente.Cliente;
import domain.usecases.campanha.CampanhaDAO;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

public class EmitirRelatorioCampanhas {

    private final CampanhaDAO campanhaDAO;

    public EmitirRelatorioCampanhas(CampanhaDAO campanhaDAO) {
        this.campanhaDAO = campanhaDAO;
    }

    public String listCampanhasToString(){
        List<Campanha> CampanhaList = campanhaDAO.findAll();
        return CampanhaList.stream().map(Campanha::toString)
                .collect(Collectors.joining("\n"));
    }

    public void gerarRelatorio(){
        String campanhasString = listCampanhasToString();
        try (PrintWriter out = new PrintWriter("relatorioCampanhas.csv")) {
            out.println("id, codigo, nome, edicao, dataLancamento, dataExpiracao, cnpjEmpresa\n" + campanhasString);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Arquivo 'relatorioCampanhas.csv' não encontrado");
        }
    }
}
