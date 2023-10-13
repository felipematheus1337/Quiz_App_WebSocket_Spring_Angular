
# Quiz App Temático
Este é um aplicativo de quiz temático que permite aos usuários testarem seus conhecimentos em quatro categorias emocionantes: Conhecimentos Gerais, DBZ (Dragon Ball Z), Cinema e MMA. O aplicativo é construído com tecnologias modernas, usando o Spring Framework com Java 17 no lado do servidor e o Angular como cliente, com suporte a WebSocket para uma experiência de jogo em tempo real. Os resultados dos usuários são armazenados no banco de dados H2.

## Recursos
Quatro temas de quiz emocionantes: Conhecimentos Gerais, DBZ, Cinema e MMA.
Autenticação de usuário para rastrear o progresso individual.
Suporte a WebSocket para jogar em tempo real com amigos ou outros jogadores.
Armazenamento de resultados de usuários no banco de dados H2 para acompanhar seu desempenho.
Interface de usuário intuitiva e responsiva usando Angular.
Placar de líderes para competir com outros jogadores.
Tecnologias Utilizadas
Spring Framework: Utilizamos o Spring com Java 17 para criar a lógica de negócios, autenticação de usuário e gerenciamento de partidas.

### WebSocket: Implementamos o suporte a WebSocket para permitir partidas em tempo real e chat entre jogadores.

### Angular: O cliente é construído com Angular, aproveitando a estrutura do lado do cliente para criar uma experiência de usuário responsiva e interativa.

### Banco de Dados H2: Utilizamos o banco de dados H2 para armazenar informações de usuários e resultados de quiz de forma eficiente.

## Instruções de Instalação
Para configurar o aplicativo em seu ambiente de desenvolvimento, siga as etapas abaixo:

## Configuração do Servidor (Spring)
Certifique-se de ter o Java 17 instalado em sua máquina.

Clone este repositório.

Navegue até a pasta do servidor: cd server.

Execute o servidor Spring: ./mvnw spring-boot:run.

O servidor estará em execução em http://localhost:8080.

Configuração do Cliente (Angular)
Certifique-se de ter o Node.js e o Angular CLI instalados em sua máquina.

Navegue até a pasta do cliente: cd client.

Instale as dependências do cliente: npm install.

Inicie o cliente Angular: ng serve.

O cliente estará em execução em http://localhost:4200.

## Screenshots

### Menu
<img src="https://github.com/felipematheus1337/Quiz_App_WebSocket_Spring_Angular/blob/dev/imgs/menu.png" />

### Selecionar Quiz
<img src="https://github.com/felipematheus1337/Quiz_App_WebSocket_Spring_Angular/blob/dev/imgs/select.png" />

### Resultados
<img src="https://github.com/felipematheus1337/Quiz_App_WebSocket_Spring_Angular/blob/dev/imgs/resultado.png" />



## Uso
Após a instalação, você pode acessar o aplicativo em seu navegador. Você pode criar uma conta, escolher um tema de quiz, convidar amigos para jogar em tempo real e competir para ver quem tem o melhor conhecimento.

## Contribuições
Aceitamos contribuições para tornar este aplicativo ainda melhor. Se você deseja contribuir, crie um fork deste repositório, faça suas melhorias e envie uma solicitação pull.

## Licença
Este projeto é licenciado sob a Licença MIT. Consulte o arquivo LICENSE para obter detalhes.

Aproveite seu tempo testando seus conhecimentos nos temas de Conhecimentos Gerais, DBZ, Cinema e MMA com nosso Quiz App Temático!

