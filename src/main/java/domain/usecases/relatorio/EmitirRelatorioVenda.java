package domain.usecases.relatorio;

import domain.entities.venda.Venda;
import domain.usecases.venda.VendaDAO;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

public class EmitirRelatorioVenda implements EmitirRelatorio<Venda>{

    @Override
    public String listToString(List<Venda> vendasList){
        return vendasList.stream().map(Venda::toString)
                .collect(Collectors.joining("\n"));
    }

    @Override
    public void gerarRelatorio(List<Venda> listaFiltrada){
        String vendasString = listToString(listaFiltrada);
        try (PrintWriter out = new PrintWriter("relatorioVenda.csv")) {
            out.println("id, cpfCliente, codProduto, valorTotal, formaPagamento, statusVenda\n" + vendasString);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Arquivo 'relatorioVenda.csv' não encontrado");
        }
    }

}
