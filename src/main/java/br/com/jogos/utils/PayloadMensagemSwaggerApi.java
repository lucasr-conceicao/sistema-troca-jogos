package br.com.jogos.utils;

public interface PayloadMensagemSwaggerApi {

    String usuarioNaoEncontrado = "\"{\\\"data\\\": \\\"2024-10-13T19:55:00.383Z\\\", \\\"code\\\": 404, \\\"httpStatus\\\": \\\"NOT_FOUND\\\", \\\"path\\\": \\\"/api-jogos/v1/cadastrar\\\", \\\"mensagem\\\": \\\"Usuário não encontrado\\\"}\"";
    String jogoNaoEncontrado = "\"{\\\"data\\\": \\\"2024-10-13T19:55:00.383Z\\\", \\\"code\\\": 404, \\\"httpStatus\\\": \\\"NOT_FOUND\\\", \\\"path\\\": \\\"/api-jogos/v1/jogos\\\", \\\"mensagem\\\": \\\"jogo não encontrado\\\"}\"";
    String cadastroTrocaSucesso = "{\"id\":\"096fb98a-1a75-4df4-844a-a1cce5494d6d\",\"jogoOferecido\":\"5c9ae35d-d1c4-4a90-a9fb-1c1f4c0284cf\",\"jogoDesejado\":\"db1b4a41-320b-40ab-a1a4-a21321114cc3\",\"usuarioDestinatario\":\"8a065017-bd3b-4dbf-acb9-9ac6ea323aa6\",\"usuarioOfertante\":\"56ec3fa4-74a8-40b3-9974-bd2770edc987\",\"status\":\"PENDENTE\",\"dataCriacao\":\"2024-10-14T00:27:42.180675\"}";
    String trocaAceita = "{\"id\":\"dbfdf865-be6a-4ef8-9bbc-32b2765dd594\",\"jogoOferecido\":\"5c9ae35d-d1c4-4a90-a9fb-1c1f4c0284cf\",\"jogoDesejado\":\"db1b4a41-320b-40ab-a1a4-a21321114cc3\",\"usuarioDestinatario\":\"8a065017-bd3b-4dbf-acb9-9ac6ea323aa6\",\"usuarioOfertante\":\"56ec3fa4-74a8-40b3-9974-bd2770edc987\",\"status\":\"ACEITA\",\"dataCriacao\":\"2024-10-14T01:13:03.327166\",\"dataResposta\":\"2024-10-14T01:13:59.932445\"}";
    String trocaRejeitada = "{\"id\":\"dbfdf865-be6a-4ef8-9bbc-32b2765dd594\",\"jogoOferecido\":\"5c9ae35d-d1c4-4a90-a9fb-1c1f4c0284cf\",\"jogoDesejado\":\"db1b4a41-320b-40ab-a1a4-a21321114cc3\",\"usuarioDestinatario\":\"8a065017-bd3b-4dbf-acb9-9ac6ea323aa6\",\"usuarioOfertante\":\"56ec3fa4-74a8-40b3-9974-bd2770edc987\",\"status\":\"REJEITADA\",\"dataCriacao\":\"2024-10-14T01:13:03.327166\",\"dataResposta\":\"2024-10-14T01:13:59.932445\"}";
    String trocarNaoEncontrada = "{\"data\":\"2024-10-14T01:36:04.613593\",\"code\":404,\"httpStatus\":\"NOT_FOUND\",\"path\":\"/api-propostas-troca/v1/buscar/proposta_troca/dbfdf865-be6a-4ef8-9bbc-32b2765dd594\",\"mensagem\":\"Proposta de troca não encontrado\"}";
    String mensagemEnviado = "{\"id\":\"4b7ae7e1-d426-4a41-b0b6-78d4f2788c1c\",\"trocaId\":\"13b23f87-17de-45c9-a4ef-f89e7e743c0c\",\"usuarioId\":\"56ec3fa4-74a8-40b3-9974-bd2770edc987\",\"conteudo\":\"oi, boa tarde!\",\"dataEnvio\":\"2024-10-15T03:22:13.253478\"}";
}
