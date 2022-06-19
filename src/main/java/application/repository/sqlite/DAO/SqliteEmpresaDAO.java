package application.repository.sqlite.DAO;

import application.repository.sqlite.utils.ConnectionFactory;
import domain.entities.empresa.Empresa;
import domain.usecases.empresa.EmpresaDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqliteEmpresaDAO  implements EmpresaDAO {

    private Empresa resultSetToEntity(ResultSet rs) throws SQLException {
        return new Empresa(
                rs.getInt("id"),
                rs.getString("cnpj"),
                rs.getString("razaoSocial")
        );
    }

    @Override
    public Integer create(Empresa empresa) {
        String sql = "INSERT INTO Empresa" +
                "(cnpj, razaoSocial)"
                +"VALUES (?, ?)";

        try(PreparedStatement ps = ConnectionFactory.createPreparedStatement(sql)){
            ps.setString(1, empresa.getCnpj());
            ps.setString(2, empresa.getRazaoSocial());
            ps.execute();

            ResultSet resultSet = ps.getGeneratedKeys();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<Empresa> findByCnpj(String cnpj) {
        String sql = "SELECT * FROM Empresa" +
                " WHERE cnpj = ?";
        return getEmpresa(cnpj, sql);
    }

    private Optional<Empresa> getEmpresa(String attribute, String sql) {
        Empresa empresa = null;
        try (PreparedStatement ps = ConnectionFactory.createPreparedStatement(sql)) {
            ps.setString(1, attribute);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                empresa = resultSetToEntity(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(empresa);
    }

    @Override
    public Optional<Empresa> findOne(Integer key) {
        String sql = "SELECT * FROM Empresa" +
                " WHERE id = ?";
        Empresa empresa = null;

        try(PreparedStatement ps = ConnectionFactory.createPreparedStatement(sql)){
            ps.setInt(1, key);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                empresa = resultSetToEntity(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(empresa);
    }

    @Override
    public List<Empresa> findAll() {
        List<Empresa> empresas = new ArrayList<>();
        String sql = "SELECT * FROM Empresa";

        try (PreparedStatement ps = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Empresa empresa = resultSetToEntity(resultSet);
                empresas.add(empresa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empresas;
    }

    @Override
    public boolean update(Empresa empresa) {
        String sql = "UPDATE Empresa SET" +
                " cnpj = ?, razaoSocial = ?" +
                " WHERE id = ?";

        try (PreparedStatement ps = ConnectionFactory.createPreparedStatement(sql)) {
            ps.setString(1, empresa.getCnpj());
            ps.setString(2, empresa.getRazaoSocial());
            ps.setInt(3, empresa.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByKey(Integer key) {
        String sql = "DELETE FROM Empresa WHERE id = ?";

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
    public boolean delete(Empresa empresa) {
        if (empresa == null || empresa.getId() == null)
            throw new IllegalArgumentException("Id de empresa não pode ser nulo");
        return deleteByKey(empresa.getId());
    }
}
