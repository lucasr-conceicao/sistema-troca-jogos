package br.com.jogos.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record JogosDTOResponse(UUID id, String titulo, String descricao, UUID usuarioId, LocalDateTime dataCadastro) {
}
