package application.repository.sqlite.utils;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Statement;

public class TableCreator {

    public void buildDatabaseIfMissing() {
        if (isDatabaseMissing()) {
            System.out.println("Database is missing. Building database: \n");
            buildTables();
        }
    }

    private boolean isDatabaseMissing() {
        return !Files.exists(Paths.get("database.db"));
    }

    private void buildTables() {
        try (Statement statement = ConnectionFactory.createStatement()) {
            statement.addBatch(createClienteTable());
            statement.addBatch(createProdutoTable());
            statement.addBatch(createVendaTable());
            statement.addBatch(createCampanhaTable());
            statement.addBatch(createEmpresaTable());
            statement.executeBatch();

            System.out.println("Database successfully created.");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private String createClienteTable() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE Cliente  (\n");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT, \n");
        builder.append("cpf TEXT NOT NULL UNIQUE, \n");
        builder.append("nomeCompleto TEXT NOT NULL, \n");
        builder.append("telefone TEXT NOT NULL, \n");
        builder.append("email TEXT NOT NULL, \n");
        builder.append("endereco TEXT NOT NULL, \n");
        builder.append("status TEXT NOT NULL, \n");
        builder.append("dataNascimento TEXT NOT NULL \n");
        builder.append("); \n");

        System.out.println(builder);
        return builder.toString();
    }

    private String createCampanhaTable() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE Campanha  (\n");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT, \n");
        builder.append("codigo TEXT NOT NULL UNIQUE, \n");
        builder.append("nome TEXT NOT NULL UNIQUE, \n");
        builder.append("edicao TEXT NOT NULL, \n");
        builder.append("dataLancamento TEXT NOT NULL, \n");
        builder.append("dataExpiracao TEXT NOT NULL, \n");
        builder.append("cnpjEmpresa TEXT NOT NULL \n");
        builder.append("); \n");

        System.out.println(builder);
        return builder.toString();
    }

    private String createEmpresaTable() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE Empresa  (\n");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT, \n");
        builder.append("cnpj TEXT NOT NULL UNIQUE, \n");
        builder.append("razaoSocial TEXT NOT NULL \n");
        builder.append("); \n");

        System.out.println(builder);
        return builder.toString();
    }

    private String createProdutoTable() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE Produto  (\n");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT, \n");
        builder.append("codProduto INTEGER NOT NULL UNIQUE, \n");
        builder.append("nome TEXT NOT NULL, \n");
        builder.append("categoria TEXT NOT NULL, \n");
        builder.append("valor DECIMAL(5,2), \n");
        builder.append("disponibilidade BOOLEAN NOT NULL, \n");
        builder.append("codCampanha TEXT NOT NULL \n");
        builder.append("); \n");

        System.out.println(builder);
        return builder.toString();
    }

    private String createVendaTable() {
        StringBuilder builder = new StringBuilder();

        builder.append("CREATE TABLE Venda (\n");
        builder.append("id INTEGER PRIMARY KEY AUTOINCREMENT, \n");
        builder.append("cpfCliente TEXT NOT NULL, \n");
        builder.append("codProduto INTEGER NOT NULL, \n");
        builder.append("formaPagamento TEXT NOT NULL, \n");
        builder.append("statusVenda TEXT NOT NULL, \n");
        builder.append("qntProduto INTEGER NOT NULL, \n");
        builder.append("valorTotal DECIMAL(5,2) NOT NULL \n");
        builder.append("); \n");

        System.out.println(builder);
        return builder.toString();
    }
}
