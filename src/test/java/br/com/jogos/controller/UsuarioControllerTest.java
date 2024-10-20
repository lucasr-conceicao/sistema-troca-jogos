package br.com.jogos.controller;

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

class UsuarioControllerTest {

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private UsuarioController usuarioController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveBuscarUsuarioPorIdComSucesso() {
        // Arrange
        UUID usuarioId = UUID.randomUUID();
        UsuarioDTOResponse usuarioResponse = UsuarioDTOResponse.builder()
                .id(usuarioId)
                .username("RM349225")
                .nome("Lucas")
                .email("lucas-fiap@hotmail.com")
                .senha("Lu8&*")
                .preferenciaDeTroca("Correio")
                .build();

        when(usuarioService.buscarUsuarioPorId(usuarioId)).thenReturn(usuarioResponse);

        ResponseEntity<UsuarioDTOResponse> response = usuarioController.buscarPorId(usuarioId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(usuarioResponse, response.getBody());
    }

    @Test
    void deveAtualizarUsuarioComSucesso() {
        UUID usuarioId = UUID.randomUUID();
        UsuarioDTORequest usuarioRequest = new UsuarioDTORequest("RM349225", "Lucas", "lucas-fiap@hotmail.com", "Correio", "Lu8&*");

        ResponseEntity<Void> response = usuarioController.atualizarUsuario(usuarioId, usuarioRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void deveExcluirUsuarioComSucesso() {
        UUID usuarioId = UUID.randomUUID();

        ResponseEntity<Void> response = usuarioController.excluirUsuario(usuarioId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
