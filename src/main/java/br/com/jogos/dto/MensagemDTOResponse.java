package br.com.jogos.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record MensagemDTOResponse(UUID id, UUID trocaId, UUID usuarioId, String conteudo, LocalDateTime dataEnvio) {
}

