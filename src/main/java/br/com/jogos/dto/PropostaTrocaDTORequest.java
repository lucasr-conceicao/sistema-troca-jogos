package br.com.jogos.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record PropostaTrocaDTORequest(
        @NotNull(message = "Jogo oferecido não pode estar vazio") UUID jogoOferecido,
        @NotNull(message = "Jogo desejado não pode estar vazio") UUID jogoDesejado,
        @NotNull(message = "Usuário não pode estar vazio") UUID usuarioDestinatario,
        @NotNull(message = "Usuário não pode estar vazio") UUID usuarioOfertante) {
}
