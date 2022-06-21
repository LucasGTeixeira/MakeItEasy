package application.repository.sqlite.DAO;

import application.repository.sqlite.utils.ConnectionFactory;
import domain.entities.produto.CategoriaProdutos;
import domain.entities.produto.Produto;
import domain.usecases.produto.ProdutoDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SqliteProdutoDAO implements ProdutoDAO {

    private Produto resultSetToEntity(ResultSet rs) throws SQLException {
        return new Produto(
                rs.getInt("id"),
                rs.getInt("codProduto"),
                rs.getString("nome"),
                CategoriaProdutos.toEnum(rs.getString("categoria")),
                rs.getDouble("valor"),
                rs.getBoolean("disponibilidade"),
                rs.getString("codCampanha")
        );
    }

    @Override
    public Integer create(Produto produto) {
        String sql = "INSERT INTO Produto" +
                "(codProduto, nome, categoria, valor, disponibilidade, codCampanha)"
                +" VALUES (?, ?, ?, ?, ?, ?)";

        try(PreparedStatement ps = ConnectionFactory.createPreparedStatement(sql)){
            setProdutoBody(produto, ps);
            ps.execute();

            ResultSet resultSet = ps.getGeneratedKeys();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<Produto> findByCodProduto(Integer cod) {
        String sql = "SELECT * FROM Produto" +
                " WHERE codProduto = ?";
        return getProduto(cod, sql);
    }

    @Override
    public Optional<Produto> findProdutoByCodCampanha(String codCampanha) {
        String sql = "SELECT * FROM Produto" +
                " WHERE codCampanha = ?";
        Produto produto = null;
        try (PreparedStatement ps = ConnectionFactory.createPreparedStatement(sql)) {
            ps.setString(1, codCampanha);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                produto = resultSetToEntity(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(produto);
    }

    @Override
    public Optional<Produto> findOne(Integer key) {
        String sql = "SELECT * FROM Produto" +
                " WHERE id = ?";
        return getProduto(key, sql);
    }

    private Optional<Produto> getProduto(Integer attribute, String sql) {
        Produto produto = null;
        try (PreparedStatement ps = ConnectionFactory.createPreparedStatement(sql)) {
            ps.setInt(1, attribute);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                produto = resultSetToEntity(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(produto);
    }

    @Override
    public List<Produto> findAll() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM Produto";

        try (PreparedStatement ps = ConnectionFactory.createPreparedStatement(sql)) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Produto produto = resultSetToEntity(resultSet);
                produtos.add(produto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }

    @Override
    public boolean update(Produto produto) {
        String sql = "UPDATE Produto SET" +
                " codProduto = ?, nome = ?, categoria = ?, valor = ?," +
                " disponibilidade = ?, codCampanha = ?" +
                " WHERE id = ?";

        try (PreparedStatement ps = ConnectionFactory.createPreparedStatement(sql)) {
            setProdutoBody(produto, ps);
            ps.setInt(7, produto.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteByKey(Integer key) {
        String sql = "DELETE FROM Produto WHERE id = ?";

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
    public boolean delete(Produto produto) {
        if (produto == null || produto.getId() == null)
            throw new IllegalArgumentException("Id de produto não pode ser nulo");
        return deleteByKey(produto.getId());
    }

    private void setProdutoBody(Produto produto, PreparedStatement ps) throws SQLException {
        ps.setInt(1, produto.getCodProduto());
        ps.setString(2, produto.getNome());
        ps.setString(3, produto.getCategoria().toString());
        ps.setDouble(4, produto.getValor());
        ps.setBoolean(5, produto.getDisponibilidade());
        ps.setString(6, produto.getCodCampanha());
    }

}
