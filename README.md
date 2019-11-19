# punch-service
O projeto foi construído utilizando-se o framework Spring Boot e os testes unitários utilizam o JUnit. 

Algumas consideraçoes:
- As batidas de ponto devem estar no formato yyyy-MM-dd HH:mm
- Todas as requisiçoes precisam ser autenticadas
  login: user
  senha: user
- As configurações de baco de dados precisam ser adicionadas no arquivo application.properties localizado no diretório src/main/resources. Durante o desenvolvimento foi utilizado o sgbd postgres


O Eclipse foi a IDE escolhida, para imporar o projeto basta ir em file->import e fazer o import usando a opção maven project
Ao executar o projeto, um Swagger com a descriçao dos endpoints pode ser aessado atraves da URL http://localhost:8080/swagger-ui.html

