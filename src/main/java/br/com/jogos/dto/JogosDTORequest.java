package br.com.jogos.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record JogosDTORequest(String titulo, String descricao, UUID usuario) {

}
