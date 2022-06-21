package application.repository.sqlite.DAO;

import application.repository.sqlite.utils.ConnectionFactory;
import domain.entities.venda.FormaPagamento;
import domain.entities.venda.StatusVenda;
import domain.entities.venda.Venda;
import domain.usecases.venda.VendaDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqliteVendaDAO implements VendaDAO {

    private Venda resultSetToEntity(ResultSet rs) throws SQLException {
        return new Venda(
                rs.getInt("id"),
                rs.getString("cpfCliente"),
                rs.getInt("codProduto"),
                FormaPagamento.toEnum(rs.getString("formaPagamento")),
                StatusVenda.toEnum(rs.getString("statusVenda")),
                rs.getInt("qntProduto"),
                rs.getDouble("valorTotal")
        );
    }

    @Override
    public Integer create(Venda venda) {
        String sql = "INSERT INTO Venda" +
                "(cpfCliente, codProduto, formaPagamento, statusVenda, " +
                "qntProduto, valorTotal)"
                +"VALUES (?, ?, ?, ?, ?, ?)";

        try(PreparedStatement ps = ConnectionFactory.createPreparedStatement(sql)){
            setVendaBody(venda, ps);
            ps.execute();

            ResultSet resultSet = ps.getGeneratedKeys();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<Venda> findOne(Integer key) {
        String sql = "SELECT * FROM Venda" +
                " WHERE id = ?";
        Venda venda = null;

        try(PreparedStatement ps = ConnectionFactory.createPreparedStatement(sql)){
            ps.setInt(1, key);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                venda = resultSetToEntity(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(venda);
    }

    @Override
    public List<Venda> findAll() {
        List<Venda> vendas = new ArrayList<>();
        String sql = "SELECT * FROM Venda";

        try (PreparedStatement ps = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Venda venda = resultSetToEntity(resultSet);
                vendas.add(venda);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vendas;
    }

    @Override
    public boolean update(Venda venda) {
        String sql = "UPDATE Venda SET" +
                " cpfCliente = ?, codProduto = ?, formaPagamento = ?, statusVenda = ?," +
                " qntProduto = ?, valorTotal = ?" +
                " WHERE id = ?";

        try (PreparedStatement ps = ConnectionFactory.createPreparedStatement(sql)) {
            setVendaBody(venda, ps);
            ps.setInt(7, venda.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByKey(Integer key) {
        return false;
    }

    @Override
    public boolean delete(Venda venda) {
        return false;
    }

    @Override
    public List<Venda> findVendaByStatus(StatusVenda statusVenda) {
        List<Venda> vendas = new ArrayList<>();
        String sql = "SELECT * FROM Venda WHERE statusVenda = ?";

        try (PreparedStatement ps = ConnectionFactory.createPreparedStatement(sql)) {
            ps.setString(1,statusVenda.toString());
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Venda venda = resultSetToEntity(resultSet);
                vendas.add(venda);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vendas;
    }

    @Override
    public Optional<Venda> findVendaByCpf(String cpf) {
        String sql = "SELECT * FROM Venda" +
                " WHERE cpfCliente = ?";
        Venda venda = null;

        try(PreparedStatement ps = ConnectionFactory.createPreparedStatement(sql)){
            ps.setString(1, cpf);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                venda = resultSetToEntity(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(venda);
    }

    @Override
    public Optional<Venda> findVendaByCodProduto(Integer codProduto) {
        String sql = "SELECT * FROM Venda" +
                " WHERE codProduto = ?";
        Venda venda = null;

        try(PreparedStatement ps = ConnectionFactory.createPreparedStatement(sql)){
            ps.setInt(1, codProduto);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                venda = resultSetToEntity(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(venda);
    }

    @Override
    public boolean updateStatus(Venda venda) {
        StatusVenda statusVenda = venda.getStatusVenda();
        if(statusVenda == StatusVenda.NAO_ENVIADO) {
            venda.setStatusVenda(StatusVenda.ENVIADO);
            update(venda);
        }
        if(statusVenda == StatusVenda.ENVIADO){
            venda.setStatusVenda(StatusVenda.FATURADO);
            update(venda);
        }
        return false;
    }

    private void setVendaBody(Venda venda, PreparedStatement ps) throws SQLException {
        ps.setString(1, venda.getCpfCliente());
        ps.setInt(2, venda.getCodProduto());
        ps.setString(3, venda.getFormaPagamento().toString());
        ps.setString(4, venda.getStatusVenda().toString());
        ps.setInt(5, venda.getQntProduto());
        ps.setDouble(6, venda.getValorTotal());
    }
}
