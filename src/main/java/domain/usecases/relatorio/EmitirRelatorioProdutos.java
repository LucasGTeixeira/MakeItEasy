package domain.usecases.relatorio;

import domain.entities.produto.Produto;
import domain.usecases.produto.ProdutoDAO;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

public class EmitirRelatorioProdutos {
    private final ProdutoDAO produtoDAO;

    public EmitirRelatorioProdutos(ProdutoDAO produtoDAO) {
        this.produtoDAO = produtoDAO;
    }

    public String listProdutosToString(){
        List<Produto> produtosList = produtoDAO.findAll();
        return produtosList.stream().map(Produto::toRelatorio)
                .collect(Collectors.joining("\n"));
    }

    public void gerarRelatorio(){
        String produtosString = listProdutosToString();
        try (PrintWriter out = new PrintWriter("relatorioProduto.csv")) {
            out.println("id, codProduto, nome, categoria, valor, disponibilidade, codCampanha" + "\n" + produtosString);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Arquivo 'relatorioProduto.csv' não encontrado");
        }
    }
}
