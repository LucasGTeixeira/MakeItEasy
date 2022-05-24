# MakeItEasy
"Make it Easy" é uma aplicação feita para controlar vendas entre clientes, produtos e campanhas
através do paradigma orientado a objetos, a fim de registrar e gerar relatórios de controle de venda

## Pacotes da aplicação
- `applicaton:` Onde se localiza os subpacotes que tangem a aplicação principal, responáveis pela
parte instanciada do sistema
  - `main:` Pacote responsável por conter a aplicação executável do sistema, classe "suja" onde 
  as instancias são feitas
  - `repository:` Local onde as os mockups são feitos, implementando as interfaces definidas dentro
  dos usecases do `domain`
- `domain:` Contém o dominio da aplicação. Desde entidades até usecases que serão usados no sistema
    - `entities:` Responsável por encapsular as entidades do sistema que serão usadas durante as instancias
    - `usecases:` Dita o comportamento do sistema e a interação entre as classes e DAOs

## Usecases
- `campanha:` Contém as operações de adicionar, modificar, remover e listar das campanhas
  além de suas regras de negócio e validator da entidade Campanha
- `cliente:` Contém as operações de adicionar, modificar, remover e listar das campanhas
além de suas regras de negócio e validator da entidade Cliente
- `empresa:` Contém as operações de adicionar, modificar, remover e listar das campanhas
além de suas regras de negócio e validator da entidade Empresa
- `produto:` Contém as operações de adicionar, modificar, remover e listar das campanhas
além de suas regras de negócio e validator da entidade Produto
- `relatorio:` Define a operação de transformar as listas de entidades registradas no sistema
em um arquivo texto formatado no padrão CSV a fim de ser exportado para uma tabela em outras aplicações
- `utils:` Define um conjunto de classes e interfaces que serão usadas em contextos de controle
dentro da aplicação. Como exceptions criadas, Validators, Notifications e uma interface genérica DAO
- `venda:` Contém as operações de adicionar, modificar, remover e listar das campanhas
além de suas regras de negócio e validator da entidade Venda