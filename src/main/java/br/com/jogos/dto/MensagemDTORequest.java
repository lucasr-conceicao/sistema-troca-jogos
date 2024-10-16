package br.com.jogos.dto;

import java.util.UUID;

public record MensagemDTORequest(UUID trocaId, UUID usuarioId, String conteudo) {
}
