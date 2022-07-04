package domain.usecases.relatorio;

import domain.entities.cliente.Cliente;
import domain.usecases.cliente.ClienteDAO;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

public class EmitirRelatorioCliente implements EmitirRelatorio<Cliente>{

    @Override
    public String listToString(List<Cliente> clienteList){
        return clienteList.stream().map(Cliente::toRelatorio)
                .collect(Collectors.joining("\n"));
    }

    @Override
    public void gerarRelatorio(List<Cliente> clienteList){
        String clienteString = listToString(clienteList);
        try (PrintWriter out = new PrintWriter("relatorioClientes.csv")) {
            out.println("id, cpf, nomeCompleto, telefone, email, endereço, status, dataNascimento\n" + clienteList);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Arquivo 'relatorioClientes.csv' não encontrado");
        }
    }
}
