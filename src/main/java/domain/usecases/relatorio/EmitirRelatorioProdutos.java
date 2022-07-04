package domain.usecases.relatorio;

import domain.entities.produto.Produto;
import domain.usecases.produto.ProdutoDAO;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

public class EmitirRelatorioProdutos implements EmitirRelatorio<Produto>{

    @Override
    public String listToString(List<Produto> produtosList){
        return produtosList.stream().map(Produto::toRelatorio)
                .collect(Collectors.joining("\n"));
    }

    @Override
    public void gerarRelatorio(List<Produto> produtosList){
        String produtosString = listToString(produtosList);
        try (PrintWriter out = new PrintWriter("relatorioProduto.csv")) {
            out.println("id, codProduto, nome, categoria, valor, disponibilidade, codCampanha" + "\n" + produtosString);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Arquivo 'relatorioProduto.csv' não encontrado");
        }
    }
}
