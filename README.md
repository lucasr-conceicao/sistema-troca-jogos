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
```
dasd
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
```
curl --request DELETE \
  --url http://localhost:8080/api-usuarios/v1/deletar/134218cf-a119-43ca-91f2-680bc590584c \
  --header 'User-Agent: insomnia/10.0.0' \
  --cookie JSESSIONID=6CF1364A2B25F7AD0A4B2A7C85710EC1
```


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
```
curl --request GET \
  --url http://localhost:8080/api-jogos/v1/buscar/db1b4a41-320b-40ab-a1a4-a21321114cc3 \
  --header 'User-Agent: insomnia/10.0.0' \
  --cookie JSESSIONID=6CF1364A2B25F7AD0A4B2A7C85710EC1
```
```
curl --request DELETE \
  --url http://localhost:8080/api-jogos/v1/deletar/db1b4a41-320b-40ab-a1a4-a21321114cc3 \
  --header 'User-Agent: insomnia/10.0.0' \
  --cookie JSESSIONID=6CF1364A2B25F7AD0A4B2A7C85710EC1
```

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
```
curl --request GET \
  --url http://localhost:8080/api-propostas-troca/v1/listar/usuario_ofertante/56ec3fa4-74a8-40b3-9974-bd2770edc987 \
  --header 'User-Agent: insomnia/10.0.0' \
  --cookie JSESSIONID=6CF1364A2B25F7AD0A4B2A7C85710EC1
```
```
curl --request PUT \
  --url http://localhost:8080/api-propostas-troca/v1/responder/dbfdf865-be6a-4ef8-9bbc-32b2765dd594/aceitar \
  --header 'User-Agent: insomnia/10.0.0' \
  --cookie JSESSIONID=6CF1364A2B25F7AD0A4B2A7C85710EC1
```
```
curl --request PUT \
  --url http://localhost:8080/api-propostas-troca/v1/responder/dbfdf865-be6a-4ef8-9bbc-32b2765dd594/rejeitar \
  --header 'User-Agent: insomnia/10.0.0' \
  --cookie JSESSIONID=6CF1364A2B25F7AD0A4B2A7C85710EC1
```
```
curl --request GET \
  --url http://localhost:8080/api-propostas-troca/v1/buscar/proposta_troca/13b23f87-17de-45c9-a4ef-f89e7e743c0c \
  --header 'User-Agent: insomnia/10.0.0' \
  --cookie JSESSIONID=6CF1364A2B25F7AD0A4B2A7C85710EC1
```
```
curl --request DELETE \
  --url http://localhost:8080/api-propostas-troca/v1/deletar/13b23f87-17de-45c9-a4ef-f89e7e743c0c \
  --header 'User-Agent: insomnia/10.0.0' \
  --cookie JSESSIONID=6CF1364A2B25F7AD0A4B2A7C85710EC1
```

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