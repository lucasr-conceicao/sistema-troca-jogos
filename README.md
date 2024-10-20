## Resumo do projeto
- Nosso sistema visa criar um ambiente dinâmico e acolhedor para que
entusiastas de videogames possam interagir e trocar experiências, promovendo
não apenas uma simples transação de jogos, mas sim uma verdadeira conexão
entre gamers. Queremos fomentar uma comunidade vibrante e colaborativa, na
qual gamers possam compartilhar não apenas seus jogos, mas também suas
paixões, opiniões e conhecimentos sobre o mundo dos videogames. Ao unir
jogadores de diferentes origens e preferências, buscamos não apenas facilitar a
troca de jogos, mas também enriquecer a experiência de cada membro da nossa
comunidade, criando laços e momentos inesquecíveis no universo dos games.

## 🔨 Funcionalidades do projeto

- `Funcionalidade 1` <br><br>
`API de autenticação`: A API tem como objetivo permitir que uma pessoa cadastre e logue no sistema.
</br>


```
curl --request POST \
  --url http://localhost:8080/api-autenticar/v1/cadastrar \
  --header 'Content-Type: application/json' \
  --header 'User-Agent: insomnia/10.0.0' \
  --cookie JSESSIONID=6CF1364A2B25F7AD0A4B2A7C85710EC1 \
  --data '{
	"username":"lucas",
	"nome":"42343",
	"email":"lucas@hotmail.com",
	"senha":"Ku8&*",
	"preferenciaDeTroca": "Correio"
}'
```

Banco de dados sem nenhum cadastro do usuário

