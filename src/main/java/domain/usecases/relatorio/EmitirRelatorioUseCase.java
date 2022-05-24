package domain.usecases.relatorio;

import domain.entities.venda.Venda;
import domain.usecases.venda.VendaDAO;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

public class EmitirRelatorioUseCase {

    private final VendaDAO vendaDAO;

    public EmitirRelatorioUseCase(VendaDAO vendaDAO) {
        this.vendaDAO = vendaDAO;
    }

    public String listVendasToString(){
        List<Venda> vendasList = vendaDAO.findAll();
        return vendasList.stream().map(Venda::toString)
                .collect(Collectors.joining("\n"));
    }

    public void gerarRelatorio(){
        String vendasString = listVendasToString();
        try (PrintWriter out = new PrintWriter("relatorio.csv")) {
            out.println("id, cpfCliente, codProduto, valorTotal, formaPagamento, statusVenda\n" + vendasString);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Arquivo 'relatorio.csv' não encontrado");
        }
    }
}
