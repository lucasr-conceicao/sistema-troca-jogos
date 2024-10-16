package br.com.jogos.dto;

import java.util.UUID;

public record JogosDTORequest(String titulo, String descricao, UUID usuario) {

}
