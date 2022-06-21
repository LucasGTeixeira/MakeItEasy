package domain.usecases.venda;

import domain.entities.venda.StatusVenda;
import domain.entities.venda.Venda;

import java.util.List;
import java.util.Optional;

public class ListarVendasUseCase {
    private final VendaDAO vendaDAO;


    public ListarVendasUseCase(VendaDAO vendaDAO) {
        this.vendaDAO = vendaDAO;
    }

    public Optional<Venda> findOne(Integer id){
        if(id == null)
            throw new IllegalArgumentException("id não pode ser nulo");
        return vendaDAO.findOne(id);
    }

    public List<Venda> findAll(){
        return vendaDAO.findAll();
    }

    public Optional<Venda> findVendaByCpf(String cpf){
        return vendaDAO.findVendaByCpf(cpf);
    }

    public Optional<Venda> findVendaByCodProduto(Integer codProduto){
        return vendaDAO.findVendaByCodProduto(codProduto);
    }

    public List<Venda> findVendaByStatus(StatusVenda statusVenda){
        if(statusVenda == null)
            throw new IllegalArgumentException("status não pode ser nulo");
        return vendaDAO.findVendaByStatus(statusVenda);
    }
}
