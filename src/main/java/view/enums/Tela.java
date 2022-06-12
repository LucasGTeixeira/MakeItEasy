package view.enums;

public enum Tela {
    INITIAL("/views/MenuInicialAfvUI"),

    MENU_CLIENTE("/views/gerenciamento/cliente/MenuCliente"),
    MENU_CAMPANHA("/views/gerenciamento/campanha/MenuCampanha"),
    MENU_EMPRESA("/views/gerenciamento/empresa/MenuEmpresa"),
    MENU_PRODUTO("/views/gerenciamento/produto/MenuProduto"),

    UPDATE_OR_INSERIR_CLIENTE("/views/gerenciamento/cliente/UpdateOrInserirClient"),
    UPDATE_OR_INSERIR_CAMPANHA("/views/gerenciamento/campanha/UpdateOrInserirCampanha"),
    UPDATE_OR_INSERIR_EMPRESA("/views/gerenciamento/empresa/UpdateOrInserirEmpresa"),
    UPDATE_OR_INSERIR_PRODUTO("/views/gerenciamento/produto/UpdateOrInserirProduto"),

    REMOVER_CLIENTE("/views/gerenciamento/cliente/RemoverClient"),
    REMOVER_CAMPANHA("/views/gerenciamento/campanha/RemoverCampanha"),
    REMOVER_EMPRESA("/views/gerenciamento/empresa/RemoverEmpresa"),
    REMOVER_PRODUTO("/views/gerenciamento/produto/RemoverProduto"),

    MENU_REALIZAR_VENDA("/views/venda/RealizarVendaUI"),

    MENU_RELATORIO_CLIENTE("/views/relatorios/RelatorioCliente"),
    MENU_RELATORIO_CAMPANHA("/views/relatorios/RelatorioCampanha"),
    MENU_RELATORIO_EMPRESA("/views/relatorios/RelatorioEmpresa"),
    MENU_RELATORIO_PRODUTO("/views/relatorios/RelatorioProduto"),
    ;

    private final String nomeTela;

    Tela(String nomeTela) {
        this.nomeTela = nomeTela;
    }

    public String getNomeTela() {
        return nomeTela;
    }

    @Override
    public String toString() {
        return getNomeTela();
    }
}
