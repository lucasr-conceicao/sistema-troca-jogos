package br.com.jogos.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record UsuarioDTOResponse (UUID id, String username, String nome, String email, String senha, String preferenciaDeTroca) {

}
