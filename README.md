B2W - Desafio - Star Wars API
==================================================

API desenvolvida para atender ao desafio da B2W, uma POC para a oportunidade de backend developer, que tem como desafio permitir a consulta de dados para um jogo Star Wars.

### Arquitetura


Esta aplicação foi desenvolvida usando:

* Java 8
* Maven 4.0.0
* Jersey 2.27
* MySql 5.1.6
* Apache Commons BeanUtils 1.9.3
* JUnit 4.12
* Hibernate 5.2.17.Final

### URL Base

A URL base é a URL principal utilizada para realizar qualquer tipo de requisição para a API, em caso de erro em uma chamada favor verifique primeiro se a URL Base esta correta.

Endereço Base:
http://localhost:8080/starwarsapi/api/v1

# Recursos

* Planetas
Obtem informações sobre os planetas, como listagem, consulta por id e nome, inclusão e remoção.

### Endereço do Recurso

  - /planets/ -- Lista todos os planetas.
  - /planets/id -- Retorna um planeta específico.
  - /planets/query?name=:nome -- Pesquisa um planeta pelo nome.
  
#### Exemplo de Requisição:
``` http://localhost:8080/starwarsapi/api/v1/planets/1```

#### Exemplo de Resposta:
```sh
{
   "name": "Planeta1",
   "weather": "Ensolarado",
   "ground": "Plano",
   "countFilms":5,
   "URL": "http://localhost:8080/starwarsapi/api/v1/planets/1"
}
```
#### Atributos
| Nome | Tipo | Descrição |
| ------ | ------ | ------- |
| name | Texto | Nome do planeta |
| ground | Texto | Tipo do solo |
| weather | Texto | Clima |
| countFilms | Número | Total de aparições em filmes |
| URI | Texto | URI do recurso com Identificador |

#### Campo de Pesquisa:
* name

### Exemplo de Inclusão:
Para inclusão é utilizado o verbo HTTP POST, passando no corpo um JSON conforme exemplo abaixo
```http://localhost:8080/starwarsapi/api/v1/planets```

#### Corpo Requisição
```sh
{
	"name": "",
	"weather": "",
	"ground": ""
}
```
### Exemplo de Exclusão:
Para exclusão é utilizado o verbo HTTP DELETE, informando o ID desejado.
```http://localhost:8080/starwarsapi/api/v1/planets/{id}```
