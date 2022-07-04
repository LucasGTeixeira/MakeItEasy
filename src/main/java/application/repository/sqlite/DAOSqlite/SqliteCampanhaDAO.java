package application.repository.sqlite.DAOSqlite;

import application.repository.sqlite.utils.ConnectionFactory;
import domain.entities.campanha.Campanha;
import domain.usecases.campanha.CampanhaDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqliteCampanhaDAO implements CampanhaDAO {

    private Campanha resultSetToEntity(ResultSet rs) throws SQLException {
        return new Campanha(
                rs.getInt("id"),
                rs.getString("codigo"),
                rs.getString("nome"),
                rs.getString("edicao"),
                LocalDate.parse(rs.getString("dataLancamento")),
                LocalDate.parse(rs.getString("dataExpiracao")),
                rs.getString("cnpjEmpresa")
        );
    }

    @Override
    public Integer create(Campanha campanha) {
        String sql = "INSERT INTO Campanha" +
                "(codigo, nome, edicao, dataLancamento, dataExpiracao, cnpjEmpresa)"
                +"VALUES (?, ?, ?, ?, ?, ?)";

        try(PreparedStatement ps = ConnectionFactory.createPreparedStatement(sql)){
            ps.setString(1, campanha.getCodigo());
            ps.setString(2, campanha.getNome());
            ps.setString(3, campanha.getEdicao());
            ps.setString(4, campanha.getDataLancamento().toString());
            ps.setString(5, campanha.getDataExpiracao().toString());
            ps.setString(6, campanha.getCnpjEmpresa());
            ps.execute();

            ResultSet resultSet = ps.getGeneratedKeys();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<Campanha> findByCodigo(String codigo) {
        String sql = "SELECT * FROM Campanha" +
                " WHERE codigo = ?";
        return getCampanha(codigo, sql);
    }

    @Override
    public Optional<Campanha> findByCnpj(String cnpj) {
        String sql = "SELECT * FROM Campanha" +
                " WHERE cnpjEmpresa = ?";
        return getCampanha(cnpj, sql);
    }

    private Optional<Campanha> getCampanha(String attribute, String sql) {
        Campanha campanha = null;
        try(PreparedStatement ps = ConnectionFactory.createPreparedStatement(sql)){
            ps.setString(1, attribute);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                campanha = resultSetToEntity(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(campanha);
    }

    @Override
    public Optional<Campanha> findOne(Integer key) {
        String sql = "SELECT * FROM Campanha" +
                " WHERE id = ?";
        Campanha campanha = null;

        try(PreparedStatement ps = ConnectionFactory.createPreparedStatement(sql)){
            ps.setInt(1, key);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                campanha = resultSetToEntity(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(campanha);
    }

    @Override
    public List<Campanha> findAll() {
        List<Campanha> campanhas = new ArrayList<>();
        String sql = "SELECT * FROM Campanha";

        try (PreparedStatement ps = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Campanha campanha = resultSetToEntity(resultSet);
                campanhas.add(campanha);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return campanhas;
    }

    @Override
    public boolean update(Campanha campanha) {
        String sql = "UPDATE Campanha SET nome = ?, edicao = ?," +
                " dataLancamento = ?, dataExpiracao = ?, cnpjEmpresa = ?" +
                " WHERE id = ?";

        try (PreparedStatement ps = ConnectionFactory.createPreparedStatement(sql)) {
            ps.setString(1, campanha.getNome());
            ps.setString(2, campanha.getEdicao());
            ps.setString(3, campanha.getDataLancamento().toString());
            ps.setString(4, campanha.getDataExpiracao().toString());
            ps.setString(5, campanha.getCnpjEmpresa());
            ps.setInt(6, campanha.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByKey(Integer key) {
        String sql = "DELETE FROM Campanha WHERE id = ?";

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
    public boolean delete(Campanha campanha) {
        if (campanha == null)
            throw new IllegalArgumentException("Campanha não pode ser nula");
        if (campanha.getId() == null)
            throw new IllegalArgumentException("Id de campanha não pode ser nulo");
        return deleteByKey(campanha.getId());
    }
}
