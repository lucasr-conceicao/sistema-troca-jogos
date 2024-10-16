package br.com.jogos.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public record UsuarioDTORequest(
        @NotEmpty(message = "username nao pode estar vazio") String username,
        @NotEmpty(message = "nome nao pode estar vazio") String nome,
        @Email(message = "E-mail deve ser válido") String email,
        @NotEmpty(message = "Preferencia para realizar a troca nao pode ser vazio") String preferenciaDeTroca,
        @NotEmpty @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).+$", message = "A senha deve conter pelo menos: 1 letra maiuscula, 1 letra minuscula, 1 numero e 1 caractere especial.") String senha) {
}
