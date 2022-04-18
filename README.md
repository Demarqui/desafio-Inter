# desafio-Inter

![Interinvestment](https://imgur.com/zP5cdon.png)

# Interinvestment 
[![NPM](https://img.shields.io/npm/l/react)](https://github.com/Demarqui/desafio-Inter/blob/main/LICENSE) 

# Sobre o projeto

O projeto Interinvestment é um projeto construído a partir de uma oportunidade cedida pelo banco Inter. 

Voltado para uma vaga de Dev lll no banco, o desafio consiste em uma aplicação back-end que realizará o(s) melhor(es) investimento(s) para o cliente, levando em consideração o valor passado pelo mesmo à investir, juntamente com a quantidade de empresas distintas desejada, visando sempre a diversificação.

A API fará uma lista de possíveis investimentos para o cliente, e escolherá aquele que retornar o menor troco para o solicitante.

## Modelo conceitual
![Modelo Conceitual](https://imgur.com/sFFOABw.png)

## Exemplo dos endpoints disponibilizados a partir do Swagger
![Modelo Conceitual](https://imgur.com/xA0PNPt.png). Link disponível para os testes http://localhost:8080/swagger-ui/index.html. Ops: Só funcionará quando a aplicação estiver em execução

# Tecnologias utilizadas
## Back end
- Java
- Spring Boot
- JPA / Hibernate
- Maven
- Banco de dados H2

# Como executar o projeto

## Back end
Pré-requisitos: Java 8 +

```bash
# clonar repositório
git clone https://github.com/Demarqui/desafio-Inter.git

# salvar o projeto em sua workspace e abri-lo com sua IDE

# executar o projeto 
com o botão direito na classe DesafioInterApplication do pacote br.com.desafiointer, clique na opção Run Ass > Spring Boot App

# executar as operações do projeto
é possível efetuar as requisições dos endpoints do projeto através do swagger, que, via Spring framework, documentou todos os endpoints. Estes estão disponíveis no link (http://localhost:8080/swagger-ui/index.html), que funcionará assim que o projeto for executado. Exemplo dos endpoints demonstrado acima.

# Autor

Douglas Louvison Demarqui

https://www.linkedin.com/in/douglasdemarqui/
