# punch-service
O projeto foi construído utilizando-se o framework Spring Boot e os testes unitários utilizam o JUnit. 

Algumas considerações:
- As batidas de ponto devem estar no formato yyyy-MM-dd HH:mm
- Todas as requisições precisam ser autenticadas
  login: user
  senha: user
- As configurações de banco de dados precisam ser adicionadas no arquivo application.properties localizado no diretório src/main/resources. Durante o desenvolvimento foi utilizado o sgbd postgres

O Eclipse foi a IDE escolhida, para importar o projeto basta ir em file->import e fazer o import usando a opção maven project Ao executar o projeto, um Swagger com as descrições dos endpoints pode ser acessado através da URL http://localhost:8080/swagger-ui.html
