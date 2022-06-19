package application.repository.sqlite.DAO;

import application.repository.sqlite.utils.ConnectionFactory;
import domain.entities.cliente.Cliente;
import domain.entities.cliente.ClienteStatus;
import domain.usecases.cliente.ClienteDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqliteClienteDAO implements ClienteDAO {

    private Cliente resultSetToEntity(ResultSet rs) throws SQLException {
        return new Cliente(
                rs.getInt("id"),
                rs.getString("cpf"),
                rs.getString("nomeCompleto"),
                rs.getString("telefone"),
                rs.getString("email"),
                rs.getString("endereco"),
                ClienteStatus.toEnum(rs.getString("status")),
                LocalDate.parse(rs.getString("dataNascimento"))
        );
    }

    @Override
    public Integer create(Cliente cliente) {
        String sql = "INSERT INTO Cliente" +
                "(cpf, nomeCompleto, telefone, email, endereco, status, dataNascimento)"
                +" VALUES (?, ?, ?, ?, ?, ?, ?)";

        try(PreparedStatement ps = ConnectionFactory.createPreparedStatement(sql)){
            ps.setString(1, cliente.getCpf());
            ps.setString(2, cliente.getNomeCompleto());
            ps.setString(3, cliente.getTelefone());
            ps.setString(4, cliente.getEmail());
            ps.setString(5, cliente.getEndereco());
            ps.setString(6, cliente.getStatus().toString());
            ps.setString(7, cliente.getDataNascimento().toString());
            ps.execute();

            ResultSet resultSet = ps.getGeneratedKeys();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<Cliente> findByCpf(String cpf) {
        String sql = "SELECT * FROM Cliente" +
                " WHERE cpf = ?";
        return getCliente(cpf, sql);
    }

    private Optional<Cliente> getCliente(String attribute, String sql) {
        Cliente cliente = null;
        try (PreparedStatement ps = ConnectionFactory.createPreparedStatement(sql)) {
            ps.setString(1, attribute);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                cliente = resultSetToEntity(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(cliente);
    }

    @Override
    public Optional<Cliente> findOne(Integer key) {
        String sql = "SELECT * FROM Cliente" +
                " WHERE id = ?";
        Cliente cliente = null;

        try(PreparedStatement ps = ConnectionFactory.createPreparedStatement(sql)){
            ps.setInt(1, key);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                cliente = resultSetToEntity(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(cliente);
    }

    @Override
    public List<Cliente> findAll() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM Cliente";

        try (PreparedStatement ps = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Cliente cliente = resultSetToEntity(resultSet);
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    @Override
    public boolean update(Cliente cliente) {
        String sql = "UPDATE Cliente SET" +
                " nomeCompleto = ?, telefone = ?, email = ?," +
                " endereco = ?, status = ?, dataNascimento = ?" +
                " WHERE id = ?";

        try (PreparedStatement ps = ConnectionFactory.createPreparedStatement(sql)) {
            setClienteBody(cliente, ps);
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByKey(Integer key) {
        String sql = "DELETE FROM Cliente WHERE id = ?";

        try (PreparedStatement ps = ConnectionFactory.createPreparedStatement(sql)) {
            ps.setInt(1, key);
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Cliente cliente) {
        if (cliente == null || cliente.getId() == null)
            throw new IllegalArgumentException("Id de cliente não pode ser nulo");
        return deleteByKey(cliente.getId());
    }

    private void setClienteBody(Cliente cliente, PreparedStatement ps) throws SQLException {
        ps.setString(1, cliente.getNomeCompleto());
        ps.setString(2, cliente.getTelefone());
        ps.setString(3, cliente.getEmail());
        ps.setString(4, cliente.getEndereco());
        ps.setString(5, cliente.getStatus().toString());
        ps.setString(6, cliente.getDataNascimento().toString());
        ps.setInt(7, cliente.getId());

    }
}
