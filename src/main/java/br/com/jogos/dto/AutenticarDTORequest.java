package br.com.jogos.dto;

import jakarta.validation.constraints.NotEmpty;

public record AutenticarDTORequest(@NotEmpty String username, @NotEmpty String senha) {
}
