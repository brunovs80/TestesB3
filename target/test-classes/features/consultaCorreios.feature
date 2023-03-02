# language: pt

Funcionalidade: Realizar consulta de cep e codigo rastreamento



  Esquema do Cenário: Buscar  cep e codigo rastreamento
    Dado que acesso o site correios"www.correios.com.br"
    E realizar a consulta do cep invalido "80700000"
    E devera retornar: "Dados não encontrado"
    E retornar a pagina inicial
    "
    Quando realizar a consulta do cep "<cep>"
    Então devera retornar rua "<rua>"
    Então devera retornar cidade "<cidade>"
    E retornar a pagina inicial

    Exemplos:
      | site                         | cep      | rua                                 |cidade      |
      | https://www.correios.com.br/ | 01013001 | Rua Quinze de Novembro - lado ímpar |São Paulo/SP|


  Esquema do Cenário: Buscar  cep invalido
    Dado que acesso o site correios"<site>"
    Quando realizar a consulta do rastreamento "<rastreamento>"
    E preencher o captcha
    E clicar no botao pesquisar
    Então devera retornar: "<msg>"
    E retornar a pagina inicial

    Exemplos:
      | site                         | rastreamento | msg                                                  |
      | https://www.correios.com.br/ | SS987654321BR| Objeto não encontrado na base de dados dos Correios. |

    Dado acesso site correios "www.correios.com.br"
    E pesquis CEP incorreto "80700000"
    E valido a mensagem "Dados não encontrado"
    E volto para tela inicial
    E pesquis CEP existente "01013001"
    E valido o nome da rua "Rua Quinze de Novembro - lado ímpar"
    E valido a cidade
    Quando Voltar para tela inicial
    Entao procure pelo código de rastreamento