![image](https://github.com/user-attachments/assets/bf930016-1494-4d31-89ab-9cecdbfecbbd)

Após rodar a API pelo Insomnia o usuário será cadastrado no banco de dados.

![image](https://github.com/user-attachments/assets/2e6563af-2209-499b-8f94-63fe995b006e)

![image](https://github.com/user-attachments/assets/5ae5792b-20fe-4b1d-b199-a6e4ebb6a4cb)

```
curl --request POST \
  --url http://localhost:8080/api-autenticar/v1/login \
  --header 'Content-Type: application/json' \
  --header 'User-Agent: insomnia/10.0.0' \
  --cookie JSESSIONID=6CF1364A2B25F7AD0A4B2A7C85710EC1 \
  --data '{
	"username":"luquinha98",
	"senha":"Ku8&*"
}'
```

#### 
- `Funcionalidade 2` <br><br> 
`API de gestão de usuario`: A API tem como objetivo permitir o gerenciamento de informações sobre os usuarios cadastrados em nosso sistema. 
</br>

```
curl --request GET \
  --url http://localhost:8080/api-usuarios/v1/buscar/134218cf-a119-43ca-91f2-680bc590584c \
  --header 'User-Agent: insomnia/10.0.0' \
  --cookie JSESSIONID=6CF1364A2B25F7AD0A4B2A7C85710EC1
```

Realizando a busca do usuario cadastrado no banco de dados

![image](https://github.com/user-attachments/assets/7f785075-ba8f-4f99-b88d-3469ccfabbc8)

```
curl --request PUT \
  --url http://localhost:8080/api-usuarios/v1/atualizar/134218cf-a119-43ca-91f2-680bc590584c \
  --header 'Content-Type: application/json' \
  --header 'User-Agent: insomnia/10.0.0' \
  --cookie JSESSIONID=6CF1364A2B25F7AD0A4B2A7C85710EC1 \
  --data '{
	"username":"lucas",
	"nome":"Lucas Rocha",
	"email":"lucas@hotmail.com",
	"senha":"Ku8&*",
	"preferenciaDeTroca": "Correio"
}'
```

Atualizando o campo do nome desse usuario cadastrado no banco de dados.

![image](https://github.com/user-attachments/assets/ea044d23-998d-414a-bccb-98ba7f940ca5)

![image](https://github.com/user-attachments/assets/fffea248-6aa7-4692-bed6-90c838f294db)


```
curl --request DELETE \
  --url http://localhost:8080/api-usuarios/v1/deletar/134218cf-a119-43ca-91f2-680bc590584c \
  --header 'User-Agent: insomnia/10.0.0' \
  --cookie JSESSIONID=6CF1364A2B25F7AD0A4B2A7C85710EC1
```

Deletando usuario da base

![image](https://github.com/user-attachments/assets/8c4a5925-e486-4ba5-8d94-83bfcb48d0b3)

![image](https://github.com/user-attachments/assets/bc6bc538-80ca-4dc1-b6c1-1e21bc9fa6e5)


#### 
- `Funcionalidade 3` <br><br>
`API de gestão de jogos`: A API tem como objetivo permitir o gerenciamento de informações sobre os jogos cadastrados em nosso sistema. 
</br>

```
curl --request POST \
  --url http://localhost:8080/api-jogos/v1/cadastrar \
  --header 'Content-Type: application/json' \
  --header 'Cookie: JSESSIONID=6CF1364A2B25F7AD0A4B2A7C85710EC1' \
  --header 'User-Agent: insomnia/10.0.0' \
  --cookie JSESSIONID=6CF1364A2B25F7AD0A4B2A7C85710EC1 \
  --data '{
	"titulo":"F1 2024",
	"descricao":"Formula 1",
	"usuario":"56ec3fa4-74a8-40b3-9974-bd2770edc987"
}'
```


Cadastrando Jogo para um usuario

![image](https://github.com/user-attachments/assets/49f10d32-efa6-482c-b83b-0647a59576da)

![image](https://github.com/user-attachments/assets/f48457e4-2918-44e6-9184-a617fa5d0fc4)

```
curl --request PUT \
  --url http://localhost:8080/api-jogos/v1/atualizar/5c9ae35d-d1c4-4a90-a9fb-1c1f4c0284cf \
  --header 'Content-Type: application/json' \
  --header 'Cookie: JSESSIONID=6CF1364A2B25F7AD0A4B2A7C85710EC1; JSESSIONID=6CF1364A2B25F7AD0A4B2A7C85710EC1' \
  --header 'User-Agent: insomnia/10.0.0' \
  --cookie JSESSIONID=6CF1364A2B25F7AD0A4B2A7C85710EC1 \
  --data '{
	"titulo": "F1 2024",
	"descricao": "Formula 1 2024 edicao profissional",
	"usuario": "56ec3fa4-74a8-40b3-9974-bd2770edc980"
}'
```
Atualizando informacoes do jogo cadastrado

![image](https://github.com/user-attachments/assets/cee9ded7-06cf-4cca-9380-83c06c0acd89)

![image](https://github.com/user-attachments/assets/89e3da34-559c-465c-a8c2-396fa5b81e1e)


```
curl --request GET \
  --url http://localhost:8080/api-jogos/v1/listar/jogos \
  --header 'Content-Type: application/json' \
  --header 'Cookie: JSESSIONID=6CF1364A2B25F7AD0A4B2A7C85710EC1' \
  --header 'User-Agent: insomnia/10.0.0' \
  --cookie JSESSIONID=6CF1364A2B25F7AD0A4B2A7C85710EC1 \
  --data '{
	"titulo":"F1 2024",
	"descricao":"Formula 1",
	"usuario":"56ec3fa4-74a8-40b3-9974-bd2770edc980"
}'
```

Listar jogos cadastrados

![image](https://github.com/user-attachments/assets/be917bbc-074d-43e9-b642-37f24c14067e)


```
curl --request GET \
  --url http://localhost:8080/api-jogos/v1/buscar/db1b4a41-320b-40ab-a1a4-a21321114cc3 \
  --header 'User-Agent: insomnia/10.0.0' \
  --cookie JSESSIONID=6CF1364A2B25F7AD0A4B2A7C85710EC1
```

Buscar por um jogo especifico

![image](https://github.com/user-attachments/assets/4ad130b3-5831-4d1f-b6b8-a95f783a5d41)

```
curl --request DELETE \
  --url http://localhost:8080/api-jogos/v1/deletar/db1b4a41-320b-40ab-a1a4-a21321114cc3 \
  --header 'User-Agent: insomnia/10.0.0' \
  --cookie JSESSIONID=6CF1364A2B25F7AD0A4B2A7C85710EC1
```

Deletar jogo

![image](https://github.com/user-attachments/assets/992ef39e-fc68-4b35-ad83-b72bd050e73f)


#### 
- `Funcionalidade 4` <br><br>
`API de gestão de troca`: A API tem como objetivo permitir o gerenciamento de propostas de troca de jogos feitas pelos usuarios da plataforma.
</br>

```
curl --request POST \
  --url http://localhost:8080/api-propostas-troca/v1/cadastrar \
  --header 'Content-Type: application/json' \
  --header 'User-Agent: insomnia/10.0.0' \
  --cookie JSESSIONID=6CF1364A2B25F7AD0A4B2A7C85710EC1 \
  --data '{
  "jogoOferecido": "5c9ae35d-d1c4-4a90-a9fb-1c1f4c0284cf",
  "jogoDesejado": "db1b4a41-320b-40ab-a1a4-a21321114cc3",
  "usuarioDestinatario": "8a065017-bd3b-4dbf-acb9-9ac6ea323aa6",
  "usuarioOfertante": "56ec3fa4-74a8-40b3-9974-bd2770edc987"
}'
```

Cadastrando uma troca entre usuarios

![image](https://github.com/user-attachments/assets/9fae616e-5c2c-436c-a8d6-fc8054f02884)

![image](https://github.com/user-attachments/assets/aa4f9ba9-4e61-45b4-9be3-476e75bfeeb4)


```
curl --request GET \
  --url http://localhost:8080/api-propostas-troca/v1/listar/usuario_ofertante/56ec3fa4-74a8-40b3-9974-bd2770edc987 \
  --header 'User-Agent: insomnia/10.0.0' \
  --cookie JSESSIONID=6CF1364A2B25F7AD0A4B2A7C85710EC1
```

Listando propostas pendentes

![image](https://github.com/user-attachments/assets/7e6defcb-9598-480e-8f3e-f0a0069d11e0)


```
curl --request PUT \
  --url http://localhost:8080/api-propostas-troca/v1/responder/dbfdf865-be6a-4ef8-9bbc-32b2765dd594/aceitar \
  --header 'User-Agent: insomnia/10.0.0' \
  --cookie JSESSIONID=6CF1364A2B25F7AD0A4B2A7C85710EC1
```

Aceitando proposta de troca

![image](https://github.com/user-attachments/assets/4d9ccb5f-41d7-4ced-9536-af3669f44d72)

![image](https://github.com/user-attachments/assets/60121158-c96b-4b09-89c3-88fcbe752dc8)


```
curl --request PUT \
  --url http://localhost:8080/api-propostas-troca/v1/responder/dbfdf865-be6a-4ef8-9bbc-32b2765dd594/rejeitar \
  --header 'User-Agent: insomnia/10.0.0' \
  --cookie JSESSIONID=6CF1364A2B25F7AD0A4B2A7C85710EC1
```

Rejeitando proposta de troca

![image](https://github.com/user-attachments/assets/bf69f325-b004-41a1-9137-fa1e2c1bff7e)

![image](https://github.com/user-attachments/assets/d3aa5b87-44e5-49fe-9cd5-c649c4973640)


```
curl --request GET \
  --url http://localhost:8080/api-propostas-troca/v1/buscar/proposta_troca/13b23f87-17de-45c9-a4ef-f89e7e743c0c \
  --header 'User-Agent: insomnia/10.0.0' \
  --cookie JSESSIONID=6CF1364A2B25F7AD0A4B2A7C85710EC1
```

Buscar uma proposta de troca especifica

![image](https://github.com/user-attachments/assets/b9453604-1d33-4d98-a330-1fb47b8b93a9)


```
curl --request DELETE \
  --url http://localhost:8080/api-propostas-troca/v1/deletar/13b23f87-17de-45c9-a4ef-f89e7e743c0c \
  --header 'User-Agent: insomnia/10.0.0' \
  --cookie JSESSIONID=6CF1364A2B25F7AD0A4B2A7C85710EC1
```

Deletando uma proposta de troca

![image](https://github.com/user-attachments/assets/a23e83c9-53cd-489e-bc45-1a9510c0aeaf)


#### 
- `Funcionalidade 5` <br><br>
`API de gestão de mensagens`: API responsável pelo envio de mensagens
</br>

```
curl --request POST \
  --url http://localhost:8080/api/mensagem/enviar \
  --header 'Content-Type: application/json' \
  --header 'User-Agent: insomnia/10.0.0' \
  --cookie JSESSIONID=6CF1364A2B25F7AD0A4B2A7C85710EC1 \
  --data '{
	"trocaId": "13b23f87-17de-45c9-a4ef-f89e7e743c0c",
	"usuarioId": "56ec3fa4-74a8-40b3-9974-bd2770edc987",
	"conteudo":"oi, boa tarde!"
}'
```

```
curl --request GET \
  --url http://localhost:8080/api/mensagem/13b23f87-17de-45c9-a4ef-f89e7e743c0c \
  --header 'User-Agent: insomnia/10.0.0' \
  --cookie JSESSIONID=6CF1364A2B25F7AD0A4B2A7C85710EC1
```

## 🛠️ Arquitetura utilizada
![image](https://github.com/lucasr-conceicao/monitoramento-consumo-energia/assets/64719344/962b3549-c2de-47b8-89da-b09065d59ef6) <br>
O código foi desenvolvido utilizando o clean arch.
