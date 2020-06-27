# Spring Boot API
Um exemplo de Rest API com Spring Boot, fazendo conexão ao Firebird e documentação gerada pelo Swagger. 

A aplicação foi criada apenas como forma de estudo sobre anotações do Spring e com o intuito de possuir uma fonte de consulta sobre estas informações, um pequeno passo para o entendimento de Rest API **_;^)_**

Para consultar todos os EndPoints disponiveis atualmente na aplicação, assim como formato aceito como entrada e saida, após executa-la acesse o arquivo /swagger-ui.html via navegador (_ex: localhost:8080/swagger-ui.html_).

a aplicação está configurada para gerar as entidades (tabelas) ao banco de dados automaticamente, refletindo as alterações feitas no código. Tambem é possivel se conectar a outros bancos de dados, alterando as configurações do application.properties

O repositório ja possui um banco de dados Firebird (DB/BANCO_DADOS.FDB), porem para utiliza-lo o caminho do mesmo precisa ser alterado no arquivo application.properties

## Implementações e Ideias
- [x] Conectar a API ao Firebird
- [x] Atualizar automaticamente as tabelas no banco de dados
- [x] Gerar a documentação da API através do Swagger
- [ ] Conectar a API ao PostgreSQL e estudar se existem impactos nas anotações, sequences, etc.
- [ ] Criar mais entidades e estudar sobre o relacionamento entre elas
- [ ] Implementar o Lombok para permitir um código mais enxuto (https://projectlombok.org/)

## Documentações
Spring Boot (https://spring.io/projects/spring-boot)
Firebird (https://firebirdsql.org/)
Swagger (https://swagger.io/)

