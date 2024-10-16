package br.com.jogos.handler;

import lombok.Builder;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

@Builder
public record JsonHandler(LocalDateTime data, Integer code, HttpStatus httpStatus, String path, String mensagem) {
}

