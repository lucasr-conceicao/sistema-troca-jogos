package br.com.jogos.controller;

import br.com.jogos.dto.AutenticarDTORequest;
import br.com.jogos.dto.UsuarioDTORequest;
import br.com.jogos.dto.UsuarioDTOResponse;
import br.com.jogos.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class AutenticarUsuarioControllerTest {

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private AutenticarUsuarioController autenticarUsuarioController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCadastrarUsuarioComSucesso() {
        var usuarioDTORequest = new UsuarioDTORequest("RM349225", "Lucas","lucas-fiap@hotmail.com", "username", "Correio");
        var usuarioDTOResponse = new UsuarioDTOResponse(UUID.randomUUID(), "RM349225", "Lucas","lucas-fiap@hotmail.com", "username", "Correio");

        when(usuarioService.cadastrarUsuario(usuarioDTORequest)).thenReturn(usuarioDTOResponse);

        ResponseEntity<UsuarioDTOResponse> response = autenticarUsuarioController.cadastrarMorador(usuarioDTORequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(usuarioDTOResponse, response.getBody());
    }

    @Test
    void deveLogarUsuarioComSucesso() {
        AutenticarDTORequest autenticarDTORequest = new AutenticarDTORequest("username", "senha");
        String expectedResponse = "Usuário autenticado com sucesso.";

        when(usuarioService.logarUsuario(autenticarDTORequest)).thenReturn(expectedResponse);

        ResponseEntity<String> response = autenticarUsuarioController.login(autenticarDTORequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());
    }
}
