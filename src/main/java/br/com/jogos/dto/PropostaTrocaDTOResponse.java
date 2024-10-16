package br.com.jogos.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record PropostaTrocaDTOResponse(
        UUID id,
        UUID jogoOferecido,
        UUID jogoDesejado,
        UUID usuarioDestinatario,
        UUID usuarioOfertante,
        String status,
        LocalDateTime dataCriacao,
        LocalDateTime dataResposta) {
}
