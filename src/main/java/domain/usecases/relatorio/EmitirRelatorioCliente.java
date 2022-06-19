package domain.usecases.relatorio;

import domain.entities.cliente.Cliente;
import domain.usecases.cliente.ClienteDAO;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

public class EmitirRelatorioCliente {

    private final ClienteDAO clienteDAO;

    public EmitirRelatorioCliente(ClienteDAO clienteDAO) {
        this.clienteDAO = clienteDAO;
    }

    public String listClientesToString(){
        List<Cliente> ClienteList = clienteDAO.findAll();
        return ClienteList.stream().map(Cliente::toRelatorio)
                .collect(Collectors.joining("\n"));
    }

    public void gerarRelatorio(){
        String clientesString = listClientesToString();
        try (PrintWriter out = new PrintWriter("relatorioClientes.csv")) {
            out.println("id, cpf, nomeCompleto, telefone, email, endereço, status, dataNascimento\n" + clientesString);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Arquivo 'relatorioClientes.csv' não encontrado");
        }
    }
}
