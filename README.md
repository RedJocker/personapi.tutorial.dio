<h2>Digital Innovation: Desenvolvendo um sistema de gerenciamento de pessoas em API REST com Spring Boot</h2>

Nesta live coding desenvolvemos um pequeno sistema para o gerenciamento de pessoas de uma empresa através de uma API REST, criada com o Spring Boot.

Durante a sessão, foram desenvolvidos e abordados os seguintes tópicos:

* Setup inicial de projeto com o Spring Boot Initialzr 
* Criação de modelo de dados para o mapeamento de entidades em bancos de dados
* Desenvolvimento de operações de gerenciamento de usuários (Cadastro, leitura, atualização e remoção de pessoas de um sistema).
* Relação de cada uma das operações acima com o padrão arquitetural REST, e a explicação de cada um dos conceitos REST envolvidos durante o desenvolvimento do projeto.
* Desenvolvimento de testes unitários para validação das funcionalidades
* Implantação do sistema na nuvem através do Heroku

Link da implementacão no Heroku:  
  https://personapi-tutorial-dio.herokuapp.com/api/v1/people
  
Endpoints:  

* GET /test -> retorna "API test"
* GET / -> retorna lista de pessoas
* GET /{id} -> retorna pessoa com {id} sendo id um numero valido de identificação da pessoa
* POST / -> cria pessoa com o conteudo do body, formato esperado similar ao exemplo abaixo (parenteses adicionados para comentarios) 
```
{
    "firstName": "joao", (campo obrigatorio)
    "lastName": "albuquerque",  (campo obrigatorio)
    "cpf": "961.369.511-77", (campo obrigatorio)
    "birthDate": 10-10-2010,
    "phones": [ 
        {
            "type": "MOBILE",
            "number": "(11)98812-1212"
        },
        {
            "type": "HOME",
            "number": "(11)3312-1212"
        },
        {
            "type": "COMMERCIAL",
            "number": "(11)3312-1212"
        }
    ] (pelo menos um telefone obrigatorio, types possiveis(MOBILE, HOME, COMMERCIAL))
}
```
* DELETE /{id} -> deleta a pessoa com id informado
* PUT /{id} -> update completo da pessoa com id informado com o conteúdo passado no body, preferencialmente adicionar os ids dos telefones no json caso já exista registro 
